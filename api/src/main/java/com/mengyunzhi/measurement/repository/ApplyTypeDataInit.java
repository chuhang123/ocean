package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 安强 on 2017/6/16.
 * 申请类型
 */
@Component
public class ApplyTypeDataInit extends ApiInitDataListener{
    private Logger logger = Logger.getLogger(ApplyTypeDataInit.class.getName());

    @Autowired
    private ApplyTypeRepository applyTypeRepository;
    @Autowired
    protected WorkflowTypeDataInit workflowTypeDataInit;
    @Autowired DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired DistrictTypeRepository districtTypeRepository;       // 区域类型
    @Autowired WebAppMenuRepository webAppMenuRepository;           // 前台菜单
    @Autowired WorkflowTypeRepository workflowTypeRepository;       // 工作流类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("----- 添加适用于器具用户申请添加强检器具的申请类型 -----");
        List<ApplyType> applyTypeList = (List<ApplyType>) applyTypeRepository.findAll();
        if (applyTypeList.size() == 0) {
            logger.info("获取前台菜单添加强检器具的菜单");
            WebAppMenu webAppMenu = webAppMenuRepository.findOneByRouteName("IntegratedAdd");

            logger.info("获取对应的工作流类型");
            WorkflowType workflowType = workflowTypeRepository.findTopOneByName("适用于器具用户新强检器具审批");

            ApplyType applyType = new ApplyType();
            applyType.setWebAppMenu(webAppMenu);
            applyType.setName("区\\县器具用户新增器具");
            applyType.setWorkflowType(workflowType);
            applyTypeRepository.save(applyType);
        }
    }

    /**
     * 申请类型数据独立
     * @return
     */
    @Override
    public int getOrder() {
        // 基于工作流类型
        return workflowTypeDataInit.getOrder() + 10;
    }
}
