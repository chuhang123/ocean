package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 工作流类型数据初始化
 */
@Component
public class WorkflowTypeDataInit extends ApiInitDataListener {
    private Logger logger = Logger.getLogger(WorkflowTypeDataInit.class.getName());
    @Autowired protected ApiInitDataListener apiInitDataListener;   // 基础数据初始化
    @Autowired protected WorkflowTypeRepository workflowTypeRepository; // 工作流类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("工作流类型数据初始化");
        List<WorkflowType> workflowTypes = (List<WorkflowType>) workflowTypeRepository.findAll();
        if (workflowTypes.size() == 0) {
            WorkflowType workflowType = new WorkflowType();
            workflowType.setName("适用于器具用户新强检器具审批");
            workflowType.setDescription("1.向上级管理部门提出申请；2.管理部门可以转给同区域或子区域的技术机构。3.技术机构可办结可返回给管理部门");
            workflowTypeRepository.save(workflowType);
        }
    }

    @Override
    public int getOrder() {
        return apiInitDataListener.getOrder() + 10;
    }
}
