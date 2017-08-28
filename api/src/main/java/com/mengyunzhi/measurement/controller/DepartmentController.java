package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.DepartmentService;
import com.mengyunzhi.measurement.jsonView.DepartmentJsonView;
import com.mengyunzhi.measurement.repository.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/7/15.
 */
@RestController
@RequestMapping("/Department")
@Api(tags = "Department 部门", description = "部门：包括器具用户 生产企业 管理部门 技术机构四种部门类型")
public class DepartmentController {
    private Logger logger = Logger.getLogger(DepartmentController.class.getName());
    @Autowired @Qualifier("DepartmentService")
    DepartmentService departmentService;        // 部门

    /**
     * 获取当前登录用户的某一工作流结点上的部门列表
     * 使用场景：审核过程中，选择某个工作流结点上对应的审核部门列表
     * @param workflowNodeId 工作流节点ID
     * @return
     * @author panjie
     */
    @GetMapping("/getAllByWorkflowNodeIdOfCurrentLoginUser/{workflowNodeId}")
    @ApiOperation(value = "getAllByWorkflowNodeIdOfCurrentLoginUser", nickname = "Department_getAllByWorkflowNodeIdOfCurrentLoginUser")
    public List<Department> getAllByWorkflowNodeIdOfCurrentLoginUser(@ApiParam("工作流结点") @PathVariable Long workflowNodeId){
        return departmentService.getAllByWorkflowNodeIdOfCurrentLoginUser(workflowNodeId);
    }

    @GetMapping("/getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser/workflowNodeId/{workflowNodeId}/mandatoryInstrumentId/{mandatoryInstrumentId}")
    @ApiOperation(value = "getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser", nickname = "Department_getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser")
    public List<Department> getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(
            @ApiParam("工作流结点") @PathVariable("workflowNodeId") Long workflowNodeId,
            @ApiParam("强检器具ID") @PathVariable("mandatoryInstrumentId") Long mandatoryInstrumentId) {
        List<Department> departments = departmentService.getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(workflowNodeId, mandatoryInstrumentId);
        return departments;
    }

    @GetMapping("/getAllTechnicalInstitutionsByDistrictId/{districtId}")
    @ApiOperation(value = "getAllTechnicalInstitutionsByDistrictId 获取某个区域下的所有技术机构",
    notes = "获取某个区域下的所有技术机构", nickname = "Department_getAllTechnicalInstitutionsByDistrictId")
    public List<Department> getAllTechnicalInstitutionsByDistrictId(@ApiParam("区域ID") @PathVariable Long districtId) {
        return departmentService.getAllTechnicalInstitutionsByDistrictId(districtId);
    }

    @GetMapping("/getAllInstrumentUserByDistrictId/{districtId}")
    @ApiOperation(value = "getAllInstrumentUserByDistrictId 获取某个区域下的所有器具用户",
    notes = "获取某个区域下的所有器具用户",
    nickname = "Department_getAllInstrumentUserByDistrictId")
    @JsonView(DepartmentJsonView.Base.class)
    public List<Department> getAllInstrumentUserByDistrictId(@ApiParam("区域ID") @PathVariable Long districtId) {
        return departmentService.getAllInstrumentUserByDistrictId(districtId);
    }

    @JsonView(DepartmentJsonView.OnlyPartentDistrict.class)
    @GetMapping("/pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts/{departmentTypePinyin}")
    @ApiOperation(value = "pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts",
            notes = "例：获取某个管理部门所辖区域内所有的器具用户（departmentTypePinyin: qijuyonghu）",
            nickname = "Department_pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts")
    public Page<Department> pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(@ApiParam("departmentTypePinyin 部门类型对应的拼音") @PathVariable("departmentTypePinyin") String departmentTypePinyin, @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return departmentService.pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(departmentTypePinyin, pageable);
    }

    @ApiOperation(value = "delete", notes = "删除一条数据", nickname = "Department_delete")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //删除成功返回状态码204
    public void delete(@ApiParam(value = "部门实体id") @PathVariable Long id) {
        //强检器具使用信息的删除
        departmentService.delete(id);
        return;
    }
}
