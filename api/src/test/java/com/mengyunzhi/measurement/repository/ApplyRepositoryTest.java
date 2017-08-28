package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * Created by liming on 17-5-21.
 */
public class ApplyRepositoryTest extends RepositoryTest{
    @Autowired
    private ApplyRepository applyRepository;
    static private Logger logger = Logger.getLogger(ApplyRepositoryTest.class.getName());

    @Test
    public void findAll() {
        applyRepository.findAll();
    }
}