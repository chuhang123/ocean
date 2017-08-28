package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.InstrumentCheckInfoService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentCheckInfoService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentService;
import com.mengyunzhi.measurement.jsonView.InstrumentCheckInfoJsonView;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfo;
import com.mengyunzhi.measurement.repository.MandatoryInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chuhang on 17-8-1.
 * 器具检定信息的C层
 */
@Api(tags = "InstrumentCheckInfo(器具检定信息)", description = "器具检定信息对应的C层")
@RestController
@RequestMapping("/InstrumentCheckInfo")
public class InstrumentCheckInfoController {
    @Autowired
    private MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;

    @ApiOperation(value = "save (保存)", notes = "保存器具检定信息", nickname = "InstrumentCheckInfo_save")
    @PostMapping("/save")
    public InstrumentCheckInfo save(@ApiParam(value = "器具检定信息实体") @RequestBody InstrumentCheckInfo instrumentCheckInfo) {
        //器具检定信息均属于强制器具检定信息，故直接保存在强制器具检定信息service中
        mandatoryInstrumentCheckInfoService.save(instrumentCheckInfo);

        return instrumentCheckInfo;
    }

    @ApiOperation(value = "delete", notes = "删除一条数据", nickname = "InstrumentCheckInfo_delete")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //删除成功返回状态码204
    public void delete(@ApiParam(value = "器具检定信息实体id") @PathVariable Long id) {
        //强制器具检定信息的删除
        mandatoryInstrumentCheckInfoService.delete(id);
        return;
    }

    @JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfCurrentUser (强检器具检定信息)", notes = "获取所有的强检器具检定信息", nickname = "InstrumentCheckInfo_pageAllOfCurrentUser")
    @GetMapping("/pageAllOfCurrentUser")
    public Page<InstrumentCheckInfo> pageAllOfCurrentUser(@ApiParam(value = "分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return mandatoryInstrumentCheckInfoService.pageAllOfCurrentUser(pageable);
    }

    @ApiOperation(value = "update", notes = "更新一条数据", nickname = "InstrumentCheckInfo_update")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@ApiParam(value = "ID") @PathVariable Long id, @ApiParam(value = "实体信息") @RequestBody InstrumentCheckInfo instrumentCheckInfo) {
        mandatoryInstrumentCheckInfoService.update(id, instrumentCheckInfo);
        return;
    }

    @ApiOperation(value = "pageAllByMandatoryInstrumentId", notes = "获取一个强检器具的所有检定信息", nickname = "InstrumentCheckInfo_pageAllByMandatoryInstrumentId")
    @GetMapping("/pageAllByMandatoryInstrumentId/{mandatoryInstrumentId}")
    public Page<InstrumentCheckInfo> pageAllOfMandatoryInstrumentId(@ApiParam("强检器具id") @PathVariable Long mandatoryInstrumentId, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return mandatoryInstrumentCheckInfoService.pageAllByMandatoryInstrumentId(mandatoryInstrumentId, pageable);
    }

    @JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfManagementDepartmentBySpecification", notes = "根据查询条件获取管理部门的强检器具鉴定信息", nickname = "MandatoryInstrument_pageAllOfManagementDepartmentBySpecification")
    @GetMapping("/pageAllOfManagementDepartmentBySpecification")
    public Page<InstrumentCheckInfo> pageAllOfManagementDepartmentBySpecification(
            @ApiParam(value = "年度") @RequestParam(name = "year", required = false) Integer year,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "检定机构id") @RequestParam(name = "checkDepartmentId", required = false) Long checkDepartmentId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentFirstLevelTypeId", required = false) Long instrumentFirstLevelTypeId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "证书编号") @RequestParam(name = "certificateNum", required = false) String certificateNum,
            @ApiParam(value = "检定结果id") @RequestParam(name = "checkResultId", required = false) Long checkResultId,
            @ApiParam(value = "准确度等级id") @RequestParam(name = "accuracyDisplayNameId", required = false) Long accuracyDisplayNameId,
            @ApiParam(value = "系统编号") @RequestParam(name = "mandatoryInstrumentId", required = false) Long mandatoryInstrumentId,
            @ApiParam(value = "强检器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(year, districtId, departmentId, checkDepartmentId, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, certificateNum, checkResultId, accuracyDisplayNameId, mandatoryInstrumentId, name, pageable);
    }

    @JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfMeasureUserBySpecification", notes = "根据查询条件获取器具用户下的强检器具鉴定信息", nickname = "MandatoryInstrument_pageAllOfMeasureUserBySpecification")
    @GetMapping("/pageAllOfMeasureUserBySpecification")
    public Page<InstrumentCheckInfo> pageAllOfMeasureUserBySpecification(
            @ApiParam(value = "年度") @RequestParam(name = "year", required = false) Integer year,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentFirstLevelTypeId", required = false) Long instrumentFirstLevelTypeId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "检定结果id") @RequestParam(name = "checkResultId", required = false) Long checkResultId,
            @ApiParam(value = "系统编号") @RequestParam(name = "mandatoryInstrumentId", required = false) Long mandatoryInstrumentId,
            @ApiParam(value = "强检器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentCheckInfoService.pageAllOfMeasureUserBySpecification(year, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId,  checkResultId, mandatoryInstrumentId, name, pageable);
    }

    @JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfTechnicalInstitutionDepartmentBySpecification", notes = "根据查询条件获取技术机构的强检器具鉴定信息", nickname = "MandatoryInstrument_pageAllOfTechnicalInstitutionDepartmentBySpecification")
    @GetMapping("/pageAllOfTechnicalInstitutionDepartmentBySpecification")
    public Page<InstrumentCheckInfo> pageAllOfTechnicalInstitutionDepartmentBySpecification(
            @ApiParam(value = "年度") @RequestParam(name = "year", required = false) Integer year,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentFirstLevelTypeId", required = false) Long instrumentFirstLevelTypeId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "证书编号") @RequestParam(name = "certificateNum", required = false) String certificateNum,
            @ApiParam(value = "检定结果id") @RequestParam(name = "checkResultId", required = false) Long checkResultId,
            @ApiParam(value = "准确度等级id") @RequestParam(name = "accuracyDisplayNameId", required = false) Long accuracyDisplayNameId,
            @ApiParam(value = "系统编号") @RequestParam(name = "mandatoryInstrumentId", required = false) Long mandatoryInstrumentId,
            @ApiParam(value = "强检器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentCheckInfoService.pageAllOfTechnicalInstitutionDepartmentBySpecification(year, districtId, departmentId, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, certificateNum, checkResultId, accuracyDisplayNameId, mandatoryInstrumentId, name, pageable);
    }


}
