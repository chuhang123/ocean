package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by administrator on 2017/5/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DistrictRepositoryTest {
    private Logger logger = Logger.getLogger(DistrictRepositoryTest.class.getName());
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictTypeRepository districtTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        //新建用户
        User user = new User();
        userRepository.save(user);

        //新建区域类别
        DistrictType districtType = new DistrictType();
        districtTypeRepository.save(districtType);

        //新建区域
        District district = new District();
        district.setDistrictType(districtType);
        district.setCreateUser(user);
        districtRepository.save(district);

        //断言
        assertThat(district.getId()).isNotNull();
    }

    @Test
    public void getByName() {
        //新建区域
        District district = new District();
        district.setName("hello kitty");
        districtRepository.save(district);
        assertThat(districtRepository.findOne(district.getId()).getName()).isEqualTo("hello kitty");
    }

    @Test
    public void getAllByParentDistrictId(){
        District district = new District();
        districtRepository.save(district);
        District sonDistrict = new District();
        sonDistrict.setParentDistrict(district);

        List<District> districts = districtRepository.getAllByParentDistrictId(district.getId());
        assertThat(districts.size()).isEqualTo(0);
    }

}