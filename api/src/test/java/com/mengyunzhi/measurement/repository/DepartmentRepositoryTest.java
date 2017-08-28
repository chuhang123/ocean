package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-22.
 * 部门实体仓库测试
 */
public class DepartmentRepositoryTest extends RepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    static private Logger logger = Logger.getLogger(DepartmentRepositoryTest.class.getName());

    @Autowired
    PostRepository postRepository;

    @Test
    public void save() {

        Department department = new Department();
        departmentRepository.save(department);
        assertThat(department.getId()).isNotNull();

    }

    @Test
    public void getByName(){
        logger.info("测试getByName");
        logger.info("增加一个实体, 并设置实体的name");
        String name = "haoliyou";
        Department department = new Department();

        department.setName(name);

        logger.info("保存实体");
        departmentRepository.save(department);

        logger.info("查找实体并断言");
        assertThat(departmentRepository.findByName(name).getId()).isEqualTo(department.getId());
    }
}