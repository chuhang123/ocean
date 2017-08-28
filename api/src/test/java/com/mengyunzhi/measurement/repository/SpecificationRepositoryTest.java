package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-20.
 * 规格型号
 */
public class SpecificationRepositoryTest extends RepositoryTest{
    @Autowired
    private SpecificationRepository specificationRepository;

    @Test
    public void save() {
        Logger logger = Logger.getLogger(SpecificationRepositoryTest.class.getName());
        logger.info("创建一个规格型号");
        Specification specification = new Specification();
        specification.setValue("value");
        logger.info("保存");
        specificationRepository.save(specification);
        logger.info("断言");
        assertThat(specification.getId()).isNotNull();
    }
}