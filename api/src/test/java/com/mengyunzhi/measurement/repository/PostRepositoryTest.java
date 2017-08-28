package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-17.
 */
public class PostRepositoryTest extends RepositoryTest{
    private static Logger logger = Logger.getLogger(RoleRepositoryTest.class.getName());
    @Autowired
    private PostRepository postRepository;

    @Test
    public void save() {
        logger.info("新建岗位");
        Post post = new Post();
        logger.info("保存");
        postRepository.save(post);
        logger.info("断言保存成功");
        assertThat(post.getId()).isNotNull();
    }

    @Test
    public void getByNameTest() throws Exception {
        Post post = new Post();
        String name = "sdfsdfewfsflsjlfjlksjfiojweoijgjflsfsdf";
        post.setName(name);
        if (postRepository.getByName(name) == null) {
            postRepository.save(post);
            assertThat(postRepository.getByName(name)).isNotNull();
        }
    }

    @Test
    public void findTopByOrderByIdAsc() {
        logger.info("添加一个实体，并断言获取到的第一实体的ID值不会大于刚刚新添加的实体");
        Post post = new Post();
        postRepository.save(post);
        Post post1 = postRepository.findTopByOrderByIdAsc();
        assertThat(post.getId()).isGreaterThanOrEqualTo(post1.getId());
    }
}