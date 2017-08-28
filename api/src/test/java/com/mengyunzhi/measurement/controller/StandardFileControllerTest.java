package com.mengyunzhi.measurement.controller;

import net.sf.json.JSONObject;
import org.junit.Test;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Created by panjie on 17/5/5.
 */

public class StandardFileControllerTest extends ControllerTest{

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("code", "代码");
        jsonObject.put("name", "器具名称");
        jsonObject.put("certificateNum", "考核证表编号");
        jsonObject.put("checkDate", "考核证表编号");
        jsonObject.put("issueDate", "颁发日期");
        jsonObject.put("validityDate", "有效期至");

        this.mockMvc.perform(post("/StandardFile/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardFile_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/StandardFile/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardFile_getAll", preprocessResponse(prettyPrint())));
    }
}