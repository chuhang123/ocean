package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-18.
 */
public class QualifierCertificateTypeRepositoryTest extends RepositoryTest{
    @Autowired
    private QualifierCertificateTypeRepository qualifierCertificateTypeRepository;

    @Test
    public void save() {
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();

        //保存并断言
        qualifierCertificateTypeRepository.save(qualifierCertificateType);
        assertThat(qualifierCertificateType.getId()).isNotNull();
    }
}