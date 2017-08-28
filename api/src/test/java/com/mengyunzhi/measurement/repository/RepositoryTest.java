package com.mengyunzhi.measurement.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by zhangjiahao on 2017/5/12.
 * Repository测试类父类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class RepositoryTest {
}
