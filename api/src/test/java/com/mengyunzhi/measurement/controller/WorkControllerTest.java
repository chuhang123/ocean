package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyService;
import com.mengyunzhi.measurement.Service.WorkService;
import com.mengyunzhi.measurement.Service.WorkflowNodeService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/18.
 * 工作
 */
public class WorkControllerTest extends ControllerTest {
    private static final Logger logger = Logger.getLogger(WorkControllerTest.class.getName());
    @Autowired
    private WorkRepository workRepository;   // 工作
    @Autowired
    private ApplyRepository applyRepository;    // 申请
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;
    @Autowired
    private WorkflowNodeService workflowNodeService;
    @Autowired @Qualifier("ApplyService") private ApplyService applyService;

    @Test
    public void updateToDoingById() throws Exception {
        Work work = WorkService.getOneWork();
        workRepository.save(work);
        this.mockMvc.perform(patch("/Work/updateToDoingById/" + work.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .header("Origin:", "http://www.test.com"))
                .andExpect(status().is(204))
                .andDo(document("Work_updateToDoingById", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllByApplyId() throws Exception {
        Apply apply = applyService.getOneApply();
        applyRepository.save(apply);
        Work work = WorkService.getOneWork();
        work.setApply(apply);
        workRepository.save(work);
        MvcResult mvcResult = this.mockMvc.perform(
                get("/Work/getAllByApplyId/" + apply.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("Work_getAllByApplyId", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(1);
    }

    @Test
    public void auditById() throws Exception {
        User user = userService.loginWithOneUser();
        logger.info("新建一个新申请");
        Apply apply = applyService.getOneApply();

        applyRepository.save(apply);
        logger.info("新建一个新工作");
        Work work = new Work();
        work.setApply(apply);
        work.setAuditDepartment(user.getDepartment());
        workService.saveNewWork(work);

        logger.info("新建一个工作流经点");
        WorkflowNode workflowNode = workflowNodeService.getOneCompleteWorkflowNode();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("department", "{id:" + user.getDepartment().getId().toString() + "}");
        jsonObject.put("type", WorkService.SEND_TO_NEXT_DEPARTMENT);
        jsonObject.put("opinion", "这里是审核信息");
        jsonObject.put("nextWorkflowNode", "{id:" + workflowNode.getId().toString() + "}");

        String content = jsonObject.toString();
        this.loginByUser(user);
        this.mockMvc
                .perform(
                        patch("/Work/auditById/" + work.getId().toString())
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header("x-auth-token", xAuthToken)
                                .content(content))
                .andExpect(status().is(204))
                .andDo(document("Work_auditById", preprocessResponse(prettyPrint())));
        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getDone()).isTrue();
    }
}
