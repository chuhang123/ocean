package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceSetServiceTest {
    private Logger logger = Logger.getLogger(DeviceSetServiceTest.class.getName());
    @Autowired protected DeviceSetService deviceSetService;       // 计量标准装置
    @Autowired protected DeviceSetRepository deviceSetRepository;
    @Autowired protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired protected AccuracyRepository accuracyRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected UserService userService;
    @Autowired protected UserRepository userRepository;
    @Autowired protected DeviceSetServiceTestData deviceSetServiceTestData;
    @Autowired protected DistrictRepository districtRepository;

    @Test
    public void save() throws Exception {
        logger.info("一并测试getOneDeviceSet save delete方法");
        DeviceSet deviceSet = deviceSetService.getOneDeviceSet();
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);
        deviceSetService.save(deviceSet);
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNotNull();
        deviceSetService.delete(deviceSet);
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNull();
    }

    @Test
    public void updateDeviceInstrumentsById() {
        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");

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

        deviceInstrumentRepository.save(deviceInstrument);
        deviceInstruments.add(deviceInstrument);

        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSetRepository.save(deviceSet);

        logger.info("更新");
        deviceSet.setName("hehe");
        deviceInstruments.remove(deviceInstrument);
        deviceSet.setDeviceInstruments(deviceInstruments);
        DeviceSet newDeviceSet = deviceSetService.updateDeviceInstrumentsById(deviceSet.getId(), deviceSet);

        logger.info("断言");
        assertThat(newDeviceSet.getName()).isEqualTo("hehe");
        logger.info("断言中间表删除成功");
        assertThat(newDeviceSet.getDeviceInstruments().size()).isEqualTo(0);

        logger.info("删除实体");
        deviceInstrumentRepository.delete(deviceInstrument);
        accuracyRepository.delete(accuracy);
        measureScaleRepository.delete(measureScale);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        logger.info("新建一个计量标注装置实体并保存");
        DeviceSet deviceSet = deviceSetService.getOneDeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个计量标准装置实体并保存");
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setName("name1");
        deviceSet1.setCertificateNum("number2");
        deviceSetRepository.save(deviceSet1);

        logger.info("设置分页大小以及显示的页码的起始位置");
        final PageRequest pageRequest = new PageRequest(1,1);

        logger.info("调用在deviceRepository中定义的分页方法，并将参数pageRequest传入");
        Page<DeviceSet> page = deviceSetRepository.findAll(pageRequest);

        logger.info("断言分页总数与数据表中数据总数相同");
        assertThat(page.getTotalPages()).isEqualTo((int)page.getTotalElements());
        logger.info("断言分页大小为1");
        assertThat(page.getContent()).size().isEqualTo(1);

        logger.info("删除刚才建立的实体");
        deviceSetRepository.delete(deviceSet);
        deviceSetRepository.delete(deviceSet1);
        return;
    }

    @Test
    public void getAllDeviceSetByTechnicalInstitutionId() {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        Set<DeviceSet> deviceSets = new HashSet<>();
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSets.add(deviceSet);
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setDepartment(department);
        deviceSets.add(deviceSet1);
        deviceSetRepository.save(deviceSets);

        logger.info("测试");
        List<DeviceSet> list = deviceSetService.getAllDeviceSetByTechnicalInstitutionId(department.getId());
        assertThat(list.size()).isEqualTo(2);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSets);
        departmentRepository.delete(department);
    }

    @Test
    public void pageAllByCurrentUser() {
        logger.info("获取区县技术机构部门User并设置为当前登录用户");
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);

        logger.info("选测试区县并添加标准装置");
        Department department = departmentRepository.findByName("测试区县技术机构");

        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("断言");
        final PageRequest pageRequest = new PageRequest(1, 1);
        Page<DeviceSet> page = deviceSetService.pageAllOfCurrentUser(pageRequest);
        assertThat(page.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void update() {
        logger.info("创建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("name");
        deviceSetRepository.save(deviceSet);
        logger.info("修改更新数据");
        deviceSet.setName("haha");
        logger.info("测试");
        deviceSetService.update(deviceSet.getId(), deviceSet);
        logger.info("断言");
        assertThat(deviceSetRepository.findOne(deviceSet.getId()).getName()).isEqualTo("haha");
        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void delete() {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("断言");
        deviceSetService.delete(deviceSet.getId());
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNull();

        logger.info("删除数据");
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

//    @Test
//    public void page() {
//        logger.info("获取市管理部门User并设置为当前登录用户");
//        User user = userRepository.findOneByUsername("user4");
//        userService.setCurrentLoginUser(user);
//
//        logger.info("选测试区县并添加标准装置");
//        Department department = departmentRepository.findByName("测试区县技术机构");
//
//        DeviceSet deviceSet = new DeviceSet();
//        deviceSet.setDepartment(department);
//        deviceSetRepository.save(deviceSet);
//
//        logger.info("测试");
//        PageRequest pageRequest = new PageRequest(1,1);
//        Page<DeviceSet> deviceSets = deviceSetService.pageAllOfCurrentUser(pageRequest);
//
//        logger.info("断言");
//        assertThat(deviceSets.getTotalPages()).isEqualTo(1);
//
//        logger.info("删除数据");
//        deviceSetRepository.delete(deviceSet);
//    }

    @Test
    public void pageAllOfCurrentUserBySpecification() {
//        User user = userService.loginWithOneUser();
        String code = CommonService.getRandomStringByLength(10);
        String name = CommonService.getRandomStringByLength(10);
        Pageable pageable = new PageRequest(0, 2);
        Page<DeviceSet> deviceSets = null;
        DeviceSet deviceSet = new DeviceSet();
        Department department = new Department();
        District district = new District();

        deviceSetServiceTestData.pageAllOfCurrentUserBySpecification(name, code, department, district, deviceSet);

        // 只传入name
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(0L, 0L,name, "", pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        // 传入name和代码
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(0L, 0L,name, code, pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        //传入部门ID，name和代码
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(0L, department.getId(), name, code, pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        //传入部门ID，name和代码,区域id
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(district.getId(), department.getId(), name, code, pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        //删除数据
        deviceSetRepository.delete(deviceSet);
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }
}