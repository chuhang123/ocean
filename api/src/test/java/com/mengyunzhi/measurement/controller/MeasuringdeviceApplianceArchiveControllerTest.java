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

public class MeasuringdeviceApplianceArchiveControllerTest extends ControllerTest {

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("name", "企业名称");
        jsonObject.put("code", "代码");
        jsonObject.put("discipline", "学科类别");
        jsonObject.put("dissipativeName", "器具名称");
        jsonObject.put("address", "地址");
        jsonObject.put("legalName", "法人姓名");
        jsonObject.put("legalPhone", "电话");
        jsonObject.put("registrantName", "联系人");

        this.mockMvc.perform(post("/MeasuringdeviceApplianceArchive/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MeasuringdeviceApplianceArchive_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/MeasuringdeviceApplianceArchive/getAll" )
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MeasuringdeviceApplianceArchive_getAll", preprocessResponse(prettyPrint())));
    }
}