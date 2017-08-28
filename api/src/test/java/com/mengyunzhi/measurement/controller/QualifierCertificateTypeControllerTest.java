package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
import com.mengyunzhi.measurement.repository.QualifierCertificateType;
import com.mengyunzhi.measurement.repository.QualifierCertificateTypeRepository;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/6/11.
 */
public class QualifierCertificateTypeControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(QualifierCertificateTypeControllerTest.class.getName());
    @Autowired
    private QualifierCertificateTypeRepository qualifierCertificateTypeRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void save() throws Exception {
        logger.info("---- 测试save方法----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hello");
        jsonObject.put("pinyin", "拼音");

        MvcResult result = this.mockMvc.perform(post("/QualifierCertificateType/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("QualifierCertificateType_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为json对象，并断言其id存在，且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言资格证类别实体存在且不为空");
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- 测试getAll方法 ----");
        logger.info("创建一个资格证类别实体");
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        logger.info("保存该实体");
        qualifierCertificateTypeRepository.save(qualifierCertificateType);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/QualifierCertificateType/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("QualifierCertificateType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isGreaterThan(0);
    }

    @Test
    public void update() throws Exception {
        logger.info("----- 更新实体 -----");
        logger.info("新建一个实体");
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        logger.info("保存该实体");
        qualifierCertificateTypeRepository.save(qualifierCertificateType);

        logger.info("修改实体, 并断言状态码返回204");
        qualifierCertificateType.setName("hello");
        this.mockMvc.perform(put("/QualifierCertificateType/update/" + qualifierCertificateType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(qualifierCertificateType).toString()))
                .andDo(document("qualifierCertificateType_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(qualifierCertificateTypeRepository.findOne(qualifierCertificateType.getId()).getName()).isEqualTo("hello");
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        logger.info("保存该实体");
        qualifierCertificateTypeRepository.save(qualifierCertificateType);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/QualifierCertificateType/delete/" + qualifierCertificateType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(qualifierCertificateType).toString()))
                .andDo(document("QualifierCertificateType_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(qualifierCertificateTypeRepository.findOne(qualifierCertificateType.getId())).isNull();
    }

    @Test
    public void getAllByDisciplineId() throws Exception {
        logger.info("新建一个学科实体");
        Discipline discipline = new Discipline();
        discipline.setName("discipline");
        discipline.setPinyin("discipline");
        disciplineRepository.save(discipline);

        logger.info("新建一个资格证类别实体A");
        QualifierCertificateType qualifierCertificateTypeA = new QualifierCertificateType();
        qualifierCertificateTypeA.setDiscipline(discipline);
        qualifierCertificateTypeA.setName("qualifierCertificateTypeA");
        qualifierCertificateTypeRepository.save(qualifierCertificateTypeA);

        logger.info("新建一个资格证类别实体B");
        QualifierCertificateType qualifierCertificateTypeB = new QualifierCertificateType();
        qualifierCertificateTypeB.setDiscipline(discipline);
        qualifierCertificateTypeB.setName("qualifierCertificateTypeB");
        qualifierCertificateTypeRepository.save(qualifierCertificateTypeB);

        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get("/QualifierCertificateType/getAllByDisciplineId/" + discipline.getId().toString() + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("QualifierCertificateType_getAllByDisciplineId",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info("获取返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(jsonObject.get("totalPages"));

        logger.info("删除用于测试的实体");
        qualifierCertificateTypeRepository.delete(qualifierCertificateTypeA);
        qualifierCertificateTypeRepository.delete(qualifierCertificateTypeB);
        disciplineRepository.delete(discipline);
    }

}
