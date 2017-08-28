package com.mengyunzhi.measurement.controller;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-7-27.
 */
public class CheckResultControllerTest extends ControllerTest{
    static private Logger logger = Logger.getLogger(CheckResultControllerTest.class.getName());
    @Test
    public void getCheckResultTree() throws Exception {
        logger.info("发送请求");
        String content = this.mockMvc.perform(get("/CheckResult/getCheckResultTree")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("CheckResult_getCheckResultTree", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();
    }

}