package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.InstrumentProductionService;
import com.mengyunzhi.measurement.jsonView.InstrumentTypeJsonView;
import com.mengyunzhi.measurement.repository.InstrumentProduction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liming on 17-7-6.
 * 器具生产信息的C层
 */
@Api(tags = "InstrumentProduction (器具生产信息)", description = "器具生产信息C层")
@RestController
@RequestMapping("/InstrumentProduction")
public class InstrumentProductionController {
    @Autowired
    private InstrumentProductionService instrumentProductionService;

    @JsonView(InstrumentTypeJsonView.Base.class)
    @ApiOperation(value = "getByDepartmentIdAndLicenseNum", notes = "通过部门ID及许可证号模糊查询器具生产信息", nickname = "InstrumentProduction_getByDepartmentIdAndLicenseNum")
    @GetMapping("/getByDepartmentIdAndLicenseNum/{departmentId}/{licenseNum}")
    public List<InstrumentProduction> getByDepartmentIdAndLicenseNum(@ApiParam(value = "部门ID") @PathVariable Long departmentId, @ApiParam(value = "许可证号") @PathVariable String licenseNum) {
        return instrumentProductionService.findTop10ByDepartmentIdAndLicenseNum(departmentId, licenseNum);
    }
}
