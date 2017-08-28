package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by panjie on 17/7/16.
 * 部门数据初始化
 */
@Component
public class DepartmentDataInit extends ApiInitDataListener {
    private Logger logger = Logger.getLogger(DepartmentDataInit.class.getName());
    @Autowired protected DistrictDataInit districtDataInit; // 区域
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired protected DistrictRepository districtRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Department> departments1 = (List<Department>) departmentRepository.findAll();
        if (departments1.size() == 0) {
            logger.info("----- 添加部门  -----");
            HashSet<Department> departments = new HashSet<Department>();

            logger.info("----- 添加管理部门  -----");
            Department hebeiguanliDepartment = new Department();
            hebeiguanliDepartment.setDepartmentType(departmentTypeRepository.getByName("管理部门"));
            hebeiguanliDepartment.setDistrict(districtRepository.getByName("河北省"));
            hebeiguanliDepartment.setName("河北省管理部门");
            hebeiguanliDepartment.setPinyin("hebeishengguanlibumen");
            departments.add(hebeiguanliDepartment);


            Department langfangDepartment = new Department();
            langfangDepartment.setDepartmentType(departmentTypeRepository.getByName("管理部门"));
            langfangDepartment.setDistrict(districtRepository.getByName("廊坊市"));
            langfangDepartment.setName("廊坊市管理部门");
            langfangDepartment.setPinyin("langfangshiguanlibumen");
            departments.add(langfangDepartment);

            Department testManamentDepartment = new Department();
            testManamentDepartment.setDepartmentType(departmentTypeRepository.getByName("管理部门"));
            testManamentDepartment.setDistrict(districtRepository.getByName("测试市"));
            testManamentDepartment.setName("测试市管理部门");
            testManamentDepartment.setPinyin("ceshishiguanlibumeng");
            departments.add(testManamentDepartment);

            Department anciguanliDepartment = new Department();
            anciguanliDepartment.setDepartmentType(departmentTypeRepository.getByName("管理部门"));
            anciguanliDepartment.setDistrict(districtRepository.getByName("安次区"));
            anciguanliDepartment.setName("安次区管理部门");
            anciguanliDepartment.setPinyin("anciquguanlibumen");
            departments.add(anciguanliDepartment);

            Department testCityManamentDepartment = new Department();
            testCityManamentDepartment.setDepartmentType(departmentTypeRepository.getByName("管理部门"));
            testCityManamentDepartment.setDistrict(districtRepository.getByName("测试区县"));
            testCityManamentDepartment.setName("测试区县管理部门");
            testCityManamentDepartment.setPinyin("ceshiquxianguanlibumeng");
            departments.add(testCityManamentDepartment);

            logger.info("----- 添加技术机构  -----");
            Department quxianjishujigou = new Department();
            quxianjishujigou.setDepartmentType(departmentTypeRepository.getByName("技术机构"));
            quxianjishujigou.setDistrict(districtRepository.getByName("安次区"));
            quxianjishujigou.setName("安次区技术机构");
            quxianjishujigou.setPinyin("anciqujishujigou");
            departments.add(quxianjishujigou);

            Department shijishujigou = new Department();
            shijishujigou.setDepartmentType(departmentTypeRepository.getByName("技术机构"));
            shijishujigou.setDistrict(districtRepository.getByName("廊坊市"));
            shijishujigou.setName("廊坊市技术机构");
            shijishujigou.setPinyin("langfangshijishujigou");
            departments.add(shijishujigou);

            Department testQuxianjishujigou = new Department();
            testQuxianjishujigou.setDepartmentType(departmentTypeRepository.getByName("技术机构"));
            testQuxianjishujigou.setDistrict(districtRepository.getByName("测试区县"));
            testQuxianjishujigou.setName("测试区县技术机构");
            testQuxianjishujigou.setPinyin("anciqujishujigou");
            departments.add(testQuxianjishujigou);

            Department testShijishujigou = new Department();
            testShijishujigou.setDepartmentType(departmentTypeRepository.getByName("技术机构"));
            testShijishujigou.setDistrict(districtRepository.getByName("测试市"));
            testShijishujigou.setName("测试市技术机构");
            testShijishujigou.setPinyin("ceshishijisujigou");
            departments.add(testShijishujigou);

            logger.info("----- 添加器具用户  -----");
            Department testQijuyonghu = new Department();
            testQijuyonghu.setDepartmentType(departmentTypeRepository.getByName("器具用户"));
            testQijuyonghu.setDistrict(districtRepository.getByName("测试区县"));
            testQijuyonghu.setName("测试器具用户");
            testQijuyonghu.setAddress("测试地址");
            testQijuyonghu.setCode("机构代码");
            testQijuyonghu.setLegalName("法人姓名");
            testQijuyonghu.setLegalPhone("法人电话");
            testQijuyonghu.setPostalCode("邮政编码");
            testQijuyonghu.setRegistrantName("注册人姓名");
            testQijuyonghu.setRegistrantPhone("注册人手机");
            testQijuyonghu.setRegistrantTel("注册人座机");
            testQijuyonghu.setRegistrantMail("注册人邮箱");
            testQijuyonghu.setPhone("联系电话");
            testQijuyonghu.setRegisterDate(new Date(1232123L));
            testQijuyonghu.setPinyin("ceshiqijuyonghu");
            departments.add(testQijuyonghu);

            logger.info("----- 添加生产企业  -----");
            Department shengchanqiye1 = new Department();
            shengchanqiye1.setDepartmentType(departmentTypeRepository.getByName("生产企业"));
            shengchanqiye1.setDistrict(districtRepository.getByName("测试区县"));
            shengchanqiye1.setName("测试生产企业");
            shengchanqiye1.setPinyin("ceshishengcanqiye");
            departments.add(shengchanqiye1);

            Department shengchanqiye2 = new Department();
            shengchanqiye2.setDepartmentType(departmentTypeRepository.getByName("生产企业"));
            shengchanqiye2.setDistrict(districtRepository.getByName("安次区"));
            shengchanqiye2.setName("生产企业2");
            shengchanqiye2.setPinyin("shengchanqiye2");
            departments.add(shengchanqiye2);

            departmentRepository.save(departments);

        }
        return;
    }
        /**
         * 申请类型数据独立
         *
         * @return
         */
    @Override
    public int getOrder() {
        return districtDataInit.getOrder() + 10;
    }
}
