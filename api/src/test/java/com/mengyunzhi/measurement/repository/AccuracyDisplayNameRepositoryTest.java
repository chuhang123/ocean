package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-18.
 */
public class AccuracyDisplayNameRepositoryTest extends RepositoryTest{
    @Autowired
    private AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    @Test
    public void save() {
        AccuracyDisplayName accuracyDisplayName = new AccuracyDisplayName();

        //保存并断言
        accuracyDisplayNameRepository.save(accuracyDisplayName);
        assertThat(accuracyDisplayName.getId()).isNotNull();
    }
}