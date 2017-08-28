package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-7-9.
 * 器具用户Service测试文件
 */
public class ApplianceUserServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(ApplianceUserServiceTest.class.getName());
    private DepartmentType departmentType;
    private Set<Department> departments = new HashSet<>();
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ApplianceUserDepartmentService applianceUserService;
    @Before public void  addData() {
        departmentType  = departmentTypeRepository.findOneByName("器具用户");
        logger.info("添加20条数据");
        for (Integer i = 0; i < 20; i++) {
            Department department = new Department();
            department.setName(i.toString() + "abc" + i.toString());
            department.setDepartmentType(departmentType);
            departments.add(department);
        }
        departmentRepository.save(departments);
    }
    @After public void deleteData() {
        logger.info("删除数据");
        departmentRepository.delete(departments);
    }
    @Test
    public void getTop10ByNameContaining() throws Exception {
        logger.info("查询数据");
        List<Department> list = applianceUserService.getTop10ByNameContaining("abc");
        logger.info("断言查处来的数据是10条");
        assertThat(list.size()).isEqualTo(10);
    }

}