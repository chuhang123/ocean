package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ManufacturerDepartmentService;
import com.mengyunzhi.measurement.repository.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 生产企业
 */
@RestController
@RequestMapping("/ManufacturerDepartment")
@Api(tags = "ManufacturerDepartment 生产企业", description = "author:panjie")
public class ManufacturerDepartmentController {
    @Autowired
    private ManufacturerDepartmentService manufacturerDepartmentService;
    @GetMapping("/getTop10ByNameContaining/{name}")
    @ApiOperation(value = "getTop10ByNameContaining 获取包含某个名称的前10条生产企业数据", nickname = "ManufacturerDepartment_getTop10ByNameContaining")
    public List<Department> getTop10ByNameContaining(@PathVariable String name) {
        return manufacturerDepartmentService.getTop10ByNameContaining(name);
    }
}
