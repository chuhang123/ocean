package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DepartmentTypeService;
import com.mengyunzhi.measurement.repository.DepartmentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
@Api(tags = "DepartmentType (部门类型)", description = "部门 部门类型C层")
@RestController
@RequestMapping("/DepartmentType")
public class DepartmentTypeController {

    @Autowired
    protected DepartmentTypeService departmentTypeService;  // 部门类型

    @GetMapping("/")
    @ApiOperation(value = "获取所有数据", notes = "获取所有数据（未排序 ）@author:panjie", nickname = "DepartmentType_getAll")
    public List<DepartmentType> getAll() {
        return departmentTypeService.findAll();
    }
}
