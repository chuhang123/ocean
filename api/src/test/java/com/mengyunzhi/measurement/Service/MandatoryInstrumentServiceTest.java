package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.input.MandatoryInstrumentSaveInput;
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
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/13.
 * 强检器具
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MandatoryInstrumentServiceTest {
    private static final Logger logger = Logger.getLogger(MandatoryInstrumentServiceTest.class.getName());
    @Autowired
    private UserRepository userRepository;           // 用户
    @Autowired
    private UserService userService;                 // 用户
    @Autowired
    protected WorkflowNodeService workflowNodeService;              // 工作流结点
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected ApplyTypeRepository applyTypeRepository;                       // 申请类型
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强制检定申请
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("DepartmentService")
    private DepartmentService departmentService; // 部门
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private MandatoryInstrumentServiceTestData mandatoryInstrumentServiceTestData;
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Autowired
    InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    DistrictRepository districtRepository;       // 区域

    @Test
    @Transactional
    public void apply() {
        // 保存一个用户, 并设置为当前登录用户
        User user = new User();
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        // 实例化输入对象
        MandatoryInstrumentSaveInput mandatoryInstrumentSaveInput = new MandatoryInstrumentSaveInput();

        // 获取申请类型
        ApplyType applyType = applyTypeRepository.findOneByName("区\\县器具用户新增器具");

        // 获取工作流类型
        WorkflowType workflowType = applyType.getWorkflowType();

        // 获取当前工作流
        WorkflowNode currentWorkflowNode = workflowNodeRepository.findTopOneByWorkflowTypeAndPreWorkflowNodeIsNull(workflowType);
        mandatoryInstrumentSaveInput.setCurrentWorkflowNode(currentWorkflowNode);

        // 获取下一工作流
        List<WorkflowNode> selectedWorkflowNodes = workflowNodeService.getAllByPreWorkflowNodeId(currentWorkflowNode.getId());
        WorkflowNode nextWorkflowNode = selectedWorkflowNodes.get(0);
        mandatoryInstrumentSaveInput.setNextWorkflowNode(nextWorkflowNode);

        // 用户选择的审核部门
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);
        mandatoryInstrumentSaveInput.setAuditDepartment(department);

        // 申请联系人
        MandatoryInstrumentApply apply = MandatoryInstrumentApplyService.getOneMandatoryInstrumentApply();
        mandatoryInstrumentSaveInput.setApply(apply);

        // 发起来意见
        Work work = WorkService.getOneWork();
        mandatoryInstrumentSaveInput.setWork(work);

        // 生产企业信息
        Department department1 = DepartmentService.getOneDepartment();

        // 器具生产信息
        InstrumentProduction instrumentProduction = InstrumentProductionService.getOneInstrumentProduction();
        instrumentProduction.setManufacturerDepartment(department);

        // 强检器具信息
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setInstrumentProduction(instrumentProduction);
        mandatoryInstrumentSaveInput.setMandatoryInstrument(mandatoryInstrument);

        mandatoryInstrumentService.apply(mandatoryInstrumentSaveInput);

        return;
    }

    @Test
    @Transactional
    public void save() {
        User user = userService.loginWithOneUser();
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department = departmentService.getOneCompleteDepartment();
        mandatoryInstrument.setGenerativeDepartment(department);
        mandatoryInstrumentService.save(mandatoryInstrument);
        assertThat(mandatoryInstrument.getCreateUser().getId()).isEqualTo(user.getId());
        assertThat(mandatoryInstrument.getDepartment().getId()).isEqualTo(user.getDepartment().getId());
        assertThat(mandatoryInstrument.getGenerativeDepartment().getId()).isEqualTo(department.getId());

        String name = CommonService.getRandomStringByLength(10);
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department1 = new Department();
        department1.setName(name);
        mandatoryInstrument1.setGenerativeDepartment(department1);
        mandatoryInstrumentService.save(mandatoryInstrument1);
        assertThat(mandatoryInstrument1.getGenerativeDepartment().getId()).isNotNull();

        MandatoryInstrument mandatoryInstrument2 = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department2 = new Department();
        department2.setName(name);
        mandatoryInstrument2.setGenerativeDepartment(department2);
        mandatoryInstrumentService.save(mandatoryInstrument2);
        assertThat(mandatoryInstrument2.getGenerativeDepartment().getId()).isEqualTo(mandatoryInstrument1.getGenerativeDepartment().getId());
        assertThat(mandatoryInstrument2.getGenerativeDepartment().getDepartmentType().getPinyin()).isEqualTo("shengchanqiye");
    }

    @Test
    @Transactional
    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest() {
        User user = userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        this.mandatoryInstrumentServiceTestData.updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest(
                user.getDepartment(), mandatoryInstrumentApply
        );
        logger.info("增加一个审核部门，并调用方法");
        Department checkDepartment = departmentService.getOneCompleteDepartment();
        mandatoryInstrumentService.updateCheckDepartmentByMandatoryInstrumentsAndDepartmentId(mandatoryInstrumentApply.getMandatoryInstruments(), checkDepartment.getId());

        logger.info("获取数据，并断言");
        List<MandatoryInstrument> mandatoryInstruments1 = mandatoryInstrumentRepository.findAllByMandatoryInstrumentApplyId(mandatoryInstrumentApply.getId());
        assertThat(mandatoryInstruments1.size()).isEqualTo(5);
        int j = 0;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments1) {
            if (mandatoryInstrument.getCheckDepartment() != null &&
                    mandatoryInstrument.getCheckDepartment().getId() == checkDepartment.getId()) {
                j++;
            }
        }
        assertThat(j).isEqualTo(3);
    }

    @Test
    public void pageAllOfCurrentUser() {
        logger.info("获取技术机构登录用户");
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);

        logger.info("取出测试区县技术机构");
        Department department = departmentRepository.findByName("测试区县技术机构");

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setCheckDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUser(pageRequest);

        logger.info("断言");
        assertThat(mandatoryInstruments.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void getAllOfCurrentUser() {
        logger.info("获取技术机构登录用户");
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);

        logger.info("取出测试区县技术机构");
        Department department = departmentRepository.findByName("测试区县技术机构");

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setCheckDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentService.getAllOfCurrentUser();

        logger.info("断言");
        assertThat(mandatoryInstruments.size()).isEqualTo(1);

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() {
//        https://my.oschina.net/u/2434456/blog/596938
        logger.info("新建立两个区域，父区域和子区域");
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
        Pageable pageable = new PageRequest(0, 2);                  // 分页
        Page<MandatoryInstrument> mandatoryInstruments = null;                  // 返回值
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();    // 强检器具
        mandatoryInstrument.setDepartment(user.getDepartment());                // 设置部门
        InstrumentType instrumentType = new InstrumentType();                   // 器具类别
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType(); // 一级类别
        Discipline discipline = new Discipline();                                           // 学科类别
        // 区域

        mandatoryInstrumentServiceTestData.pageAllOfCurrentUserBySpecification(name, mandatoryInstrument, instrumentType, instrumentFirstLevelType, discipline);

        // 只传入器具名称
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, null, null, null, null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入器具名称及器具类别
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, 1L, 1L, instrumentType.getId(), null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, 1L, null, instrumentType.getId(), null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入器具名称、及一级学科类别 name
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null,1L, instrumentFirstLevelType.getId(), null, null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null,null, instrumentFirstLevelType.getId(), null, null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 只传入学科类别 name
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, null, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入name及是否已检定
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, false, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        mandatoryInstrument.setAudit(true);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, true, name, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按ID查询");
        mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(mandatoryInstrument.getId(), null, null, null,null, null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按父区域查询");
        mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, district.getId(), null, null, null,null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按子区域查询");
        mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, district1.getId(), null, null, null,null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("查询审核部门");
        mandatoryInstrument.setCheckDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification(null, null, null,null,null,null,null,null);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);


    }

    public void updateFixSiteAndName() throws Exception {
        logger.info("---- updateFixsiteAndName方法测试 ----");
        logger.info("新建一个强检器具实体");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setName("123");
        mandatoryInstrument.setFixSite("123");
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("修改该实体");
        mandatoryInstrument.setFixSite("456");
        mandatoryInstrument.setName("456");
        mandatoryInstrumentService.updateFixSiteAndName(mandatoryInstrument.getId(), mandatoryInstrument);

        logger.info("断言修改成功");
        assertThat(mandatoryInstrument.getName()).isEqualTo("456");
        assertThat(mandatoryInstrument.getFixSite()).isEqualTo("456");

        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

}