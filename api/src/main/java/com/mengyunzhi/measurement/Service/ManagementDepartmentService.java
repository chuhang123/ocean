package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.District;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 管理部门Service
 */
public interface ManagementDepartmentService extends DepartmentService{
    List<Department> getTop10ByNameContaining(String name);
    Department getTopOneByDistrict(District district); // 获取某个区域的管理部门
    Department getTopOneByDistrictId(Long districtId); // 获取某个区域的管理部门
}