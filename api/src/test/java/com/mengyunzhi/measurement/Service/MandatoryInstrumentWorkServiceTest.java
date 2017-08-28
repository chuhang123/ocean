package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请 工作
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MandatoryInstrumentWorkServiceTest {
    private Logger logger = Logger.getLogger(MandatoryInstrumentWorkServiceTest.class.getName());
    @Autowired
    protected DepartmentRepository departmentRepository;        // 部门
    @Autowired protected UserService userService;               // 用户
    @Autowired protected MandatoryInstrumentWorkService mandatoryInstrumentWorkService; // 强制检定申请工作
    @Autowired
    protected UserRepository userRepository;        // 用户

    @Test
    @Transactional
    public void pageOfCurrentLoginUser() {
        logger.info("新建一个部门");
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("新建一个用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userService.setCurrentLoginUser(user);

        logger.info("调用方法");
        PageRequest pageRequest = new PageRequest(1,1);
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUser(pageRequest);

        logger.info("断言获取的总数为0");
        assertThat(works.getTotalElements()).isEqualTo(0);
    }
}