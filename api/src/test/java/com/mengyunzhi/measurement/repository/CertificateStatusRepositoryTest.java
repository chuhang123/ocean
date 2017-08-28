package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-18.
 */
public class CertificateStatusRepositoryTest extends RepositoryTest{
    @Autowired
    private CertificateStatusRepository certificateStatusRepository;

    @Test
    public void save() {
        CertificateStatus certificateStatus = new CertificateStatus();

        //保存并断言
        certificateStatusRepository.save(certificateStatus);
        assertThat(certificateStatus.getId()).isNotNull();
    }
}