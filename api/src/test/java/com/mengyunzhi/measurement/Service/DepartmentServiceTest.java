package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/7/6.
 * 部门测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentServiceTest {
    private static Logger logger = Logger.getLogger(DepartmentTypeServiceTest.class.getName());
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
    @Autowired private DepartmentServiceTestData departmentServiceTestData; // 基本测试数据准备
    @Autowired
    private DistrictService districtService;     // 区域
    @Autowired private MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定器具

    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService;     // 部门
    @Autowired UserService userService;     // 用户
    private DepartmentType departmentType;
    private Set<Department> departments = new HashSet<>();
    private String name;
    @Before
    public void addData() {
        logger.info("创建一个部门类型实体并保存");
        departmentType = new DepartmentType();
        departmentTypeRepository.save(departmentType);
        logger.info("准备测试数据");
        name = CommonService.getRandomStringByLength(32);
        for (Integer i = 0;i < 20; i++)
        {
            Department department = new Department();
            department.setName(i.toString() + name + i.toString());
            department.setCode("15088");
            department.setAddress("天津市北辰区双口镇河北工业大学");
            /**
             * 关键遗漏点：
             * 我们要按照 部门类型id  进行查询，那么我们在保存部门的时候，就需要关联好这个部门类型
             * 排错方向：1.数据是否存储正确。2.是否生成了正确的查询语句。3.实体仓库层的方法名是否正确
             * 排错方法：
             * 1. 删除对ServiceTest的继承，手动写注解，并且不要写 事务 注解
             * 2. logger.info("断言取出了十条数据"); 打断点
             * 3. 使用navicat查看数据表，看是否生成自己想要的信息。
             * 4. 发现未生成department_type_id
             * 5. 得出在新建部门时，没有设置 部门类型 。
             */
            department.setDepartmentType(departmentType);
            departments.add(department);
        }
        departmentRepository.save(departments);
    }

    @After public void deleteData() {
        logger.info("删除实体");
        departmentRepository.delete(departments);
        departmentTypeRepository.delete(departmentType);
    }

    @Test
    public void getTop10ByDepartmentTypeIdAndName() throws Exception {
        logger.info("查询数据");
        List<Department> list = departmentService.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        logger.info("断言取出了十条数据");
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    public void getAllByDistrictAndDepartmentTypeIncludeSons() throws Exception {
        logger.info("添加区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("添加部门");
        Set<Department> newDepartments = new HashSet<>();
        Department department1 = new Department();
        department1.setDepartmentType(departmentType);
        department1.setDistrict(district);
        newDepartments.add(department1);
        Department department2 = new Department();
        department2.setDistrict(district);
        department2.setDepartmentType(departmentType);
        newDepartments.add(department2);
        Department department3 = new Department();
        department3.setDepartmentType(departmentType);
        department3.setDistrict(district1);
        newDepartments.add(department3);
        departmentRepository.save(newDepartments);

        logger.info("测试并断言");
        List<Department> testDepartments = departmentService.getAllByDistrictAndDepartmentTypeIncludeSons(district, departmentType);
        assertThat(testDepartments.size()).isEqualTo(3);

        logger.info("删除数据");
        departmentRepository.delete(newDepartments);
        districtRepository.delete(district1);
        districtRepository.delete(district);
    }

    @Test
    public void findAllByDistrictAndDepartmentType() {
        logger.info("创建一个区域类型");
        District district = new District();
        districtRepository.save(district);
        logger.info("新建一个部门");
        Department department = new Department();
        department.setDistrict(district);
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        logger.info("测试并断言");
        List<Department> list = departmentService.getAllByDistrictAndDepartmentType(district, departmentType);
        assertThat(list.size()).isEqualTo(1);
        logger.info("删除数据");
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }


    @Test
    @Transactional
    public void getAllByWorkflowNodeOfCurrentLoginUser() throws Exception {
        logger.info("创建一个区域类型");
        DistrictType districtType = new DistrictType();
        districtTypeRepository.save(districtType);
        logger.info("创建一个区域实体");
        District district = new District();
        district.setDistrictType(districtType);
        district.setName("name");
        districtRepository.save(district);

        logger.info("新建一个部门类型实体");
        DepartmentType departmentType = new DepartmentType();
        departmentType.setName("departmentType");
        departmentTypeRepository.save(departmentType);

        logger.info("新建一个工作流节点");
        WorkflowNode workflowNode = new WorkflowNode();
        workflowNode.setName("workflowNode");
        workflowNode.setDepartmentType(departmentType);
        workflowNode.setDistrictType(district.getDistrictType());
        workflowNode.setContainSonDistrict(false);
        workflowNodeRepository.save(workflowNode);

        logger.info("新建一个部门实体A");
        Department departmentA = new Department();
        departmentA.setName("departmentA");
        departmentA.setDepartmentType(departmentType);
        departmentA.setDistrict(district);
        departmentRepository.save(departmentA);

        logger.info("新建一个部门实体B");
        Department departmentB = new Department();
        departmentB.setName("departmentB");
        departmentB.setDepartmentType(departmentType);
        departmentB.setDistrict(district);
        departmentRepository.save(departmentB);

        logger.info("创建一个用户实体");
        User user = new User();
        user.setName("name");
        user.setDepartment(departmentA);
        userRepository.save(user);

        userService.setCurrentLoginUser(user);

        List<Department> departments = departmentService.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);
        assertThat(departments).size().isEqualTo(2);


        logger.info("测试包含子区域");
        workflowNode.setContainSonDistrict(true);
        workflowNodeRepository.save(workflowNode);

        logger.info("添加子区域");
        District district1 = districtService.getOneDistrict();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("创建一个新的部门");
        Department department = DepartmentService.getOneDepartment();
        department.setDepartmentType(departmentType);
        department.setDistrict(district1);
        departmentRepository.save(department);
        List<Department> departments1 = departmentService.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);
        assertThat(departments1).size().isEqualTo(3);
    }

    @Test
    public void getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser() {
        logger.info("准备基础测试数据");
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();

        logger.info("执行数据准备");
        departmentServiceTestData.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(workflowNode, mandatoryInstrument);
        List<Department> departments1 = departmentService.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(workflowNode, mandatoryInstrument);
        Department department1 =departments1.get(0);
        assertThat(department1.isCheckAbility()).isEqualTo(true);
    }

    @Test
    @Transactional
    public void pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts() {
        String pinyin = CommonService.getRandomStringByLength(11);
        departmentServiceTestData.pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(pinyin);

        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Department> departments = departmentService.pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(pinyin, pageRequest);
        assertThat(departments.getContent().size()).isEqualTo(10);
        assertThat(departments.getTotalElements()).isEqualTo(41);
    }

}