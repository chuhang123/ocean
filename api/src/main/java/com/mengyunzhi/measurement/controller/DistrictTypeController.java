package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DistrictTypeService;
import com.mengyunzhi.measurement.repository.DistrictType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-6-2.
 * 区域类型的C层
 */
@Api(tags = "DistrictType (区域类型)", description = "区域类型C层")
@RestController
@RequestMapping("/DistrictType")
public class DistrictTypeController {
    @Autowired
    private DistrictTypeService districtTypeService;

    @ApiOperation(value = "save (保存)", notes = "保存区域类型", nickname = "DistrictType_save")
    @PostMapping("/save")
    public DistrictType save(@ApiParam(value = "区域类型实体") @RequestBody DistrictType districtType) {
        //保存数据
        districtTypeService.save(districtType);
        return districtType;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有的区域", nickname = "DistrictType_getAll")
    @GetMapping("/getAll")
    public List<DistrictType> getAll() {

        return districtTypeService.getAll();
    }
}
