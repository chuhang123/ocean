package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.AccuracyDisplayNameService;
import com.mengyunzhi.measurement.repository.AccuracyDisplayName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/22.
 * 精度显示名称 C层
 */
@Api(tags = "AccuracyDisplayName 精度显示名称", description = "器具类别 精度显示名称")
@RestController
@RequestMapping("/AccuracyDisplayName")
public class AccuracyDisplayNameController {
    Logger logger = Logger.getLogger(AccuracyDisplayNameController.class.getName());

    @Autowired
    private AccuracyDisplayNameService accuracyDisplayNameService;

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "AccuracyDisplayName_getAll")
    @GetMapping("/getAll")
    public List<AccuracyDisplayName> getAll()
    {
        return accuracyDisplayNameService.getAll();
    }
}
