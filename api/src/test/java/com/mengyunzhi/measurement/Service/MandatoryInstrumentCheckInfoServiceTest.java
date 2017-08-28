package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-8-2.
 * 强检器具检定信息ServiceTest
 */
public class MandatoryInstrumentCheckInfoServiceTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(MandatoryInstrumentCheckInfoServiceTest.class.getName());
    @Autowired protected MandatoryInstrumentCheckInfoServiceTestData mandatoryInstrumentCheckInfoServiceTestData;
    @Autowired protected MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;
    @Autowired protected UserRepository userRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired protected InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Test
    public void pageAllOfCurrentUser() throws Exception {
        logger.info("准备测试数据");
        User user = userRepository.findOneByUsername("user3");
        Department department = departmentRepository.findByName("测试区县技术机构");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();

        mandatoryInstrumentCheckInfoServiceTestData.getOneMandatoryInstrumentCheckInfo(department, user ,mandatoryInstrument, instrumentCheckInfo);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(1,1);
        Page<InstrumentCheckInfo> instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfCurrentUser(pageRequest);

        logger.info("断言");
        assertThat(instrumentCheckInfos.getTotalPages()).isEqualTo(1);
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void update() {
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        logger.info("更新");
        instrumentCheckInfo.setCertificateNum("123456789");

        logger.info("测试");
        mandatoryInstrumentCheckInfoService.update(instrumentCheckInfo.getId(), instrumentCheckInfo);

        logger.info("断言");
        InstrumentCheckInfo instrumentCheckInfo1 = instrumentCheckInfoRepository.findOne(instrumentCheckInfo.getId());
        assertThat(instrumentCheckInfo1.getCertificateNum()).isEqualTo("123456789");

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
    }

    @Test
    public void pageAllByMandatoryInstrumentId() {
        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(0,1);
        Page<InstrumentCheckInfo> instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllByMandatoryInstrumentId(mandatoryInstrument.getId(), pageRequest);

        logger.info("断言");
        assertThat(instrumentCheckInfos.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }
}