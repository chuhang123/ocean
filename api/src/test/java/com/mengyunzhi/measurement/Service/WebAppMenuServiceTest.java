package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 安强 on 2017/6/2.
 * 前台菜单 M层测试
 */
public class WebAppMenuServiceTest extends ServiceTest{
    @Autowired
    private WebAppMenuService webAppMenuService;
    @Autowired
    private WebAppMenuRepository webAppMenuRepository;

    @Test
    public void save() throws Exception {
        //村前台菜单
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuService.save(webAppMenu);

        //断言存成功
        webAppMenu = webAppMenuRepository.findOne(webAppMenu.getId());
        assertThat(webAppMenu).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        //存前台菜单
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuService.save(webAppMenu);

        List<WebAppMenu> webAppMenuList = webAppMenuService.getAll();
        //断言可取
        assertThat(webAppMenuList.size()).isNotEqualTo(0);
    }

}