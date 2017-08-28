package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-25.
 */
public class InstrumentCheckInfoRepositoryTest extends RepositoryTest{
    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    static private Logger logger = Logger.getLogger(InstrumentCheckInfoRepositoryTest.class.getName());

    @Test
    public void save() {
        logger.info("创建器具检定信息");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        logger.info("保存");
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        logger.info("断言");
        assertThat(instrumentCheckInfo.getId()).isNotNull();
    }
}