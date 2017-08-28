package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 安强 on 2017/6/1.
 * 前台菜单 实体仓库测试
 */
public class WebAppMenuRepositoryTest extends RepositoryTest{
    private static Logger logger = Logger.getLogger(WebAppMenuRepositoryTest.class.getName());

    @Autowired
    private WebAppMenuRepository webAppMenuRepository;  // 前台菜单

    @Autowired
    protected RoleRepository roleRepository;            // 角色

    @Autowired
    protected UserRepository userRepository;            // 用户

    @Test
    public void save(){
        //向前台菜单中注入角色信息
        WebAppMenu webAppMenu = new WebAppMenu();

        //存菜单
        webAppMenuRepository.save(webAppMenu);
        assertThat(webAppMenu.getId()).isNotNull();
    }

    @Test
    public void  findListsByUserId() {
        logger.info("添加一个前台菜单");
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuRepository.save(webAppMenu);

        logger.info("添加一个角色，前设置其前台菜单");
        Role role = new Role();
        role.addWebAppMenu(webAppMenu);
        roleRepository.save(role);

        logger.info("添加一个用户，并设置其角色");
        User user = new User();
        user.addRole(role);
        userRepository.save(user);

        logger.info("断言找到的前台菜单为前台的前台菜单");
        List<WebAppMenu> webAppMenus = webAppMenuRepository.findAllByUserId(user.getId());
        assertThat(webAppMenus.size()).isNotZero();

        WebAppMenu webAppMenu1 = webAppMenus.get(0);
        assertThat(webAppMenu1.getId()).isEqualTo(webAppMenu.getId());

        return;
    }

}