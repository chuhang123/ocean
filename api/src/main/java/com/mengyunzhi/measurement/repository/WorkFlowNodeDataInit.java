package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 工作流结点初始化数据
 */
@Component
public class WorkFlowNodeDataInit  extends ApiInitDataListener {
    private Logger logger = Logger.getLogger(WorkFlowNodeDataInit.class.getName());
    @Autowired protected WorkflowTypeDataInit workflowTypeDataInit;             // 工作流类型数据初始化
    @Autowired protected WorkflowTypeRepository workflowTypeRepository;         // 工作流类型
    @Autowired protected DistrictTypeRepository districtTypeRepository;         // 区域类型
    @Autowired protected DepartmentTypeRepository departmentTypeRepository;     // 部门类型
    @Autowired protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("添加工流流结点初始化数据");
        List<WorkflowNode> workflowNodes = (List<WorkflowNode>) workflowNodeRepository.findAll();
        if (workflowNodes.size() == 0) {
            WorkflowType workflowType = workflowTypeRepository.findTopOneByName("适用于器具用户新强检器具审批");
            DistrictType countyDistrictType = districtTypeRepository.findOneByName("区\\县");
            DistrictType cityDistrictType = districtTypeRepository.findOneByName("市");
            DistrictType provinceDistrictType = districtTypeRepository.findOneByName("省");
            DepartmentType adminDepartmentType = departmentTypeRepository.findOneByName("管理部门");
            DepartmentType technicalInstitutionDepartmentType = departmentTypeRepository.findOneByName("技术机构");
            DepartmentType ApplianceUserDepartmentType = departmentTypeRepository.findOneByName("器具用户");

            WorkflowNode workflowNode = new WorkflowNode();
            workflowNode.setDistrictType(countyDistrictType);
            workflowNode.setWorkflowType(workflowType);
            workflowNode.setDepartmentType(ApplianceUserDepartmentType);
            workflowNode.setName("区县级器具用户");
            workflowNodeRepository.save(workflowNode);

            WorkflowNode workflowNode0 = new WorkflowNode();
            workflowNode0.setDistrictType(countyDistrictType);
            workflowNode0.setWorkflowType(workflowType);
            workflowNode0.setDepartmentType(adminDepartmentType);
            workflowNode0.setName("区县级管理部门");
            workflowNode0.setPreWorkflowNode(workflowNode);
            workflowNodeRepository.save(workflowNode0);

            WorkflowNode workflowNode1 = new WorkflowNode();
            workflowNode1.setDistrictType(countyDistrictType);
            workflowNode1.setDepartmentType(technicalInstitutionDepartmentType);
            workflowNode1.setPreWorkflowNode(workflowNode0);
            workflowNode1.setName("区县级属技术机构");
            workflowNode1.setWorkflowType(workflowType);
            workflowNodeRepository.save(workflowNode1);

            WorkflowNode workflowNode2 = new WorkflowNode();
            workflowNode2.setDepartmentType(adminDepartmentType);
            workflowNode2.setDistrictType(cityDistrictType);
            workflowNode2.setPreWorkflowNode(workflowNode0);
            workflowNode2.setName("市级管理部门");
            workflowNode2.setContainSonDistrict(false);
            workflowNode2.setWorkflowType(workflowType);
            workflowNodeRepository.save(workflowNode2);

            WorkflowNode workflowNode3 = new WorkflowNode();
            workflowNode3.setDistrictType(cityDistrictType);
            workflowNode3.setDepartmentType(technicalInstitutionDepartmentType);
            workflowNode3.setName("市属技术机构");
            workflowNode3.setWorkflowType(workflowType);
            workflowNode3.setContainSonDistrict(true);
            workflowNode3.setPreWorkflowNode(workflowNode2);
            workflowNodeRepository.save(workflowNode3);

            WorkflowNode workflowNode4 = new WorkflowNode();
            workflowNode4.setDistrictType(provinceDistrictType);
            workflowNode4.setDepartmentType(adminDepartmentType);
            workflowNode4.setPreWorkflowNode(workflowNode2);
            workflowNode4.setName("省级管理部门");
            workflowNode4.setWorkflowType(workflowType);
            workflowNodeRepository.save(workflowNode4);

            WorkflowNode workflowNode5 = new WorkflowNode();
            workflowNode5.setDistrictType(provinceDistrictType);
            workflowNode5.setDepartmentType(technicalInstitutionDepartmentType);
            workflowNode5.setPreWorkflowNode(workflowNode4);
            workflowNode5.setName("省属技术机构");
            workflowNode5.setContainSonDistrict(true);
            workflowNode5.setWorkflowType(workflowType);
            workflowNodeRepository.save(workflowNode5);
        }
    }

    @Override
    public int getOrder() {
        // 基于工作流类型
        return workflowTypeDataInit.getOrder() + 10;
    }
}
