package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.CommonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-17.
 */
public class DepartmentTypeRepositoryTest extends RepositoryTest{
    private static Logger logger = Logger.getLogger(DepartmentType.class.getName());
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private PostRepository postRepository;
    @Test
    public void save() {
        logger.info("创建部门类型");
        DepartmentType departmentType = new DepartmentType();
        logger.info("保存");
        departmentTypeRepository.save(departmentType);
        logger.info("断言保存成功");
        assertThat(departmentType.getId()).isNotNull();
    }

    @Test
    public void getByNameTest(){
        logger.info("测试getByName");
        logger.info("增加一个实体, 并设置实体的name");
        String name = CommonService.getRandomStringByLength(20);
        DepartmentType departmentType = new DepartmentType(name, name,0L,0L ,null);

        logger.info("保存实体");
        departmentTypeRepository.save(departmentType);

        logger.info("查找实体并断言");
        assertThat(departmentTypeRepository.getByName(name).getId()).isEqualTo(departmentType.getId());
    }

}