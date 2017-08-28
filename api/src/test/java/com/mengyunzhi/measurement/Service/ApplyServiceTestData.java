package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 17-6-3.
 * 申请M层  测试数据
 */
@Component
public class ApplyServiceTestData {
    private final static Logger logger = Logger.getLogger(ApplyServiceTestData.class.getName());
    @Autowired
    WorkflowNodeRepository workflowNodeRepository;      // 工作流结点
    @Autowired
    DepartmentRepository departmentRepository;          // 部门
    @Autowired UserRepository userRepository;           // 用户
    @Autowired ApplyTypeService applyTypeService;       // 申请类型
    @Autowired ApplyTypeRepository applyTypeRepository; // 申请类型
    @Autowired UserService userService;                 // 用户
    @Autowired @org.springframework.beans.factory.annotation.Qualifier("ApplyService") private ApplyService applyService;
    public void saveWork(Work work) {
        logger.info("新建工作流节点");
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);
        work.setWorkflowNode(workflowNode);

        logger.info("新建部门");
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);
        work.setAuditDepartment(department);

        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);
        work.setAuditUser(user);

        logger.info("新建申请类型");
        ApplyType applyType = applyTypeService.getOneApplyType();
        applyTypeRepository.save(applyType);

        logger.info("新建申请");
        Apply apply = applyService.getOneApply();
        apply.setApplyType(applyType);

        userService.setCurrentLoginUser(user);
        work.setApply(apply);
    }
}