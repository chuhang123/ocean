package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-17.
 */
public class InstrumentCertificateTypeRepositoryTest extends RepositoryTest{
    @Autowired
    private InstrumentCertificateTypeRepository instrumentCertificateTypeRepository;

    @Test
    public void save() {
        InstrumentCertificateType instrumentCertificateType = new InstrumentCertificateType();

        //保存并断言
        instrumentCertificateTypeRepository.save(instrumentCertificateType);
        assertThat(instrumentCertificateType.getId()).isNotNull();
    }
}