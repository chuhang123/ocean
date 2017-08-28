package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by administrator on 2017/5/24.
 */
public class InstrumentProductionRepositoryTest extends RepositoryTest {

    @Autowired
    private InstrumentProductionRepository instrumentProductionRepository;

    @Test
    public void save() {
        InstrumentProduction instrumentProduction = new InstrumentProduction();

        instrumentProductionRepository.save(instrumentProduction);
    }
}