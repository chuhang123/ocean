package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.Service.ApplyTypeServiceTestData;
import com.mengyunzhi.measurement.Service.WebAppMenuService;
import com.mengyunzhi.measurement.repository.ApplyType;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/5/5.
 */
public class ApplyTypeControllerTest extends ControllerTest {
    @Autowired
    ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    ApplyTypeServiceTestData applyTypeServiceTestData;

    @Test
    public void getOneByWebAppMenuId() throws Exception {
        ApplyType applyType = applyTypeService.getOneApplyType();
        WebAppMenu webAppMenu = WebAppMenuService.getOneWebAppMenu();
        applyTypeServiceTestData.getOneByWebAppMenuId(applyType, webAppMenu);

        String url = "/ApplyType/getOneByWebAppMenuId/" + webAppMenu.getId().toString();
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyType_getOneByWebAppMenuId", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        int id = (int) jsonObject.get("id");
        assertThat(Long.valueOf(id)).isEqualTo(applyType.getId());

    }
}