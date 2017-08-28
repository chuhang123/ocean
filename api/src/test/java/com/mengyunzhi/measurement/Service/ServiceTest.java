package com.mengyunzhi.measurement.Service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by zhangjiahao on 2017/5/13.
 * Service测试类父类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class ServiceTest {
}
