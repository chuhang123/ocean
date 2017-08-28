package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-5-17.
 * 用户实体仓库测试
 */
public class UserRepositoryTest extends RepositoryTest {
    static private Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void save() {
        //存角色
        logger.info("创建一个新角色");
        Role role = new Role();
        roleRepository.save(role);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        //更新用户部门岗位信息
//        Department department = departmentRepository.findOne(1L);
//        Post post = postRepository.findOne(1L);
//        DepartmentPostId departmentPostId = new DepartmentPostId(department.getId(),post.getId());
//        DepartmentPost departmentPost = new DepartmentPost(departmentPostId,department,post);


        //存user
        logger.info("创建用户并保存");
        User user = new User();
        user.setStatus(2);
        Post post = new Post();
        postRepository.save(post);
        user.addPost(post);
        user.setRoles(roles);
        userRepository.save(user);
//
        logger.info("断言用户id不为null ,创建成功");
        assertThat(user.getId()).isNotNull();
    }
}