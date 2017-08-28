package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.DeviceSetService;
import com.mengyunzhi.measurement.jsonView.DeviceSetJsonView;
import com.mengyunzhi.measurement.repository.DeviceSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
@RequestMapping("/DeviceSet")
@RestController
@Api(tags = "DeviceSet 计量标准装置", description = "author:panjie")
public class DeviceSetController {
    @Autowired
    protected DeviceSetService deviceSetService;    // 标准装置

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @PostMapping("/")
    @ApiOperation(value = "保存", notes = "计量标准装置实体", nickname = "DeviceSet_saveOfCurrentUser")
    public DeviceSet saveOfCurrentUser(@ApiParam(value = "计量标准装置") @RequestBody DeviceSet deviceSet) {
        deviceSetService.save(deviceSet);
        return deviceSet;
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @PutMapping("/updateDeviceInstrumentsById/{id}")
    @ApiOperation(value = "updateDeviceInstrumentsById(更新计量标准装置与装置授权检定项目的多对多关系)", notes = "更新计量标准装置与装置授权检定项目的多对多关系", nickname = "DeviceSet_updateDeviceInstrumentsById")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)  //设置返回的状态码
    @ApiResponse(code = 204, message = "修改成功")
    public void updateDeviceInstrumentsById(@ApiParam("ID") @PathVariable Long id, @ApiParam("计量标准装置") @RequestBody DeviceSet deviceSet) {
        deviceSetService.updateDeviceInstrumentsById(id, deviceSet);
        return;
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @GetMapping("/getAllDeviceSetByTechnicalInstitutionId/{technicalInstitutionId}")
    @ApiOperation(value = "getAllDeviceSetByTechnicalInstitutionId(通过技术机构ID获取标准装置列表)", notes = "通过技术机构ID获取标准装置列表", nickname = "DeviceSet_getAllDeviceSetByTechnicallnstitutionId")
    public List<DeviceSet> getAllDeviceSetByTechnicalInstitutionId(@ApiParam("technicalInstitutionId(技术机构ID)") @PathVariable Long technicalInstitutionId) {
        return deviceSetService.getAllDeviceSetByTechnicalInstitutionId(technicalInstitutionId);
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @GetMapping("/pageAllOfCurrentUser")
    @ApiOperation(value = "pageAllByCurrentUser(获取当前用户下的所有技术机构)", notes = "获取当前用户下的所有技术机构", nickname = "DeviceSet_pageAllOfCurrentUser")
    public Page<DeviceSet> pageAllByCurrentUser(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<DeviceSet> deviceSets = deviceSetService.pageAllOfCurrentUser(pageable);
        return deviceSets;
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @GetMapping("/pageAllOfCurrentUserBySpecification")
    @ApiOperation(value = "pageAllOfCurrentUserBySpecification(获取当前用户下的所有技术机构)", notes = "获取当前用户下的所有技术机构", nickname = "DeviceSet_pageAllOfCurrentUserBySpecification")
    public Page<DeviceSet> pageAllOfCurrentUserBySpecification(
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false, defaultValue = "0") Long districtId,
            @ApiParam(value = "部门id") @RequestParam(name = "departmentId", required = false, defaultValue = "0") Long departmentId,
            @ApiParam(value = "计量标准装置名称") @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @ApiParam(value = "代码") @RequestParam(name = "code", required = false, defaultValue = "") String code,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<DeviceSet> deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(districtId, departmentId, name, code, pageable);
        return deviceSets;
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @ApiOperation(value = "get (获取实体)", notes = "获取实体", nickname = "DeviceSet_get")
    @GetMapping("/{id}")
    public DeviceSet get(@ApiParam("id(计量标准装置id)") @PathVariable Long id) {
        return deviceSetService.get(id);
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @ApiOperation(value = "delete (删除实体)", notes = "删除实体", nickname = "DeviceSet_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam("id") @PathVariable Long id) {
        deviceSetService.delete(id);
        return;
    }

    @JsonView(DeviceSetJsonView.ToDeviceInstrument.class)
    @ApiOperation(value = "update (更新实体)", notes = "更新实体", nickname = "DeviceSet_update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@ApiParam(value = "计量标准装置id") @PathVariable Long id, @ApiParam(value = "计量标准装置实体") @RequestBody DeviceSet deviceSet) {
        deviceSetService.update(id, deviceSet);
        return;
    }
}
