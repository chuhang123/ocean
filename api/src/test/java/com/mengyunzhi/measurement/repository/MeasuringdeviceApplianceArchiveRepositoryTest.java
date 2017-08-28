package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-7.
 */
public class MeasuringdeviceApplianceArchiveRepositoryTest extends RepositoryTest {
   @Autowired
   private MeasuringdeviceApplianceArchiveRepository measuringdeviceApplianceArchiveRepository;

    @Test
    public void save() {
        MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive = new MeasuringdeviceApplianceArchive();
        measuringdeviceApplianceArchiveRepository.save(measuringdeviceApplianceArchive);

        assertThat(measuringdeviceApplianceArchive.getId()).isNotNull();
    }
}