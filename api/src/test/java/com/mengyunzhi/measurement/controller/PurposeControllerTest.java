package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.Purpose;
import com.mengyunzhi.measurement.repository.PurposeRepository;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/6/14.
 */
public class PurposeControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(ControllerTest.class.getName());
    @Autowired
    private PurposeRepository purposeRepository;

    @Test
    public void getAll() throws Exception {
        logger.info("---- 测试getAll方法 ----");
        logger.info("新建一个用途实体");
        Purpose purpose =  new Purpose();
        logger.info("保存该实体");
        purposeRepository.save(purpose);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/Purpose/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Purpose_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为JSONArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isGreaterThan(0);
    }

}