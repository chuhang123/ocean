package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.MeasuringdeviceApplianceArchiveService;
import com.mengyunzhi.measurement.repository.MeasuringdeviceApplianceArchive;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
@Api(tags = "MeasuringdeviceApplianceArchive (器具产品)", description = "器具产品 器具生产企业档案")
@RestController
@RequestMapping("/MeasuringdeviceApplianceArchive")
public class MeasuringdeviceApplianceArchiveController {

    @Autowired
    private MeasuringdeviceApplianceArchiveService measuringdeviceApplianceArchiveService;

    //保存单条数据
    @ApiOperation(value = "save (保存)", notes = "保存器具产品实体", nickname = "MeasuringdeviceApplianceArchive_save")
    @PostMapping("/save")
    public MeasuringdeviceApplianceArchive save(@ApiParam(value = "器具实体") @RequestBody MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive) {

        //返回成功
        measuringdeviceApplianceArchiveService.save(measuringdeviceApplianceArchive);
        return measuringdeviceApplianceArchive;
    }

    //index界面显示所有数据
    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "CertifiedProduct_getAll")
    @GetMapping("/getAll")
    public List<MeasuringdeviceApplianceArchive> getAll() {
        return measuringdeviceApplianceArchiveService.getAll();
    }
}
