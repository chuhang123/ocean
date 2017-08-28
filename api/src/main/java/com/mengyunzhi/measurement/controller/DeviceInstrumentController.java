package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.DeviceInstrumentService;
import com.mengyunzhi.measurement.jsonView.DepartmentJsonView;
import com.mengyunzhi.measurement.jsonView.DeviceInstrumentJsonView;
import com.mengyunzhi.measurement.repository.DeviceInstrument;
import com.mengyunzhi.measurement.repository.DeviceSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目Controller
 */
@RestController
@RequestMapping("/DeviceInstrument")
public class DeviceInstrumentController {
    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;

    @ApiOperation(value = "save (保存)", notes = "保存授权检定项目实体", nickname = "DeviceInstrument_save")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    public void save(@ApiParam(value = "DeviceSet (表主装置实体)") @RequestBody DeviceSet deviceSet) {
        deviceInstrumentService.save(deviceSet);
        return;
    }

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageAllByCurrentUserOfDeviceInstrument (获取当前登录用户的所有授权检定项目)", notes = "获取当前登录用户的授权检定项目", nickname = "DeviceInstrument_pageAllByCurrentUserOfDeviceInstrument")
    @GetMapping("/pageAllByCurrentUserOfDeviceInstrument")
    public Page<?> pageAllByCurrentUserOfDeviceInstrument(@ApiParam(value = "分页信息") Pageable pageable) {
        return deviceInstrumentService.pageAllByCurrentUserOfDeviceInstrument(pageable);
    }

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageByDeviceSetIdOfCurrentUser", notes = "根据标准装置实体ID（传入0时，表示所有），并且是属于本部门的标准装置的", nickname = "DeviceInstrument_pageByDeviceSetIdOfCurrentUser")
    @GetMapping("/pageByDeviceSetIdOfCurrentUser/{deviceSetId}")
    public Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(@ApiParam("deviceSetId 标准装置ID") @PathVariable("deviceSetId")  Long deviceSetId, Pageable pageable) {
        return deviceInstrumentService.pageByDeviceSetIdOfCurrentUser(deviceSetId, pageable);
    }

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageAllOfCurrentUserBySpecification", notes = "授权检定项目综合查询", nickname = "DeviceInstrument_pageAllOfCurrentUserBySpecification")
    @GetMapping("/pageAllOfCurrentUserBySpecification")
    public Page<DeviceInstrument> pageAllOfCurrentUserBySpecification(
            @ApiParam(value = "标准装置id") @RequestParam(name = "deviceSetId", required = false) Long deviceSetId,
            @ApiParam(value = "部门id") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "标准装置名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(deviceSetId, districtId, departmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, name, pageable);
    }
}
