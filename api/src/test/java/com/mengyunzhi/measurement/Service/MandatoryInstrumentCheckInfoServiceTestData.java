package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 17-8-2.
 * 强检器具检定信息测试数据
 */
@Component
public class MandatoryInstrumentCheckInfoServiceTestData {
    @Autowired protected UserService userService;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired protected InstrumentCheckInfoRepository instrumentCheckInfoRepository;

    public void getOneMandatoryInstrumentCheckInfo(Department department, User user, MandatoryInstrument mandatoryInstrument, InstrumentCheckInfo instrumentCheckInfo) {
        //用户
        userService.setCurrentLoginUser(user);
        //强检器具信息
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        //器具检定信息
        instrumentCheckInfo.setCheckDepartment(department);
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
    }


}
