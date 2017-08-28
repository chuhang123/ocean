package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-22.
 */
public class DepartmentTypePostRepositoryTest extends RepositoryTest{
    @Autowired
    private DepartmentTypePostRepository departmentTypePostRepository;                  //部门类型实体仓库
    static private Logger logger = Logger.getLogger(DepartmentTypePostRepository.class.getName());
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;                          //部门类型实体仓库

    @Autowired
    private PostRepository postRepository;                                              //岗位实体仓库

    @Test
    public void save() {
        logger.info("存部门类型");
        DepartmentType departmentType = new DepartmentType();
        departmentType.setName("部门类型");
        departmentTypeRepository.save(departmentType);

        logger.info("存岗位");
        Post post = new Post();
        post.setName("批准人");
        postRepository.save(post);

        logger.info("新建部门类型-岗位");
        DepartmentTypePost departmentTypePost = new DepartmentTypePost(departmentType,post);

        logger.info("保存");
        departmentTypePostRepository.save(departmentTypePost);

        logger.info(("断言成功"));
        assertThat(departmentTypePost.getId()).isNotNull();
    }
}