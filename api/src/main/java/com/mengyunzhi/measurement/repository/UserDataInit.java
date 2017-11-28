package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panjie on 17/7/16.
 * 用户数据
 */
@Component
public class UserDataInit extends ApiInitDataListener {
    private Logger logger = Logger.getLogger(UserDataInit.class.getName());
    private final DepartmentDataInit departmentDataInit; // 部门
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserDataInit(DepartmentDataInit departmentDataInit, RoleRepository roleRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.departmentDataInit = departmentDataInit;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (null == userRepository.findOneByUsername("admin")) {
            User adminUser = new User();
            adminUser.setName("系统管理员");
            adminUser.setStatus(0);
            adminUser.setPinyin("xitongguanliyuan");
            adminUser.setUsername("admin");
            adminUser.setPassword("admin");

            Role adminRole = roleRepository.findOneByName("系统管理员");
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);

            Department department = departmentRepository.findByName("河北省管理部门");
            adminUser.setDepartment(department);

            userRepository.save(adminUser);

            logger.info("为测试器具用户添加一个用户");
            Role role = roleRepository.findOneByIsAdmin(true);
            User user1 = new User();
            user1.setDepartment(departmentRepository.findByName("测试器具用户"));
            //user1.addRole(role);
            user1.setUsername("user1");
            user1.setPassword("user1");
            user1.setName("测试器具用户");
            user1.setStatus(0);
            userRepository.save(user1);

            logger.info("为测试区县管理部门添加一个用户");
            User user2 = new User();
            user2.setDepartment(departmentRepository.findByName("测试区县管理部门"));
            //user2.addRole(role);
            user2.setUsername("user2");
            user2.setPassword("user2");
            user2.setName("测试区县管理部门用户");
            user2.setStatus(0);
            userRepository.save(user2);

            logger.info("为测试区县技术机构添加一个用户");
            User user3 = new User();
            user3.setDepartment(departmentRepository.findByName("测试区县技术机构"));
            //user3.addRole(role);
            user3.setUsername("user3");
            user3.setPassword("user3");
            user3.setName("测试区县技术机构用户");
            user3.setStatus(0);
            userRepository.save(user3);

            logger.info("为测试市管理部门添加一个用户");
            User user4 = new User();
            user4.setDepartment(departmentRepository.findByName("测试市管理部门"));
            //user4.addRole(role);
            user4.setUsername("user4");
            user4.setPassword("user4");
            user4.setName("测试市管理部门");
            user4.setStatus(0);
            userRepository.save(user4);

            logger.info("为测试市技术机构添加一个用户");
            User user5 = new User();
            user5.setDepartment(departmentRepository.findByName("测试市技术机构"));
            //user5.addRole(role);
            user5.setUsername("user5");
            user5.setPassword("user5");
            user5.setName("测试市技术机构");
            user5.setStatus(0);
            userRepository.save(user5);

        }
    }

        @Override
    public int getOrder() {
        return departmentDataInit.getOrder() + 10;
    }
}
