package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by lenovo on 2017/7/14.
 * 获取某个区域上的管理部门测试
 */
public class ManagementDepartmentServiceTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(ManagementDepartmentServiceTest.class.getName());
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ManagementDepartmentService managementDepartmentService;

    @Test
    public void getTopOneByDistrict() throws Exception {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        District district = new District();
        districtRepository.save(district);
        Department department = new Department();
        department.setDistrict(district);
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);

        logger.info("测试数据");
        Department newDepartment = managementDepartmentService.getTopOneByDistrict(district);
        logger.info("断言");
        assertThat(newDepartment.getId()).isEqualTo(department.getId());

        logger.info("删除测试数据");
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }
    @Test
    public void getTopOneByDistrictId() throws Exception{
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        District district = new District();
        districtRepository.save(district);
        Department department = new Department();
        department.setDistrict(district);
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);

        logger.info("测试数据");
        Department newDepartment = managementDepartmentService.getTopOneByDistrictId(district.getId());
        logger.info("断言");
        assertThat(newDepartment.getId()).isEqualTo(department.getId());

        logger.info("删除测试数据");
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }

}