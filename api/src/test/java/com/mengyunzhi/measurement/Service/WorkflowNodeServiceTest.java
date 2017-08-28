package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WorkflowNode;
import com.mengyunzhi.measurement.repository.WorkflowNodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mengyunzhi.measurement.repository.*;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by zhangjiahao on 2017/7/11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowNodeServiceTest extends ServiceTest {

    static private Logger logger = Logger.getLogger(WorkflowNodeServiceTest.class.getName());
    @Autowired
    protected UserService userService;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected DistrictTypeRepository districtTypeRepository;
    @Autowired
    protected DistrictRepository districtRepository;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    protected WebAppMenuRepository webAppMenuRepository;
    @Autowired
    protected WorkflowTypeRepository workflowTypeRepository;
    @Autowired
    protected ApplyTypeRepository applyTypeRepository;
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;
    @Autowired
    protected WorkflowNodeService workflowNodeService;
    @Autowired
    private ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    WorkflowNodeServiceTestData workflowNodeServiceTestData; // 测试数据

    @Test
    public void getAllByWebAppMenuIdOfCurrentUser() throws Exception {
        logger.info("新建一个用户");
        User user = new User();
        logger.info("为用户设置department");
        DepartmentType departmentType = new DepartmentType();
        departmentTypeRepository.save(departmentType);
        Department department = new Department();
        department.setName("生产");
        department.setDepartmentType(departmentType);

        logger.info("为区域设置区域类型");
        DistrictType districtType = new DistrictType();
        districtType.setName("区/县");
        districtTypeRepository.save(districtType);
        District district = new District();
        district.setName("涞水县");
        district.setDistrictType(districtType);
        districtRepository.save(district);

        department.setDistrict(district);
        departmentRepository.save(department);

        user.setDepartment(department);
        userService.setCurrentLoginUser(user);

        logger.info("新建一个前台菜单");
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuRepository.save(webAppMenu);
        logger.info("新建一个工作流类型");
        WorkflowType workflowType = new WorkflowType();
        workflowTypeRepository.save(workflowType);
        logger.info("新建一个申请类型");
        ApplyType applyType = new ApplyType();
        applyType.setWorkflowType(workflowType);
        applyType.setWebAppMenu(webAppMenu);
        applyTypeRepository.save(applyType);

        logger.info("新建一个工作流节点");
        WorkflowNode workflowNode = new WorkflowNode();
        workflowNode.setDepartmentType(departmentType);
        workflowNode.setDistrictType(districtType);
        workflowNode.setWorkflowType(workflowType);
        workflowNodeRepository.save(workflowNode);

        logger.info("测试并断言");
        List<WorkflowNode> workflowNodes = workflowNodeService.getAllByWebAppMenuIdOfCurrentUser(webAppMenu.getId());
        assertThat(workflowNodes.size()).isEqualTo(1);
    }


    @Test
    public void getAllByPreWorkflowNodeIdTest() throws Exception {
        logger.info("通过前一个工作流节点的id获取工作流节点信息");
        logger.info("新建并保存一个工作流节点实体A");
        WorkflowNode workflowNodeA = new WorkflowNode();
        workflowNodeRepository.save(workflowNodeA);

        logger.info("新建工作流节点B，并且将B的前一个工作流节点设置成A");
        WorkflowNode workflowNodeB = new WorkflowNode();
        workflowNodeB.setPreWorkflowNode(workflowNodeA);
        workflowNodeRepository.save(workflowNodeB);

        logger.info("新建工作流节点C，并且将C的前一个工作流节点设置成A");
        WorkflowNode workflowNodeC = new WorkflowNode();
        workflowNodeC.setPreWorkflowNode(workflowNodeA);
        workflowNodeRepository.save(workflowNodeC);

        logger.info("断言以A为前一工作流节点的工作流节点共有两个");
        assertThat(workflowNodeService.getAllByPreWorkflowNodeId(workflowNodeA.getId())).size().isEqualTo(2);
    }

    @Test
    public void getTopOneByApplyTypeIdOfCurrentUser() {
        User user = userService.loginWithOneUser();
        ApplyType applyType = applyTypeService.getOneApplyType();
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        workflowNodeServiceTestData.getTopOneByApplyTypeIdOfCurrentUser(user, applyType, workflowNode);
        WorkflowNode workflowNode1 = workflowNodeService.getTopOneByApplyTypeIdOfCurrentUser(applyType.getId());
        assertThat(workflowNode.getId()).isEqualTo(workflowNode1.getId());
    }

}