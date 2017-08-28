package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-18.
 */
public class CheckResultRepositoryTest extends RepositoryTest{
    @Autowired
    private CheckResultRepository checkResultRepository;

    @Test
    public void save() {
        CheckResult checkResult = new CheckResult();

        //保存并测试
        checkResultRepository.save(checkResult);
        assertThat(checkResult.getId()).isNotNull();
    }
}