package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/6.
 * 申请类型
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplyTypeDataInitTest {
    @Autowired ApplyTypeRepository applyTypeRepository;
    @Test
    public void find() {
        ApplyType applyType = applyTypeRepository.findOneByName("区\\县器具用户新增器具");
        assertThat(applyType).isNotNull();
    }
}
