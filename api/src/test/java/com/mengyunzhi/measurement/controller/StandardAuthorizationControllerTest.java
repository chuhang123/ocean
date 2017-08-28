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
 * Created by liming on 17-5-7.
 */

public class StandardAuthorizationControllerTest extends ControllerTest {

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("technical", "技术机构");
        jsonObject.put("name", "器具名称");
        jsonObject.put("rank", "准确度等级");
        jsonObject.put("measureScale", "测量范围");
        jsonObject.put("address", "地址");
        jsonObject.put("phone", "联系电话");

        this.mockMvc.perform(post("/StandardAuthorization/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardAuthorization_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/StandardAuthorization/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardAuthorization_getAll", preprocessResponse(prettyPrint())));
    }
}