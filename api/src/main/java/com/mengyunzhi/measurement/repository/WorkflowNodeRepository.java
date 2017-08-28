package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liming on 17-5-22.
 */
public interface WorkflowNodeRepository extends CrudRepository<WorkflowNode, Long> {
    WorkflowNode findTopOneByName(String name);

    List<WorkflowNode> findAllByPreWorkflowNodeId(Long preWorkflowNodeId);

    //通过区域类型,工作流类型, 部门类型获取工作流节点里列表
    List<WorkflowNode> findAllByDistrictTypeAndWorkflowTypeAndDepartmentType(DistrictType districtType, WorkflowType workflowType, DepartmentType departmentType);

    //通过区域类型,工作流类型, 部门类型获取工作流节点里列表
    List<WorkflowNode> findAllByDistrictTypeAndWorkflowTypeAndDepartmentTypeAndPreWorkflowNode(DistrictType districtType, WorkflowType workflowType, DepartmentType departmentType, WorkflowNode preWorkflowNode);

    List<WorkflowNode> findAllByDistrictTypeAndWorkflowTypeAndDepartmentTypeAndPreWorkflowNodeIsNull(DistrictType districtType, WorkflowType workflowType, DepartmentType departmentType);

    // 根据工作流类型查找首结点
    WorkflowNode findTopOneByWorkflowTypeAndPreWorkflowNodeIsNull(WorkflowType workflowType);
    // 根据工作流类型 部门类型 区域类型 查找首结点
    WorkflowNode findTopOneByDepartmentTypeAndDistrictTypeAndWorkflowTypeAndPreWorkflowNodeIsNull(
            DepartmentType departmentType,
            DistrictType districtType,
            WorkflowType workflowType);

    WorkflowNode findTopOneByDepartmentTypeAndDistrictTypeAndWorkflowType(
            DepartmentType departmentType,
            DistrictType districtType,
            WorkflowType workflowType);

}
