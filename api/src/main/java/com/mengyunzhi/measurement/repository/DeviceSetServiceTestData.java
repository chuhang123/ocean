package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 17-8-13.
 */
@Component
public class DeviceSetServiceTestData {
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected DistrictRepository districtRepository;

    public void pageAllOfCurrentUserBySpecification(String name, String code, Department department, District district, DeviceSet deviceSet) {
        //只传入标准装置名称
        deviceSet.setName(name);
        deviceSetRepository.save(deviceSet);

        //只传入代码,标准装置名层
        deviceSet.setCode(code);
        deviceSetRepository.save(deviceSet);

        //只传入部门,代码,标准装置名称
        departmentRepository.save(department);
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        //传入部门，区域，器具名称，代码
        districtRepository.save(district);
        department.setDistrict(district);
        departmentRepository.save(department);
    }
}
