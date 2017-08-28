package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-16.
 */
public class DistrictTypeRepositoryTest extends RepositoryTest{
    private Logger logger = Logger.getLogger(DistrictTypeRepositoryTest.class.getName());

    @Autowired
    private DistrictTypeRepository districtTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void save() {
        //创建区域类型
        DistrictType districtType = new DistrictType();
        //创建一个用户
        User user = new User();
        userRepository.save(user);
        //保存区域类型
        districtTypeRepository.save(districtType);
        //断言
        assertThat(districtType.getId()).isNotNull();
    }

    @Test
    public void getByNameTest() {
        logger.info("测试getByName");
        logger.info("增加一个实体, 并设置实体的name");
        String name = "xcvdsfsdfsefewfewfwe";
        DistrictType districtType = new DistrictType(name, name, null);

        logger.info("保存实体");
        districtTypeRepository.save(districtType);

        logger.info("查找实体并断言");
        assertThat(districtTypeRepository.findOne(districtType.getId()).getName()).isEqualTo(name);
    }
}