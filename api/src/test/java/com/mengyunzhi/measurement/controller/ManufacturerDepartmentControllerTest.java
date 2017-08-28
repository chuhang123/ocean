package com.mengyunzhi.measurement.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by panjie on 17/7/11.
 * 生产企业
 */

public class ManufacturerDepartmentControllerTest extends ControllerTest {
    @Test
    public void getTop10ByNameContaining() throws Exception {
        this.mockMvc.perform(get("/ManufacturerDepartment/getTop10ByNameContaining/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
        .andDo(document("ManufacturerDepartment_getTop10ByNameContaining", preprocessResponse(prettyPrint())));
    }


}
