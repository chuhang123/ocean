package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WebAppMenu;

import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 前台菜单 M层
 */
public interface WebAppMenuService {
    //保存
    WebAppMenu save(WebAppMenu webAppMenu);

    //获取所有数据
    List<WebAppMenu> getAll();

    static WebAppMenu getOneWebAppMenu() {
        String randString = CommonService.getRandomStringByLength(10);
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenu.setName("测试" + randString);
        webAppMenu.setRouteName("testroute" + randString);
        return webAppMenu;
    }
}
