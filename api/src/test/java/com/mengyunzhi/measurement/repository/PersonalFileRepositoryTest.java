package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-6.
 */
public class PersonalFileRepositoryTest extends RepositoryTest {

    @Autowired
    private PersonalFileRepository personalFileRepository;
    @Test
    public void save() {
        PersonalFile personalFile = new PersonalFile();
        personalFileRepository.save(personalFile);

        //断言
        assertThat(personalFile.getId()).isNotNull();
    }
}