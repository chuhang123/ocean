package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.WorkflowNodeService;
import com.mengyunzhi.measurement.repository.WorkflowNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 工作流结点
 */
@RestController
@RequestMapping("/WorkflowNode")
@Api(value = "WorkflowNodeController 工作流节点")
public class WorkflowNodeController {
    @Autowired private WorkflowNodeService workflowNodeService; // 工作流节点
    @ApiOperation(value = "getAllByWebAppMenuIdOfCurrentUser 通过菜单id获取当前用户符合的所有列表信息", notes = "先通过当前菜单获取工作流类型，再根据工作流类型及当前登录用户获取工作流节点", nickname = "WorkflowNode_getAllByWebAppMenuIdOfCurrentUser")
    @GetMapping("/getAllByWebAppMenuIdOfCurrentUser/{webAppMenuId}")
    public List<WorkflowNode> getAllByWebAppMenuIdOfCurrentUser(@Param("前台菜单ID") @PathVariable Long webAppMenuId) {
        return workflowNodeService.getAllByWebAppMenuIdOfCurrentUser(webAppMenuId);
    }

    @ApiOperation(value = "getAllByPreWorkflowNodeId 通过前一工作流节点获取当前工作流节点", notes = "先通过获取当前工作流节点对应的前一工作流节点id，然后通过前一工作流节点id获取到全部以该工作流节点为前一工作流节点的所有的工作流节点", nickname = "WorkflowNode_getAllByPreWorkflowNodeId")
    @GetMapping("/getAllByPreWorkflowNodeId/{preWorkflowNodeId}")
    public List<WorkflowNode> getAllByPreWorkflowNodeId(@Param("前一工作流节点id") @PathVariable Long preWorkflowNodeId)
    {
        return workflowNodeService.getAllByPreWorkflowNodeId(preWorkflowNodeId);
    }

    @ApiOperation(
            value = "getTopOneByApplyTypeIdOfCurrentUser 获取某个申请类型对应当前登录用户的首条工作流结点",
            notes = "要求在添加工作流结点时，首个工作流结点，对于某种用户，只能有一条适合的工作流结点。否则，无法确定工作流起点）",
            nickname = "WorkflowNode_getTopOneByApplyTypeIdOfCurrentUser")
    @GetMapping("/getTopOneByApplyTypeIdOfCurrentUser/{applyTypeId}")
    public WorkflowNode getTopOneByApplyTypeIdOfCurrentUser(@Param("申请类型id") @PathVariable Long applyTypeId) {
        return workflowNodeService.getTopOneByApplyTypeIdOfCurrentUser(applyTypeId);
    }
}
