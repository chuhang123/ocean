package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
@Service
public class DepartmentTypeServiceImpl implements DepartmentTypeService {
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository; // 部门类型

    /**
     * 获取所有数据
     * @return List
     * @author panjie
     */
    @Override
    public List<DepartmentType> findAll() {
        return (List<DepartmentType>) departmentTypeRepository.findAll();
    }

    @Override
    public DepartmentType save(DepartmentType departmentType) {
        departmentTypeRepository.save(departmentType);
        return departmentType;
    }
}
