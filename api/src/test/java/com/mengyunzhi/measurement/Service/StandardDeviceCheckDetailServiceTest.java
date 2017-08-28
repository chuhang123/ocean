package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetail;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-14.
 * 测试标准器检定信息serviceTest
 */
public class StandardDeviceCheckDetailServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(StandardDeviceCheckDetailServiceTest.class.getName());
    @Autowired
    private StandardDeviceCheckDetailService standardDeviceCheckDetailService;
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    protected StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Test
    public void save() throws Exception {
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        //保存
        standardDeviceCheckDetailService.save(standardDeviceCheckDetail);
        //断言
        assertThat(standardDeviceCheckDetail.getId()).isNotNull();
    }

    @Test
    public void update() throws Exception {
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        // 保存
        standardDeviceCheckDetailService.save(standardDeviceCheckDetail);
        // 修改
        standardDeviceCheckDetail.setInspectorQualifierCertificate("ceshi");
        // 更新
        standardDeviceCheckDetailService.update(standardDeviceCheckDetail,standardDeviceCheckDetail.getId());
        // 断言修改成功
        StandardDeviceCheckDetail standardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(standardDeviceCheckDetail.getId());
        assertThat(standardDeviceCheckDetail.getInspectorQualifierCertificate()).isEqualTo("ceshi");
    }

    @Test
    public void delete() throws Exception {
        // 新建
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        // 保存
        standardDeviceCheckDetailService .save(standardDeviceCheckDetail);
        // 删除
        standardDeviceCheckDetailService.delete(standardDeviceCheckDetail.getId());
        // 断言
        StandardDeviceCheckDetail standardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(standardDeviceCheckDetail.getId());
        assertThat(standardDeviceCheckDetail1).isNull();
    }

    @Test
    public void getAllByStandardDeviceId() {
        List<StandardDeviceCheckDetail> list = new ArrayList<StandardDeviceCheckDetail>();
        //获取一个standarDevice
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);

        list = standardDeviceCheckDetailService.getAllByStandardDevice(standardDevice);
        //打印
        System.out.println(list);
    }

    @Test
    public void pageAllByStandardDeviceId() {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceRepository.save(standardDevice);
        logger.info("新建一个标准器检定信息");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setStandardDevice(standardDevice);
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        PageRequest pageRequest = new PageRequest(1, 1);
        logger.info("测试");
        Page<StandardDeviceCheckDetail> page = standardDeviceCheckDetailService.pageAllByStandardDeviceId(standardDevice.getId(), pageRequest);

        logger.info("断言");
        assertThat(page.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        standardDeviceCheckDetailRepository.delete(standardDeviceCheckDetail);
        standardDeviceRepository.delete(standardDevice);
    }
}