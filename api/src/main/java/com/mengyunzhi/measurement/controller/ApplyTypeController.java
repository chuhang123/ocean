package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.repository.ApplyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by panjie on 17/7/28.
 * 申请类型
 */
@RestController
@RequestMapping("/ApplyType")
@Api(tags = "ApplyType 申请类型")
public class ApplyTypeController {
    @Autowired private ApplyTypeService applyTypeService;   // 申请
    @GetMapping("/getOneByWebAppMenuId/{webAppMenuId}")
    @ApiOperation(value = "getOneByWebAppMenuId 获取前台菜单对应的申请类型", nickname = "ApplyType_getOneByWebAppMenuId")
    public ApplyType getOneByWebAppMenuId(@Param("前台菜单ID") @PathVariable Long webAppMenuId) {
        return applyTypeService.getOneByWebAppMenuId(webAppMenuId);
    }
}
