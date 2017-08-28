package com.mengyunzhi.measurement.controller;

import io.swagger.util.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Created by panjie on 17/6/7.
 * 前台菜单
 */
public class WebAppMenuControllerTest extends ControllerTest {
    @Test
    public void getAll() throws Exception {
        MvcResult mcvResult = this.mockMvc.perform(get("/WebAppMenu/")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("WebAppMenu",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        String content = mcvResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isGreaterThan(8);
        return;

    }

}