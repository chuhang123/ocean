package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceInstrumentServiceTest {
    static private Logger logger = Logger.getLogger(DeviceInstrumentServiceTest.class.getName());

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
    @Autowired
    private DeviceInstrumentServiceTestData deviceInstrumentServiceTestData;    // 数据准备
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private AccuracyService accuracyService;
    @Autowired
    private MeasureScaleService measureScaleService;
    @Test
    public void save() throws Exception {
        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");
        deviceSetRepository.save(deviceSet);

        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);

        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScaleRepository.save(measureScale);

        logger.info("新建授权鉴定项目实体");
        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstruments.add(deviceInstrument);

        logger.info("测试数据");
        deviceInstrumentService.save(deviceSet);
        deviceSet.setDeviceInstruments(deviceInstruments);

        logger.info("断言");
        DeviceSet newDeviceSet = deviceSetRepository.findOne(deviceSet.getId());
        assertThat(newDeviceSet.getDeviceInstruments().size()).isEqualTo(1);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
        deviceInstrumentRepository.delete(deviceInstrument);
        accuracyRepository.delete(accuracy);
        measureScaleRepository.delete(measureScale);
    }

    @Test
    public void pageAllByCurrentUserOfDeviceInstrument() {
        logger.info("pageAllByCurrentUserOfDeviceInstrument");
        logger.info("创建一个部门");
        Department department = new Department();
        DepartmentType departmentType = departmentTypeRepository.getByName("技术机构");
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.clearCurrentTestLoginUser();
        userService.setCurrentLoginUser(user);

        logger.info("新建器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("123");
        instrumentType.setPinyin("123");
        instrumentTypeRepository.save(instrumentType);

        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");
        deviceSet.setDepartment(department);

        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        Accuracy accuracy1 = new Accuracy();
        accuracyRepository.save(accuracy);
        accuracyRepository.save(accuracy1);

        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(instrumentType);
        MeasureScale measureScale1 = new MeasureScale();
        measureScale1.setInstrumentType(instrumentType);
        measureScaleRepository.save(measureScale);
        measureScaleRepository.save(measureScale1);

        logger.info("新建授权鉴定项目实体");
        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument1.setMeasureScale(measureScale1);
        deviceInstrument1.setAccuracy(accuracy1);

        deviceInstrumentRepository.save(deviceInstrument);
        deviceInstrumentRepository.save(deviceInstrument1);
        deviceInstruments.add(deviceInstrument);
        deviceInstruments.add(deviceInstrument1);

        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSetRepository.save(deviceSet);

        PageRequest pageRequest = new PageRequest(1, 1);

        logger.info("测试存两条成功");
        assertThat(deviceSet.getDeviceInstruments().size()).isEqualTo(2);
        logger.info("测试");
        Page<?> page = deviceInstrumentService.pageAllByCurrentUserOfDeviceInstrument(pageRequest);

//        List<DeviceInstrument> deviceInstruments1 = (List<DeviceInstrument>) deviceInstrumentRepository.findAll();
//        assertThat(deviceInstruments1.size()).isEqualTo(2);
//
//        logger.info("断言");
//        assertThat(page.getTotalPages()).isEqualTo(2);
    }

    @Test
    public void pageByDeviceSetOfCurrentUser() {
        DeviceSet deviceSet = new DeviceSet();
        deviceInstrumentServiceTestData.pageByDeviceSetOfCurrentUser(deviceSet);

        Pageable pageable = new PageRequest(1,2);
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentService.pageByDeviceSetOfCurrentUser(deviceSet, pageable);
        logger.info("模拟登录获取第一个标准装置的");
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(2);

        logger.info("模拟登录获取当前部门所有标准装置的");
        DeviceSet deviceSet2 = new DeviceSet();
        deviceInstruments = deviceInstrumentService.pageByDeviceSetOfCurrentUser(deviceSet2, pageable);
        logger.info("模拟登录获取第一个标准装置的");
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(4);

    }

    @Test
    public void pageAllOfCurrentManageDepartmentBySpecification() {
        logger.info("新建两个区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("使用用户登录");
        User user = userService.loginWithOneUser();
        user.getDepartment().setDistrict(district);
        departmentRepository.save(user.getDepartment());

        String name = CommonService.getRandomStringByLength(10);        // 名称
        Pageable pageable = new PageRequest(0, 2);          //分页信息

        logger.info("创建一个授权检定装置");
        Accuracy accuracy = accuracyService.getOneAccuracy();
        MeasureScale measureScale = measureScaleService.getOneMeasureScale();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("创建标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName(name);
        deviceSet.setDepartment(user.getDepartment());
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);

        Page<DeviceInstrument> deviceInstruments = null;

        logger.info("测试标准装置名ID");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(deviceSet.getId(), null, null, null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试标准装置名称");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, null,null, null, null, null, name, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试部门, 标准装置名称");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, null, user.getDepartment().getId(), null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试部门,区域,标准装置姓名");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, district.getId(), user.getDepartment().getId(), null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

    }
}