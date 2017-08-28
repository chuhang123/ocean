package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.OptionalCheckDetailService;
import com.mengyunzhi.measurement.repository.OptionalCheckDetail;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/5/10.
 * 非强检器具-检定信息查询
 */
@Api(tags = "OptionalCheckDetail (非强检器具)", description = "非强检器具 检定信息查询")
@RestController
@RequestMapping("/OptionalCheckDetail")
public class OptionalCheckDetailController {
    @Autowired
    private OptionalCheckDetailService optionalCheckDetailService;

    @ApiOperation(value = "save (保存)", notes = "保存非强检器具实体", nickname = "OptionalCheckDetail_save")
    @PostMapping("/save")
    public OptionalCheckDetail save(@ApiParam(value = "非强检器具实体") @RequestBody OptionalCheckDetail optionalCheckDetail)
    {
        //保存数据
        optionalCheckDetail = optionalCheckDetailService.save(optionalCheckDetail);
        return optionalCheckDetail;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "CertifiedProduct_getAll")
    @GetMapping("/getAll")
    public List<OptionalCheckDetail> getAll() {

        //获取全部数据
        return optionalCheckDetailService.getAll();
    }

    @ApiOperation(value = "getAllByOptionalIntergrated(获取检定信息)", notes = "根据OptionalIntergrated获取非强检器具相应的检定信息", nickname = "OptionalCheckDetail_getAllByOptionalIntergrated")
    @PostMapping("/getAllByOptionalIntergrated")
    public List<OptionalCheckDetail> getAllByOptionalIntergrated (@RequestBody OptionalIntegrated optionalIntegrated) {
        return optionalCheckDetailService.getAllByOptionalIntergrated(optionalIntegrated);
    }
}
