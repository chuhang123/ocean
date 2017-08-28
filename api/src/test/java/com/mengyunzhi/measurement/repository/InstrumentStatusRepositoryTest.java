package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/5/20.
 * 器具状态 实体仓库测试
 */
public class InstrumentStatusRepositoryTest extends RepositoryTest {

    @Autowired
    private InstrumentStatusRepository instrumentStatusRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
//        创建user对象并保存
        User user = new User();
        userRepository.save(user);
//        创建器具状态对象，与user关联并保存
        InstrumentStatus instrumentStatus = new InstrumentStatus();
        instrumentStatus.setCreateUser(user);
        instrumentStatusRepository.save(instrumentStatus);
        System.out.println(instrumentStatus);
//       断言保存成功
        assertThat(instrumentStatus.getId()).isNotNull();
    }

}