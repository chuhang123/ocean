package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by panjie on 17/7/6.
 * 申请类型
 */
@Service
public class ApplyTypeServiceImpl implements ApplyTypeService {
    @Autowired DistrictTypeService districtTypeService;     // 区域类型
    @Autowired DepartmentTypeService departmentTypeService; // 部门类型
    @Autowired WebAppMenuService webAppMenuService;         // 前台菜单
    @Autowired WorkflowTypeService workflowTypeService;                   // 工作流类型
    @Autowired ApplyTypeRepository applyTypeRepository;     // 申请类型
    @Autowired WorkflowTypeRepository workflowTypeRepository;
    @Override
    public ApplyType getOneApplyType() {
        WebAppMenu webAppMenu = WebAppMenuService.getOneWebAppMenu();
        webAppMenuService.save(webAppMenu);
        WorkflowType workflowType = WorkflowTypeService.getOneWorkflowType();
        workflowTypeRepository.save(workflowType);

        ApplyType applyType = new ApplyType();
        applyType.setWebAppMenu(webAppMenu);
        applyType.setWorkflowType(workflowType);
        return applyType;
    }

    @Override
    public ApplyType getOneByWebAppMenuId(Long webAppMenuId) {
        return applyTypeRepository.findOneByWebAppMenuId(webAppMenuId);
    }
}
