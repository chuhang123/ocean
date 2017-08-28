package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by panjie on 17/7/26.
 * 强制检定器具类别
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MandatoryInstrumentTypeRepositoryTest {
    @Autowired MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;// 强制检定器具类别
    @Test
    public void test() {
        mandatoryInstrumentTypeRepository.findAll();
    }
}
