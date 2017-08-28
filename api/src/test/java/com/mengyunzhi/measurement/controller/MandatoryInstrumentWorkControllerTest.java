package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请
 */
public class MandatoryInstrumentWorkControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(MandatoryInstrumentWorkControllerTest.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkRepository workRepository;   // 工作
    @Autowired
    private DepartmentRepository departmentRepository;   // 部门
    @Autowired
    private WorkflowNodeRepository workflowNodeRepository;   // 工作流
    @Autowired
    private ApplyRepository applyRepository;        // 申请

    @Test
    public void pageOfCurrentLoginUser() throws Exception {
        User user = userService.loginWithOneUser();
        this.loginByUser(user);
        logger.info("新建工作");
        MvcResult mvcResult = this.mockMvc
                .perform(get("/MandatoryInstrumentWork/pageOfCurrentLoginUser?page=1&size=2")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentWork_pageOfCurrentLoginUser", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
    }
}
