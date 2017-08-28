package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by 安强 on 2017/5/24.
 * 器具生产年检信息 单元测试
 */
public class InstrumentProductionAnnualRepositoryTest extends RepositoryTest {

    @Autowired
    private InstrumentProductionAnnualRepository instrumentProductionAnnualRepository;

    @Autowired
    private InstrumentProductionRepository instrumentProductionRepository;

    @Test
    public void save() {
        //存器具生产信息
        InstrumentProduction instrumentmentProduction = new InstrumentProduction();
        instrumentProductionRepository.save(instrumentmentProduction);

        //存器具生产年检信息
        InstrumentProductionAnnual instrumentProductionAnnual = new InstrumentProductionAnnual();
        instrumentProductionAnnual.setInstrumentProduction(instrumentmentProduction);
        instrumentProductionAnnualRepository.save(instrumentProductionAnnual);
        System.out.println(instrumentProductionAnnual);

        //断言
        assertThat(instrumentProductionAnnual.getId()).isNotNull();
    }
}