package com.mengyunzhi.measurement.controller;

import net.sf.json.JSONObject;
import org.junit.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/5/5.
 */
public class CertifiedProductControllerTest extends ControllerTest {

    @Test
    public void saveMeasuringdeviceEnterpriseFile() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region", "唐山市");
        jsonObject.put("county", "乐亭县");
        jsonObject.put("name", "器具名称");
        jsonObject.put("rank", "准备度等级");
        jsonObject.put("measureScale", "测试范围");
        jsonObject.put("manufactureUnit", "制造单位");
        jsonObject.put("licenseNum", "许可证号");
        jsonObject.put("issueDate", "发证日期");
        jsonObject.put("validityDate", "有效期至");
        jsonObject.put("checkDate", "检定日期");

        this.mockMvc.perform(post("/CertifiedProduct/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("CertifiedProduct_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        String url = "CertifiedProduct/getAll";
        this.mockMvc.perform(get("/" + url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("CertifiedProduct_getAll", preprocessResponse(prettyPrint())));
    }
}