package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by panjie on 17/8/9.
 */
@Component
public class DeviceInstrumentServiceTestData {
    private final static Logger logger = Logger.getLogger(DeviceInstrumentServiceTestData.class.getName());
    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;
    @Autowired
    protected DeviceSetService deviceSetService;       // 计量标准装置
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired
    protected AccuracyRepository accuracyRepository;
    @Autowired
    protected MeasureScaleRepository measureScaleRepository;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;

    public User pageByDeviceSetOfCurrentUser(DeviceSet deviceSet) {
        logger.info("新建一个用户登录");
        User user = userService.loginWithOneUser();

        logger.info("新增两个授权检定项目");
        DeviceInstrument deviceInstrument = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrumentRepository.save(deviceInstrument);

        DeviceInstrument deviceInstrument1 = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrumentRepository.save(deviceInstrument1);

        logger.info("新建两个标准装置");
        logger.info("在每个标准装置上都增加这两个授权检定项目");
        deviceSet.setDepartment(user.getDepartment());
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSet.addDeviceInstrument(deviceInstrument1);
        deviceSetRepository.save(deviceSet);

        DeviceSet deviceSet1 = deviceSetService.getOneDeviceSet();
        deviceSet1.setDepartment(user.getDepartment());
        deviceSet1.addDeviceInstrument(deviceInstrument);
        deviceSet1.addDeviceInstrument(deviceInstrument1);
        deviceSetRepository.save(deviceSet1);

        return user;
    }
}
