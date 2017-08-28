package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-5-21.
 */
public class MeasureScaleRepositoryTest extends RepositoryTest{
    static private Logger logger = Logger.getLogger(MeasureScaleRepositoryTest.class.getName());

    @Autowired
    private MeasureScaleRepository measureScaleRepository;

    @Test
    public void save() {
        logger.info("创建测量范围");
        MeasureScale measureScale = new MeasureScale();
        logger.info("保存");
        measureScaleRepository.save(measureScale);
        logger.info("断言");
        assertThat(measureScale.getId()).isNotNull();
    }
}