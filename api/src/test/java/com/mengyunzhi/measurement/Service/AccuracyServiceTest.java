package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Accuracy;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccuracyServiceTest {
    private Logger logger = Logger.getLogger(AccuracyServiceTest.class.getName());

    @Autowired
    private AccuracyService accuracyService; // 精度

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Test
    public void save() {
        Accuracy accuracy = new Accuracy();
        accuracyService.save(accuracy);
        assertThat(accuracy.getId()).isNotNull();

        accuracyRepository.delete(accuracy.getId());
    }
}