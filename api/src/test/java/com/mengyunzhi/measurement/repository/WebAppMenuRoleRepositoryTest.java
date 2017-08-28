package com.mengyunzhi.measurement.repository;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/6/14.
 * 前台菜单 - 角色
 * 在这里，我们并没有启用事务。原因是由于如果启用事务的话，该测试用例将无法通过。
 * 具体阐述如下：
 * 启用事务后，数据直接被事务进行管理，当测试结束后，统一进行处理。
 * 1. 在测试未结束前，事务将数据保存在了内存中，并没在进行数据的持久化（将数据保存到数据表中）操作
 * 2. 由于我们在Role中未配置对WebAppMenuRole的oneToMany，所以在进行WebAppMenu的查询时。
 * 事务并没有发现，在事务管理中有此实体，随即访问了WebAppMenuRole对应的实体表。
 * 3. 由于1并没有将数据写在事务中，而并没有写入表中。所以2在进行表查询时，当然就查询不到了。
 * 4. 当未启用事务时，数据每操作一次都会进行一次持久化操作。当然，在进行完存储后，再进行查询就没有问题了。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAppMenuRoleRepositoryTest{
    private static Logger logger = Logger.getLogger(WebAppMenuRoleRepositoryTest.class.getName());
    @Autowired
    protected WebAppMenuRepository webAppMenuRepository;    // 前台菜单

    @Autowired
    protected RoleRepository roleRepository;    // 角色

    @Autowired
    protected WebAppMenuRoleRepository webAppMenuRoleRepository; // 前台菜单-角色

    @Test
    public void test() {

        logger.info("添加一个前台菜单");
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuRepository.save(webAppMenu);

        logger.info("添加一个角色");
        Role role = new Role();
        Set<WebAppMenu> webAppMenus = new HashSet<>();
        webAppMenus.add(webAppMenu);
        role.setWebAppMenus(webAppMenus);
        roleRepository.save(role);

        logger.info("查询这个数据添加到了数据表中");
        WebAppMenuRole.Id id = new WebAppMenuRole.Id(role.getId(), webAppMenu.getId());
        WebAppMenuRole webAppMenuRole = webAppMenuRoleRepository.findOne(id);
        assertThat(webAppMenuRole).isNotNull();
    }
}