package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/7/6.
 * 部门
 */
public interface DepartmentService {
    List<Department> getTop10ByDepartmentTypeIdAndNameContaining(Long DepartmentTypeId, String name);
    // 取出某区域某类型下的所有 部门列表（包含子部门）
    List<Department> getAllByDistrictAndDepartmentTypeIncludeSons(District district, DepartmentType departmentType);
    //取出某个区域某个部门类型所有部门类型信息
    List<Department> getAllByDistrictAndDepartmentType(District district, DepartmentType departmentType);

    static Department getOneDepartment() {
        Department department = new Department();
        department.setName("测试部门信息" + CommonService.getRandomStringByLength(10));
        return department;
    }

    // 获取一个完整的部门
    Department getOneCompleteDepartment();
    List<Department> getAllByWorkflowNodeIdOfCurrentLoginUser(Long workflowNodeId)  throws ObjectNotFoundException;

    List<Department> getAllByWorkflowNodeAndUser(WorkflowNode workflowNode, User user);

    List<Department> getAllByWorkflowNodeOfCurrentLoginUser(WorkflowNode workflowNode);
    // 获取符合当前工作流的所有部门信息，同时，添加是否有传入器具的检定能力
    List<Department> getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(Long workflowNodeId, Long mandatoryInstrumentId) throws ObjectNotFoundException;
    List<Department> getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(WorkflowNode workflowNode, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException;

    // 获取某个区域下的所有技术机构
    List<Department> getAllTechnicalInstitutionsByDistrictId(Long districtId);
    // 获取一个技术机构
    Department getOneTechnicalInstitutionDepartment();
    // 获取当前用户所辖区域内 某部门类型拼音 的所有部门
    Page<Department> pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(String departmentTypePinyin, Pageable pageable) throws SecurityException;
    //删除功能实现
    void delete(Long id);

    // 获取某个区域下的所有的器具用户
    List<Department> getAllInstrumentUserByDistrictId(Long districtId);
}
