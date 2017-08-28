package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Role;
import com.mengyunzhi.measurement.repository.RoleRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 安强 on 2017/6/2.
 * 角色 M层测试
 */
public class RoleServiceTest extends ServiceTest{

    private Logger logger = Logger.getLogger(RoleServiceTest.class.getName());
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save() throws Exception {
        //存角色
        Role role = new Role();
        roleService.save(role);

        //断言存成功
        role = roleRepository.findOne(role.getId());
        assertThat(role).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        //存角色
        Role role = new Role();
        roleService.save(role);

        //断言可取
        assertThat(roleService.getAll().size()).isNotEqualTo(0);
    }

    @Test
    public void update() {
        logger.info("新建角色");
        Role role = new Role();
        roleRepository.save(role);

        Role newRole = new Role();
        newRole.setName("hello");
        roleService.update(role.getId(), newRole);

        assertThat(roleRepository.findOne(role.getId()).getName()).isEqualTo("hello");
    }

}