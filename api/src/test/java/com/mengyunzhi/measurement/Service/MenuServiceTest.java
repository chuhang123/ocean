package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Menu;
import com.mengyunzhi.measurement.repository.MenuRepository;
import com.mengyunzhi.measurement.repository.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by 安强 on 2017/6/2.
 * 后台菜单 M层测试
 */
public class MenuServiceTest extends ServiceTest{

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void save() throws Exception {
        //存菜单
        Menu menu = new Menu();
        menuService.save(menu);

        //断言存成功
        menu = menuRepository.findOne(menu.getId());
        assertThat(menu).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        //存菜单
        Menu menu = new Menu();
        menuService.save(menu);

        //断言可取
        assertThat(menuService.getAll().size()).isNotEqualTo(0);
    }

}