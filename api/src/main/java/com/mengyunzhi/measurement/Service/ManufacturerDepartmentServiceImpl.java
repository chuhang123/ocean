package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 生产企业service实现
 */
@Service
public class ManufacturerDepartmentServiceImpl extends DepartmentServiceImpl implements ManufacturerDepartmentService {
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
        DepartmentType departmentType = departmentTypeRepository.findOneByName("生产企业");
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }
}
