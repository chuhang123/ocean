package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.StandardFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StandardDeviceRepositoryTest {
    @Autowired
    private StandardDeviceRepository standardDeviceRepository;
    @Autowired
    private StandardFileService standardFileService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void save() {
        //获取一个standardFile
        StandardFile standardFile = new StandardFile();
        standardFileService.save(standardFile);
        //获取一个操作用户
        User user = new User();
        userRepository.save(user);
        //获取一个部门
        Department department = new Department();
        departmentRepository.save(department);

        StandardDevice standardDevice = new StandardDevice();
        //保存
        standardDeviceRepository.save(standardDevice);
        //断言
        assertThat(standardDevice.getId()).isNotNull();
    }
}