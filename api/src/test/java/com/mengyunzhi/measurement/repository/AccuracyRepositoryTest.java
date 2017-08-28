package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by administrator on 2017/5/24.
 * 精度
 */
public class AccuracyRepositoryTest extends RepositoryTest{
    private Logger logger = Logger.getLogger(AccuracyRepositoryTest.class.getName());

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Test
    public void save(){
        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();

        //保存实体
        accuracyRepository.save(accuracy);

        //断言已存
        assertThat(accuracy.getId()).isNotNull();
    }

}