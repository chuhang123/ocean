package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * Created by liming on 17-5-9.
 * 标准器的测试文件
 */
public class StandardDeviceServiceTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(StandardDeviceServiceTest.class.getName());
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    protected StandardDeviceService standardDeviceService;
    @Autowired
    protected AccuracyRepository accuracyRepository;
    @Autowired
    protected MeasureScaleRepository measureScaleRepository;
    @Autowired
    protected SpecificationRepository specificationRepository;
    @Autowired
    protected InstrumentTypeRepository instrumentTypeRepository;

    @Test
    public void getAllByDeviceSetId() {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("测试");
        List<StandardDevice> standardDevices = standardDeviceService.getAllByDeviceSetId(deviceSet.getId());
        logger.info("断言");
        assertThat(standardDevices.size()).isEqualTo(1);
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void save() {
        logger.info("新建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个精度");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        logger.info("新建测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setValue("456");
        measureScaleRepository.save(measureScale);
        logger.info("新建一个型号规格");
        Specification specification = new Specification();
        specification.setValue("adf");
        specificationRepository.save(specification);
        logger.info("新建器具类别");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("456");
        standardDeviceInstrumentType.setPinyin("456");
        instrumentTypeRepository.save(standardDeviceInstrumentType);
        logger.info("新建标准器具");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDevice.setAccuracy(accuracy);
        standardDevice.setMeasureScale(measureScale);
        standardDevice.setSpecification(specification);
        standardDevice.setStandardDeviceInstrumentType(standardDeviceInstrumentType);
        logger.info("保存");
        standardDeviceRepository.save(standardDevice);
        logger.info("断言");
        StandardDevice newStandardDevice = standardDeviceRepository.findOne(standardDevice.getId());
        assertThat(newStandardDevice.getId()).isEqualTo(standardDevice.getId());
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        specificationRepository.delete(specification);
        deviceSetRepository.delete(deviceSet);
        measureScaleRepository.delete(measureScale);
        accuracyRepository.delete(accuracy);
        instrumentTypeRepository.delete(standardDeviceInstrumentType);
    }

    @Test
    public void update() {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setName("name");
        standardDeviceService.save(standardDevice);
        logger.info("更改名字");
        standardDevice.setName("newName");
        logger.info("测试");
        StandardDevice newStandardDeviece = standardDeviceService.update(standardDevice.getId(), standardDevice);
        logger.info("断言");
        assertThat(newStandardDeviece.getName()).isEqualTo("newName");
        logger.info("删除实体");
        standardDeviceRepository.delete(standardDevice);
    }

    @Test
    public void delete() {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);
        logger.info("测试");
        standardDeviceService.delete(standardDevice.getId());
        logger.info("断言");
        assertThat(standardDeviceRepository.findOne(standardDevice.getId())).isNull();
    }

    @Test
    public void pageAllByDeviceSetId() {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        PageRequest pageRequest = new PageRequest(1,1);
        logger.info("测试");
        Page<StandardDevice> standardDevices = standardDeviceService.pageAllByDeviceSetId(deviceSet.getId(), pageRequest);
        logger.info("断言");
        assertThat(standardDevices.getTotalPages()).isEqualTo(1);
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }
}