package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 技术机构Service实现
 */
@Service
public class TechnicalInstitutionDepartmentServiceImpl extends DepartmentServiceImpl implements TechnicalInstitutionDepartmentService {
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    /**
     * @param name
     * @return array
     * 通过部门的名字模糊查询出前10条数据
     */
    @Override
    public List<Department> getTop10ByNameContaining(String name) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }
}
