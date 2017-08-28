package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.OptionalIntergratedService;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-4-26.
 */
@Api(tags = "OptionalIntergrated (非强检器具)", description = "非强检器具 综合息查询")
@RestController
@RequestMapping("/OptionalIntergrated")
public class OptionalIntergratedController {

    @Autowired
    private OptionalIntergratedService optionalIntergratedService;

    @ApiOperation(value = "save (保存)", notes = "保存非强检器具实体", nickname = "OptionalIntergrated_save")
    @PostMapping("/save")
    public OptionalIntegrated save(@ApiParam(value = "非强检器具实体") @RequestBody OptionalIntegrated optionalIntegrated) {

        //保存数据
        optionalIntergratedService.save(optionalIntegrated);
        return optionalIntegrated;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "OptionalIntergrated_getAll")
    @GetMapping("/getAll")
    public List<OptionalIntegrated> getAll() {
        return optionalIntergratedService.getAll();
    }
}