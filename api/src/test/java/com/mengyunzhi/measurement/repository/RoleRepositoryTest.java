package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-17.
 */
public class RoleRepositoryTest extends RepositoryTest{
    private static Logger logger = Logger.getLogger(RoleRepositoryTest.class.getName());
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WebAppMenuRepository webAppMenuRepository; // 前台菜单

    //测试保存方法
    @Test
    public void save() {
        // 我们在这里，并没有对user进行持久化操作。在进行role的保存操作时，仍然成功的进行持久化。
        // 这里由于在Role中， users 设置为：cascade = CascadeType.PERSIST（持续的）。
        // 即在保存role时，同时对user进行持久化操作。
        logger.info("新建用户");
        User user = new User();
        Set users = new HashSet<User>();
        users.add(user);

        // 以下代码，我们进行了持久化的操作后，将其添加到了Role中。
        // 这是由于 cascade = CascadeType.MERGE -- 合并 决定的。
        // 即在保存role时，我们假设webAppMenu已经是一个真实存在的实体。
        // 如果在保存role时，webAppMenu并没有进行持久化。则会报错。
        logger.info("新建菜单");
        WebAppMenu webAppMenu = new WebAppMenu();
        Set webAppMenus = new HashSet<WebAppMenu>();
        webAppMenuRepository.save(webAppMenu);
        webAppMenus.add(webAppMenu);


        logger.info("新建角色, 并赋值前台菜单");
        Role role = new Role();
        role.setWebAppMenus(webAppMenus);
        roleRepository.save(role);

        logger.info("断言保存成功");
        assertThat(role.getId()).isNotNull();
    }

    @Test
    public void getByName() {
        logger.info("---- 断言findByName成功通过 ----");
        String name = "xxxxdfsdfsdfsfsdfefsaf";
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);

        assertThat(roleRepository.findOneByName(name)).isNotNull();
    }

    @Test
    public void findOneByAdmin() {
        logger.info("----- 测试 -----");
        if (null == roleRepository.findOneByIsAdmin(true)) {
            Role role = new Role();
            role.setAdmin(true);
        }

        assertThat(roleRepository.findOneByIsAdmin(true)).isNotNull();
    }

}