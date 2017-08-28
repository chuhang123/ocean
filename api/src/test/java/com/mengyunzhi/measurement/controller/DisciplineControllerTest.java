package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.Accuracy;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
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
 * Created by zhangjiahao on 2017/6/19.
 * 学科
 */
public class DisciplineControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(DisciplineControllerTest.class.getName());
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建一个学科类别实体");
        Discipline discipline = new Discipline();
        logger.info("保存该实体");
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        disciplineRepository.save(discipline);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/Discipline/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Discipline_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<Discipline> disciplines = (List<Discipline>) disciplineRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(disciplines.size());
    }

    @Test
    public void save() throws Exception {
        logger.info("---- 测试save方法 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangjiahao");
        jsonObject.put("pinyin", "MR.zhang");

        MvcResult result = this.mockMvc.perform(post("/Discipline/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Discipline_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为json对象，并断言其id存在，且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言学科类别实体存在且不为空");
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("----- 更新实体 -----");
        logger.info("新建一个学科类别实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        logger.info("保存该实体");
        disciplineRepository.save(discipline);

        logger.info("修改实体, 并断言状态码返回204");
        discipline.setName("hello");
        this.mockMvc.perform(put("/Discipline/update/" + discipline.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(discipline).toString()))
                .andDo(document("Discipline_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(disciplineRepository.findOne(discipline.getId()).getName()).isEqualTo("hello");
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        logger.info("保存该实体");
        disciplineRepository.save(discipline);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/Discipline/delete/" + discipline.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(discipline).toString()))
                .andDo(document("Discipline_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(disciplineRepository.findOne(discipline.getId())).isNull();
    }

    /**
     * 获取某个实体
     * @throws Exception
     * @author panjie
     */
    @Test
    public void getById() throws Exception {
        logger.info("新建一个带有精度的实体，并断言查询的到结果中包含精度的个数与新增加的相同");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        discipline.addAccruacy(accuracy);
        disciplineRepository.save(discipline);
        MvcResult mvcResult = this.mockMvc.perform(get("/Discipline/" + discipline.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Discipline_get", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        JSONArray jsonArray = (JSONArray) jsonObject.get("accuracies");
        assertThat(jsonArray.size()).isEqualTo(1);
    }
}