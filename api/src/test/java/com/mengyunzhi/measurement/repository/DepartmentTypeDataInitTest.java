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
 * 部门类型
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentTypeDataInitTest {
    private Logger logger = Logger.getLogger(DepartmentTypeDataInitTest.class.getName());
    //部门类型
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Test
    public void DepartmentTypeTest() {
        logger.info("----- 测试部门类型是否添加成功 panjie -----");
        assertThat(departmentTypeRepository.getByName("管理部门")).isNotNull();
    }
}