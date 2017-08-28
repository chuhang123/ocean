package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/6/14.
 * 用户-角色 测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRoleRepositoryTest {
    private static Logger logger = Logger.getLogger(UserRoleRepositoryTest.class.getName());

    @Autowired
    protected RoleRepository roleRepository;        // 角色

    @Autowired
    protected UserRepository userRepository;        // 用户

    @Autowired
    protected UserRoleRepository userRoleRepository;    // 用户-角色

    @Test
    public void test () {
        logger.info("添加一个角色");
        Role role = new Role();
        roleRepository.save(role);

        logger.info("添加一个用户，并设置角色");
        User user = new User();
        user.addRole(role);
        userRepository.save(user);

        logger.info("查找关联表，并断言");
        UserRole userRole = userRoleRepository.findOne(new UserRole.Id(user.getId(), role.getId()));
        assertThat(userRole).isNotNull();
    }
}