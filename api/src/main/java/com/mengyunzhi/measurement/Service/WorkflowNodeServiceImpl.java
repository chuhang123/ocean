package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WorkflowNode;

import com.mengyunzhi.measurement.repository.WorkflowNodeRepository;
import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 工作流结点
 */
@Service
public class WorkflowNodeServiceImpl implements WorkflowNodeService {
    /**
     * 通过菜单id获取当前用户符合的所有列表信息
     * 先通过当前菜单获取工作流类型，再根据工作流类型及当前登录用户获取工作流节点
     *
     * @param webAppMenuId
     * @return
     */
    @Autowired
    protected WebAppMenuRepository webAppMenuRepository;
    @Autowired
    protected ApplyTypeRepository applyTypeRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;

    @Override
    public List<WorkflowNode> getAllByWebAppMenuIdOfCurrentUser(Long webAppMenuId) {
        //获取前台菜单实体
        WebAppMenu webAppMenu = webAppMenuRepository.findOne(webAppMenuId);
        //获取菜单对应的申请类型
        ApplyType applyType = applyTypeRepository.findOneByWebAppMenuId(webAppMenuId);
        //获取申请类型对应的工作流类型
        WorkflowType workflowType = applyType.getWorkflowType();

        //获取当前登陆用户
        User user = userService.getCurrentLoginUser();

        //获取工作流节点
        List<WorkflowNode> workflowNodes =
                workflowNodeRepository.findAllByDistrictTypeAndWorkflowTypeAndDepartmentTypeAndPreWorkflowNodeIsNull(
                        user.getDepartment().getDistrict().getDistrictType(),
                        workflowType,
                        user.getDepartment().getDepartmentType());
        return workflowNodes;
    }

    /**
     * 通过前一工作流节点获取到下一工作流节点的全部信息
     * 先通过当前工作流节点获取到前一工作流节点，再根据前一个工作流节点获取到它的所有的下一个工作流节点的全部信息
     *
     * @param preWorkflowNodeId
     * @return list
     */
    public List<WorkflowNode> getAllByPreWorkflowNodeId(Long preWorkflowNodeId) {
        List<WorkflowNode> list = new ArrayList<WorkflowNode>();
        list = (List<WorkflowNode>) workflowNodeRepository.findAllByPreWorkflowNodeId(preWorkflowNodeId);
        return list;
    }

    @Override
    public WorkflowNode getTopOneByApplyTypeIdOfCurrentUser(Long applyTypeId) {
        ApplyType applyType = applyTypeRepository.findOne(applyTypeId);
        return this.getTopOneByWorkflowTypeOfCurrentUser(applyType.getWorkflowType());
    }

    @Override
    public WorkflowNode getTopOneByWorkflowTypeOfCurrentUser(WorkflowType workflowType) {
        User user = userService.getCurrentLoginUser();
        DepartmentType departmentType = user.getDepartment().getDepartmentType();
        DistrictType districtType = user.getDepartment().getDistrict().getDistrictType();
        return workflowNodeRepository.findTopOneByDepartmentTypeAndDistrictTypeAndWorkflowTypeAndPreWorkflowNodeIsNull(
                departmentType, districtType, workflowType
        );
    }

    @Override
    public WorkflowNode getOneCompleteWorkflowNode() {
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);
        return workflowNode;
    }
}
