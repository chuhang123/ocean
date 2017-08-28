package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WorkflowNode;
import com.mengyunzhi.measurement.repository.WorkflowType;

import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 工作流节点
 */
public interface WorkflowNodeService {
    List<WorkflowNode> getAllByWebAppMenuIdOfCurrentUser(Long webAppMenuId);
    List<WorkflowNode> getAllByPreWorkflowNodeId(Long preWorkflowNodeId);

    static WorkflowNode getOneWorkflowNode() {
        WorkflowNode workflowNode = new WorkflowNode();
        return workflowNode;
    }
    // 获取符合当前登录用户的对应当前申请类型的首条工作流结点
    WorkflowNode getTopOneByApplyTypeIdOfCurrentUser(Long applyTypeId);
    WorkflowNode getTopOneByWorkflowTypeOfCurrentUser(WorkflowType workflowType);
    WorkflowNode getOneCompleteWorkflowNode();
}
