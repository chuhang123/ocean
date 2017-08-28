package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.Service.WorkflowNodeService;
import com.mengyunzhi.measurement.Service.WorkflowNodeServiceTestData;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/7/12.
 */
public class WorkflowNodeControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(WorkflowNodeControllerTest.class.getName());
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
    public void getAllByPreWorkflowNodeId() throws Exception {
        logger.info("---- getAllByPreWorkflowNodeId方法测试 ----");
        logger.info("新建一个工作流节点A");
        WorkflowNode workflowNodeA = new WorkflowNode();
        workflowNodeA.setName("nameA");
        workflowNodeRepository.save(workflowNodeA);

        logger.info("新建一个工作流节点B，并将A作为其前一个工作流节点");
        WorkflowNode workflowNodeB = new WorkflowNode();
        workflowNodeB.setName("nameB");
        workflowNodeB.setPreWorkflowNode(workflowNodeA);
        workflowNodeRepository.save(workflowNodeB);

        logger.info("新建一个工作流节点C，并将A作为其前一个工作流节点");
        WorkflowNode workflowNodeC = new WorkflowNode();
        workflowNodeC.setName("nameC");
        workflowNodeC.setPreWorkflowNode(workflowNodeA);
        workflowNodeRepository.save(workflowNodeC);

        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/WorkflowNode/getAllByPreWorkflowNodeId/" + workflowNodeA.getId())
                .header("x-auth-token", xAuthToken)
                .contentType("application/json"))
                .andDo(document("WorkflowNode_getAllByPreWorkflowNodeId", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(2);
        ;
    }

    @Test
    public void getTopOneByApplyTypeIdOfCurrentUser() throws Exception {
        // 进行模拟登录
        User user = userService.loginWithOneUser();
        this.loginByUser(user);

        // 准备数据
        ApplyType applyType = applyTypeService.getOneApplyType();
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        workflowNodeServiceTestData.getTopOneByApplyTypeIdOfCurrentUser(user, applyType, workflowNode);

        // 模拟请求并断言
        String url = "/WorkflowNode/getTopOneByApplyTypeIdOfCurrentUser/" + applyType.getId().toString();
        MvcResult result = this.mockMvc
                .perform(get(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType("application/json"))
                .andDo(document("WorkflowNode_getTopOneByApplyTypeIdOfCurrentUser", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(result.getResponse().getContentAsString());
        int id = (int) jsonObject.get("id");
        assertThat(Long.valueOf(id)).isEqualTo(workflowNode.getId());
    }

}