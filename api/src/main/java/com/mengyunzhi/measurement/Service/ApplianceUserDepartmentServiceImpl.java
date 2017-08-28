package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 器具用户Service实现
 * ApplianceUserService接口继承了DepartmentService接口,所以要实现DepartmentService接口的方法,故要继承DepartmentServiceImpl来避免代码冗余
 */
@Service
public class ApplianceUserDepartmentServiceImpl extends DepartmentServiceImpl implements ApplianceUserDepartmentService {
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
        DepartmentType departmentType = departmentTypeRepository.findOneByName("器具用户");
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }
}
