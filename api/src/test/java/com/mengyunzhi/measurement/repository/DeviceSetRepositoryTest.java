package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeviceSetRepositoryTest {
    @Autowired private DeviceSetRepositoryTestDataInit deviceSetDeviceInstrumentRepositoryTestDataInit;
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    static private Logger logger = Logger.getLogger(DeviceSetRepositoryTest.class.getName());

    @Test
    public void save() {
        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        logger.info("保存");
        deviceSetRepository.save(deviceSet);

        assertThat(deviceSet.getId()).isNotNull();
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void count() {
        Department department = new Department();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceSetDeviceInstrumentRepositoryTestDataInit.countByDeviceInstrumentAndDeviceSetDepartment(deviceInstrument, department);
        logger.info("调用方法，并断言返回的条目数为1");
        int count = deviceSetRepository.countByDepartmentAndDeviceInstrument(department, deviceInstrument);
        assertThat(count).isEqualTo(1);
    }


}