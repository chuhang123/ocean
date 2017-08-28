package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Menu;

import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 后台 菜单  M层
 */
public interface MenuService {
    //保存方法
    Menu save(Menu menu);

    //获取所有菜案
    List<Menu> getAll();
}
