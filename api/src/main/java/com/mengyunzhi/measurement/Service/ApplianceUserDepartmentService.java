package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 器具用户Service
 */
public interface ApplianceUserDepartmentService extends DepartmentService {
    List<Department> getTop10ByNameContaining(String name);
}
