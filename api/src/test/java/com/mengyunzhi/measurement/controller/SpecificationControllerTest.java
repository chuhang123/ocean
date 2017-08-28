package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.Specification;
import com.mengyunzhi.measurement.repository.SpecificationRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/6/28.
 * 规格型号 C层test
 */
public class SpecificationControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(SpecificationControllerTest.class.getName());

    @Autowired
    private SpecificationRepository specificationRepository;

    @Test
    public void save() throws Exception {
        logger.info("---- save方法测试 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cost", 2L);
        jsonObject.put("value", "value");
        MvcResult result = this.mockMvc.perform(post("/Specification/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("Specification_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为json对象，并断言其ID存在，切不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("---- update方法测试 ----");
        logger.info("新建一个规格型号实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        logger.info("修改实体，并断言状态码返回204");
        specification.setCost(1L);
        this.mockMvc.perform(put("/Specification/update/" + specification.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(specification).toString()))
                .andDo(document("specification_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(specificationRepository.findOne(specification.getId()).getCost()).isEqualTo(1L);
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建一个规格型号实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/Specification/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("specification_getAll", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度与实体仓库中对象长度相等");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<Specification> specifications = (List<Specification>) specificationRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(specifications.size());
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("---- 删除实体 ----");
        logger.info("新建一个实体");
        Specification specification = new Specification();
        specification.setValue("value");
        logger.info("保存该实体");
        specificationRepository.save(specification);

        logger.info("发送模拟数据，删除实体中的一条数据，并断言状态码为204");
        this.mockMvc.perform(delete("/Specification/delete/" + specification.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(specification).toString()))
                .andDo(document("Specification_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

    }

}