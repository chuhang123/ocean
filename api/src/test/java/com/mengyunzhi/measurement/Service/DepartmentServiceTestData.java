package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/24.
 * 部门服务测试
 */
@Component
public class DepartmentServiceTestData {
    private static Logger logger = Logger.getLogger(DepartmentServiceTestData.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired private DistrictTypeRepository districtTypeRepository; //区域类型
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkflowNodeRepository workflowNodeRepository;
    @Autowired private DeviceSetRepositoryTestDataInit deviceSetDeviceInstrumentRepositoryTestDataInit;
    @Autowired private InstrumentProductionRepository instrumentProductionRepository;   // 器具生产信息
    @Autowired private MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具信息

    @Autowired @org.springframework.beans.factory.annotation.Qualifier("DepartmentService") DepartmentService departmentService;     // 部门
    @Autowired UserService userService;     // 用户

    private Department department;      // 部门
    private User user;                  // 用户
    private DistrictType districtType;  // 区域类型
    private District district;          // 区域
    private DepartmentType departmentType; // 部门类型
    private InstrumentProduction instrumentProduction;  // 生产信息

    public void getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(WorkflowNode workflowNode, MandatoryInstrument mandatoryInstrument) {
        department = new Department();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceSetDeviceInstrumentRepositoryTestDataInit.countByDeviceInstrumentAndDeviceSetDepartment(deviceInstrument, department);

        logger.info("创建一个用户实体");
        user = UserService.getOneUser();
        user.setStatus(0);
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("创建一个区域类型");
        districtType = new DistrictType();
        districtTypeRepository.save(districtType);
        logger.info("创建一个区域实体");
        district = new District();
        district.setDistrictType(districtType);
        district.setName("name");
        districtRepository.save(district);

        logger.info("新建一个部门类型实体");
        departmentType = new DepartmentType();
        departmentType.setName("departmentType");
        departmentTypeRepository.save(departmentType);
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);

        logger.info("新建一个工作流节点");
        workflowNode.setName("workflowNode");
        workflowNode.setDepartmentType(departmentType);
        workflowNode.setDistrictType(district.getDistrictType());
        workflowNode.setContainSonDistrict(false);
        workflowNodeRepository.save(workflowNode);

        logger.info("新建使用信息");
        instrumentProduction =InstrumentProductionService.getOneInstrumentProduction();
        instrumentProductionRepository.save(instrumentProduction);

        mandatoryInstrument.setInstrumentProduction(instrumentProduction);
        mandatoryInstrument.setAccuracy(deviceInstrument.getAccuracy());
        mandatoryInstrument.setMeasureScale(deviceInstrument.getMeasureScale());
        mandatoryInstrumentRepository.save(mandatoryInstrument);
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public InstrumentProduction getInstrumentProduction() {
        return instrumentProduction;
    }

    public void setInstrumentProduction(InstrumentProduction instrumentProduction) {
        this.instrumentProduction = instrumentProduction;
    }

    /**
     * 数据准备
     * @param departmentTypePinyin 部门类型对应的pinyin
     * @param user 用户（用以进行模拟登录）
     */
    public User pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(String departmentTypePinyin) {
        logger.info("新建个管理部门");
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        Department department = new Department();
        department.setName("测试部门");
        department.setPinyin("test");
        department.setDepartmentType(departmentType);

        logger.info("新建区域");
        District district = new District();
        district.setName(CommonService.getRandomStringByLength(10));
        districtRepository.save(district);
        department.setDistrict(district);
        departmentRepository.save(department);

        logger.info("新建部门类型");
        DepartmentType departmentType1 = new DepartmentType();
        departmentType1.setName(CommonService.getRandomStringByLength(10));
        departmentType1.setPinyin(departmentTypePinyin);
        departmentTypeRepository.save(departmentType1);

        logger.info("在父区域上增加一个符合条件的部门");
        Department department1 = new Department();
        department1.setName(CommonService.getRandomStringByLength(3));
        department1.setDistrict(district);
        department1.setDepartmentType(departmentType1);
        departmentRepository.save(department1);

        logger.info("新建20个子区域，每个区域上建立2个符合条件的部门");
        for (int i = 0; i < 20; i++) {
            District district1 = new District();
            district1.setName(CommonService.getRandomStringByLength(4));
            district1.setParentDistrict(district);
            districtRepository.save(district1);
            for (int j = 0; j < 2; j++) {
                Department department2 = new Department();
                department2.setName(CommonService.getRandomStringByLength(5));
                department2.setDistrict(district1);
                department2.setDepartmentType(departmentType1);
                departmentRepository.save(department2);
            }
        }

        User user = userService.loginWithOneUser();
        user.setDepartment(department);
        userRepository.save(user);
        return user;
    }
}
