package com.mengyunzhi.measurement.controller;

import net.sf.json.JSONObject;
import org.junit.Test;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import org.springframework.beans.factory.annotation.Autowired;

import static javafx.scene.input.KeyCode.O;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Created by zhangjiahao on 2017/5/10.
 * 非强检器具-检定信息C层单元测试
 */
public class OptionalCheckDetailControllerTest extends ControllerTest {
    @Test
    public void saveOptionalCheckDetail() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("certificateNum", "13956231");
        jsonObject.put("checkResult", "合格");
        jsonObject.put("checkTime", "2017.02.08");
        jsonObject.put("checkUnit", "河北工业大学");
        jsonObject.put("checker", "张先生");
        jsonObject.put("examiner", "张佳明");
        jsonObject.put("indicationDeviation", "5cm");
        jsonObject.put("validityTime", "2017.9.8");

        this.mockMvc.perform(post("/OptionalCheckDetail/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("OptionalCheckDetail_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/OptionalCheckDetail/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("OptionalCheckDetail_getAll", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllByOptionalIntergrated() throws Exception {
        //获取一个OptionalIntergrated非强检器具
        OptionalIntegrated optionalIntegrated = new OptionalIntegrated();
        //java对象转化为json对象
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(optionalIntegrated);
        //测试
        this.mockMvc.perform(post("/OptionalCheckDetail/getAllByOptionalIntergrated")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("OptionalCheckDetail_getAllByOptionalIntergrated", preprocessResponse(prettyPrint())));
    }

}