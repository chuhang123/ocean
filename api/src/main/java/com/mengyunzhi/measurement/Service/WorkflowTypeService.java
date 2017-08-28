package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WorkflowType;

/**
 * Created by panjie on 17/7/11.
 * 工作流类型
 */
public interface WorkflowTypeService {
    static WorkflowType getOneWorkflowType() {
        WorkflowType workflowType = new WorkflowType();
        workflowType.setName("测试工作流类型" + CommonService.getRandomStringByLength(10));
        return workflowType;
    }
}
