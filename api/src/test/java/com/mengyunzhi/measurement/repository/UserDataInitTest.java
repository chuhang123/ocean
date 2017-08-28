package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/7/16.
 * 用户数据初始化
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDataInitTest {
    @Autowired private UserRepository userRepository;
    @Test
    public void addUser() {
        User user = userRepository.findOneByUsername("user1");
        assertThat(user).isNotNull();
    }
}
