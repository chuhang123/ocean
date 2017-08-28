package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/25.
 * 前台菜单数据初始化测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebAppMenuDataInitTest {
    private Logger logger = Logger.getLogger(WebAppMenuDataInitTest.class.getName());
    // 前台菜单
    @Autowired
    private WebAppMenuRepository webAppMenuRepository;
    @Test
    public void test() {
        logger.info("----- 测试前台菜单是否添加成功 panjie -----");
        List<WebAppMenu> webAppMenus = webAppMenuRepository.findAll();
        assertThat(webAppMenus.size()).isGreaterThan(8);
        return;
    }

}