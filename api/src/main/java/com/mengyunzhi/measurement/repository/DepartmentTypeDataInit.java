package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * Created by panjie on 17/7/25.
 * 部门类型数据初始化
 */
@Component
public class DepartmentTypeDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(DepartmentTypeDataInit.class.getName());
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;      // 部门类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DepartmentType> departmentTypes1 = (List<DepartmentType>) departmentTypeRepository.findAll();
        if (departmentTypes1.size() == 0) {
            logger.info("----- 添加部门类型  -----");
            HashSet<DepartmentType> departmentTypes = new HashSet<DepartmentType>();
            departmentTypes.add(new DepartmentType("管理部门", "guanlibumen", System.currentTimeMillis(), System.currentTimeMillis(), null));
            departmentTypes.add(new DepartmentType("技术机构", "jishujigou", 0L, 0L, null));
            departmentTypes.add(new DepartmentType("器具用户", "qijuyonghu", 0L, 0L, null));
            departmentTypes.add(new DepartmentType("生产企业", "shengchanqiye", 0L, 0L, null));
            departmentTypeRepository.save(departmentTypes);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
