package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.AccuracyDisplayName;
import com.mengyunzhi.measurement.repository.AccuracyDisplayNameRepository;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/6/22.
 * 精度显示名称 C层测试
 */
public class AccuracyDisplayNameControllerTest extends  ControllerTest{
    private Logger logger = Logger.getLogger(AccuracyDisplayNameControllerTest.class.getName());
    @Autowired
    private AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建一个精度显示名称实体");
        AccuracyDisplayName accuracyDisplayName = new AccuracyDisplayName();
        logger.info("保存该实体");
        accuracyDisplayNameRepository.save(accuracyDisplayName);
        logger.info("模拟发送数据");
        MvcResult result = this.mockMvc.perform(get("/AccuracyDisplayName/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("AccuracyDisplayName_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<AccuracyDisplayName> accuracyDisplayNames = (List<AccuracyDisplayName>) accuracyDisplayNameRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(accuracyDisplayNames.size());

    }

}