package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 安强 on 2017/5/31.
 * 部门岗位用户  实体仓库测试
 */
public class DepartmentPostUserRepositoryTest extends RepositoryTest{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DepartmentPostUserRepository departmentPostUserRepository;

    @Test
    public void save(){

        //存部门
        Department department = new Department();
        departmentRepository.save(department);

        //存岗位
        Post post = new Post();
        postRepository.save(post);

        //创建用户
        User user = new User();
        userRepository.save(user);

        DepartmentPostUser departmentPostUser = new DepartmentPostUser();
        departmentPostUser.setDepartment(department);
        departmentPostUser.setPost(post);
        departmentPostUser.setUser(user);

        //存用户
        departmentPostUserRepository.save(departmentPostUser);
    }

}