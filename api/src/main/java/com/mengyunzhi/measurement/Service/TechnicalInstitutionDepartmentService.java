package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 技术机构Service
 */
public interface TechnicalInstitutionDepartmentService extends DepartmentService {
    List<Department> getTop10ByNameContaining(String name);
}
