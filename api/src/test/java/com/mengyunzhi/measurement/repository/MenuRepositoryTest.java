package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-19.
 */
public class MenuRepositoryTest extends RepositoryTest{
    private static Logger logger = Logger.getLogger(MenuRepositoryTest.class.getName());
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save() {
        logger.info("创建一个新menu");
        Menu menu = new Menu();

        logger.info("保存");
        menuRepository.save(menu);
        logger.info("断言");
        assertThat(menu.getId()).isNotNull();
    }
}