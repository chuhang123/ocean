package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.controller.MandatoryInstrumentApplyController;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Calendar;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-15.
 */
public class MandatoryInstrumentApplyServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceTest.class.getName());
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected UserRepository userRepository;
    @Autowired protected UserService userService;
    @Autowired protected WorkRepository workRepository;
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired protected MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired private MandatoryInstrumentApplyServiceTestData mandatoryInstrumentApplyServiceTestData;
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;      // 部门类型
    @Autowired InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;   // 一级类别
    @Autowired InstrumentTypeRepository instrumentTypeRepository;                       // 二级类别
    @Autowired PurposeRepository purposeRepository;                                     // 用途
    @Autowired MandatoryInstrumentRepository mandatoryInstrumentRepository;             // 强检器具
    @Test
    public void getPageOfCurrentDepartment() throws Exception {
        logger.info("设置部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("设置当前登录用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);
        logger.info("设置申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("设置工作");
        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(1, 1);
        Page<Apply> applies = mandatoryInstrumentApplyService.getPageOfCurrentDepartment(pageRequest);
        logger.info("断言");
        assertThat(applies.getTotalPages()).isEqualTo(1);
        logger.info("删除数据");
        workRepository.delete(work);
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void save() {
        MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply workMandatoryInstrumentApply =
                new MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply();
        User user = mandatoryInstrumentApplyServiceTestData.save(workMandatoryInstrumentApply);
        mandatoryInstrumentApplyService.save(workMandatoryInstrumentApply);

        assertThat(workMandatoryInstrumentApply.getWork().getId()).isNotNull();
        assertThat(workMandatoryInstrumentApply.getMandatoryInstrumentApply().getId()).isNotNull();
        assertThat(workMandatoryInstrumentApply.getWork().getApply().getId())
                .isEqualTo(workMandatoryInstrumentApply.getMandatoryInstrumentApply().getId());
        assertThat(workMandatoryInstrumentApply.getWork().getAuditDepartment().getId())
                .isEqualTo(user.getDepartment().getId());

    }

    @Autowired DistrictTypeRepository districtTypeRepository;
    @Autowired DistrictRepository districtRepository;
    @Test
    public void generateWordApplyByToken() throws Exception {
        logger.info("创建一个部门，并设置基本信息");
        Department department = new Department();
        department.setName("部门名称");
        department.setAddress("部门地址");
        department.setCode("统一社用代码");
        departmentRepository.save(department);

        logger.info("创建一个生产企业");
        Department department0 = new Department();
        department0.setName("制造单位名称");
        departmentRepository.save(department0);

        logger.info("创建两个技术机构");
        District district = new District();
        district.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
        districtRepository.save(district);
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        Department department1 = new Department();
        department1.setName("区县技术机构一");
        department1.setDistrict(district);;
        department1.setDepartmentType(departmentType);
        departmentRepository.save(department1);

        District district1 = new District();
        district1.setDistrictType(districtTypeRepository.findOneByName("市"));
        districtRepository.save(district1);
        Department department2 = new Department();
        department2.setDistrict(district1);
        department2.setName("技术机构二");
        department2.setDepartmentType(departmentType);
        departmentRepository.save(department2);

        logger.info("创建一个申请, 并设置基本信息");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setDepartment(department);
        mandatoryInstrumentApply.setContactName("联系人");
        mandatoryInstrumentApply.setContactNumber("联系电话");
        mandatoryInstrumentApply.setApplyTime(Calendar.getInstance());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("创建一个一级类别");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setName("一级类别");
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("创建一个二级类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("二级类别");
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);

        logger.info("创建一个用途");
        Purpose purpose = new Purpose();
        purpose.setName("用途");
        purposeRepository.save(purpose);

        logger.info("创建10个强检器具,其中4个给一个技术机构审核，3个给第二个技术机构审核，另外3个不审核");
        for(Integer i = 0; i < 10; i ++) {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            mandatoryInstrument.setMandatoryInstrumentApply(mandatoryInstrumentApply);
            mandatoryInstrument.setDepartment(department);
            mandatoryInstrument.setPurpose(purpose);
            mandatoryInstrument.setInstrumentType(instrumentType);
            mandatoryInstrument.setGenerativeDepartment(department0);

            if (i % 3 == 0) {
                mandatoryInstrument.setCheckDepartment(department1);
            } else if (i % 3 == 1) {
                mandatoryInstrument.setCheckDepartment(department2);
            }

            mandatoryInstrument.setName("器具名称");
            mandatoryInstrument.setSpecificationName("规格型号");
            mandatoryInstrument.setMeasureScaleName("测量范围");
            mandatoryInstrument.setAccuracyName("准确度等级");
            mandatoryInstrument.setSerialNum("出厂编号");
            mandatoryInstrument.setFixSite("安装/使用地点");

            mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        }
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        String token = MandatoryInstrumentApplyService.generateTokenById(mandatoryInstrumentApply.getId());
        mandatoryInstrumentApplyService.generateWordApplyByToken(token);
    }

}