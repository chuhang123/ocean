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

public class PersonalFileControllerTest extends ControllerTest {

    @Test
    public void savePersonalFile() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("technical", "123");
        jsonObject.put("User", "132");
        jsonObject.put("generative", "generative");
        jsonObject.put("name", "姓名");
        jsonObject.put("age", "age");
        jsonObject.put("edubg", "学历");
        jsonObject.put("title", "职称");
        jsonObject.put("occupation", "从业年限");
        jsonObject.put("qualifierCertificate", "资格证");
        jsonObject.put("authorizeItem", "授权项目");
        jsonObject.put("issueUnit", "发证单位");
        jsonObject.put("issueTime", "发证日期");
        jsonObject.put("validityTime", "有效期至");

        this.mockMvc.perform(post("/PersonalFile/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("PersonalFile_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/PersonalFile/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("PersonalFile_getAll", preprocessResponse(prettyPrint())));
    }
}