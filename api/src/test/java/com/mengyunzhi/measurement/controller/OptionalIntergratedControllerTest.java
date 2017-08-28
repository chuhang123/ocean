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

public class OptionalIntergratedControllerTest extends ControllerTest {

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("User", "器具用户");
        jsonObject.put("name", "器具名称");
        jsonObject.put("status", "状态");
        jsonObject.put("type", "规格型号");
        jsonObject.put("purpose", "用途");
        jsonObject.put("measureScale", "测试范围");
        jsonObject.put("rank", "准备度等级");
        jsonObject.put("manufactureUnit", "制造单位");
        jsonObject.put("factoryNum", "出厂编号");
        jsonObject.put("fixSite", "安装地点");
        jsonObject.put("licenseNum", "许可证号");

        this.mockMvc.perform(post("/OptionalIntergrated/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("OptionalIntergrated_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/OptionalIntergrated/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
//                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("OptionalIntergrated_getAll", preprocessResponse(prettyPrint())));
    }
}