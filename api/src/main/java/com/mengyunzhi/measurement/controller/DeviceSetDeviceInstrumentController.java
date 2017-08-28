package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DeviceSetDeviceInstrumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by panjie on 17/7/19.
 * 标准装置授权检定项目
 */
@RestController
@RequestMapping("/DeviceSetDeviceInstrument")
@Api(tags = "DeviceSetDeviceInstrument 标准装置授权检定项目")
public class DeviceSetDeviceInstrumentController {
    @Autowired
    private DeviceSetDeviceInstrumentService deviceSetDeviceInstrumentService;   // 计量标准装置：授权检定项目

    @GetMapping("/countByAccuracyIdAndMeasureScaleIdAndDepartmentId/accuracyId/{accuracyId}/measureScaleId/{measureScaleId}/departmentId/{departmentId}")
    @ApiOperation(value = "countByAccuracyIdAndMeasureScaleIdAndDepartmentId 获取某部门拥有某个器具种类的可检定数",
            notes = "获取某个技术机构（器具用户）是否拥有检测某器具种类的能力（大于1则说明拥有检测能力）",
            nickname = "DeviceSetDeviceInstrument_countByAccuracyIdAndMeasureScaleIdAndDepartmentId")
    public int countByAccuracyIdAndMeasureScaleIdAndDepartmentId(@PathVariable("accuracyId") @ApiParam("精度ID") Long accuracyId,
                                                                 @PathVariable("measureScaleId") @ApiParam("测量范围ID") Long measureScaleId,
                                                                 @PathVariable("departmentId") @ApiParam("技术机构（建标用户）ID") Long departmentId) {
        return deviceSetDeviceInstrumentService.countByAccuracyIdAndMeasureScaleIdAndDepartmentId(accuracyId, measureScaleId, departmentId);
    }

    @ApiOperation(value = "delete (删除实体)", notes = "删除实体", nickname = "deviceSetDeviceInstrument_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/accuracyId/{accuracyId}/measureScaleId/{measureScaleId}/deviceSetId/{deviceSetId}")
    public void delete(@PathVariable("accuracyId") @ApiParam("精度ID") Long accuracyId,
                                   @PathVariable("measureScaleId") @ApiParam("测量范围ID") Long measureScaleId,
                                   @PathVariable("deviceSetId") @ApiParam("计量标准装置实体ID") Long deviceSetId) {

        deviceSetDeviceInstrumentService.delete(accuracyId, measureScaleId, deviceSetId);
        return;
    }
}
