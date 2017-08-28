package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by chuhang on 17-5-24.
 */
public class QualifierRepositoryTest  extends RepositoryTest{
    @Autowired
    private QualifierRepository qualifierRepository;
    static private Logger logger = Logger.getLogger(QualifierRepository.class.getName());

    @Test
    public void save() {
        logger.info("新建一个人员");
        Qualifier qualifier = new Qualifier();
        logger.info("保存");
        qualifierRepository.save(qualifier);
        logger.info("断言");
        assertThat(qualifier.getId()).isNotNull();
    }
}