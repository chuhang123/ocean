package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liming on 17-5-17.
 */
public class ApplyTypeRepositoryTest extends RepositoryTest{
    @Autowired
    private ApplyTypeRepository applyTypeRepository;

    @Test
    public void findAll() {
        applyTypeRepository.findAll();
    }
}