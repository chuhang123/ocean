package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 管理部门Service实现
 */
@Service
public class ManagementDepartmentServiceImpl extends DepartmentServiceImpl implements ManagementDepartmentService {
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    /**
     * @param name
     * @return array
     * 通过部门的名字模糊查询出前10条数据
     */
    @Override
    public List<Department> getTop10ByNameContaining(String name) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }

    @Override
    public Department getTopOneByDistrict(District district) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        Department department = departmentRepository.findTopOneByDistrictAndDepartmentType(district, departmentType);
        return department;
    }

    @Override
    public Department getTopOneByDistrictId(Long districtId) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        Department department = departmentRepository.findTopOneByDistrictIdAndDepartmentType(districtId, departmentType);
        return department;
    }
}
