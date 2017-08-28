package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.WebAppMenuService;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by panjie on 17/6/7.
 * 前台菜单
 */
@Api(tags = "WebAppMenu 前台菜单", description = "前台菜单 用于前台注册路由及显示左侧菜单")
@RequestMapping("/WebAppMenu")
@RestController
public class WebAppMenuController {
    // 前台菜单
    @Autowired
    private WebAppMenuService webAppMenuService;

    @ApiOperation(value = "/ 获取所有菜单列表", notes = "获取所有的前台菜单", nickname = "WebAppMenu")
    @GetMapping("/")
    public List<WebAppMenu> getAll() {
        return webAppMenuService.getAll();
    }
}
