package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/25.
 * 区域类型
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictTypeDataInitTest {
    private Logger logger = Logger.getLogger(DistrictTypeDataInitTest.class.getName());
    // 区域类型
    @Autowired
    private DistrictTypeRepository districtTypeRepository;

    @Test
    public void DistrictTypeTest() {
        logger.info("----- 测试区域类型是否添加成功 panjie -----");
        assertThat(districtTypeRepository.findOneByName("市")).isNotNull();
    }
}