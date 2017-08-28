package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/5/5.
 */
public class OptionalIntergratedRepositoryTest extends RepositoryTest {
    @Autowired
    private OptionalIntergratedRepository optionalIntergratedRepository;
    @Test
    public void save() {
        OptionalIntegrated optionalIntegrated = new OptionalIntegrated();
        optionalIntergratedRepository.save(optionalIntegrated);

        assertThat(optionalIntegrated.getId()).isNotNull();

    }
}