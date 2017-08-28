package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by panjie on 17/7/28.
 */
@Component
public class WorkflowNodeServiceTestData {
    private static final Logger logger = Logger.getLogger(WorkflowNodeServiceTestData.class.getName());
    @Autowired
    UserService userService; // 用户
    @Autowired
    WorkflowTypeService workflowTypeService;
    @Autowired
    WorkflowTypeRepository workflowTypeRepository;  // 工作流类型
    @Autowired
    ApplyTypeRepository applyTypeRepository;    // 申请类型
    @Autowired
    WorkflowNodeRepository workflowNodeRepository;   // 工作流结点

    public void getTopOneByApplyTypeIdOfCurrentUser(User user, ApplyType applyType, WorkflowNode workflowNode) {
        logger.info("新建一个工作流类型");
        WorkflowType workflowType = WorkflowTypeService.getOneWorkflowType();
        workflowTypeRepository.save(workflowType);
        applyType.setWorkflowType(workflowType);
        applyTypeRepository.save(applyType);

        logger.info("设置工作的3项信息");
        workflowNode.setWorkflowType(workflowType);
        workflowNode.setDistrictType(user.getDepartment().getDistrict().getDistrictType());
        workflowNode.setDepartmentType(user.getDepartment().getDepartmentType());
        workflowNodeRepository.save(workflowNode);
    }
}
