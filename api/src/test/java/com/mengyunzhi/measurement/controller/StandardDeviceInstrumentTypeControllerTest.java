package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DisciplineService;
import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.Service.StandardDeviceInstrumentTypeService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-7-27.
 * 标准器类别Controller
 */
public class StandardDeviceInstrumentTypeControllerTest extends ControllerTest{
    static private Logger logger = Logger.getLogger(StandardDeviceInstrumentTypeControllerTest.class.getName());
    @Autowired protected StandardDeviceInstrumentTypeRepository standardDeviceInstrumentTypeRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;
    @Autowired protected InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Autowired private DisciplineService disciplineService;         // 学科类别
    @Test
    public void save() throws Exception {
        logger.info("---- save方法测试 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangjiahao");
        jsonObject.put("pinyin", "Mr.Zhang");

        MvcResult result = this.mockMvc.perform(post("/StandardDeviceInstrumentType/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("StandardDeviceInstrumentType_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为json对象，并断言其id存在，且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言器具类别实体存在且不为空");
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("---- update方法测试 ----");
        logger.info("新建一个器具类别实体并保存");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("name");
        standardDeviceInstrumentType.setPinyin("pinyin");
        java.util.Set<MeasureScale> measureScales = new HashSet<>();     //关联测量范围
        MeasureScale measureScale = new MeasureScale();
        measureScales.add(measureScale);
        standardDeviceInstrumentType.setMeasureScales(measureScales);

        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);

        logger.info("修改实体，并断言状态码返回204");
        standardDeviceInstrumentType.setName("hello");
        measureScales.remove(measureScale);
        standardDeviceInstrumentType.setMeasureScales(measureScales);

        this.mockMvc.perform(put("/StandardDeviceInstrumentType/update/" + standardDeviceInstrumentType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(standardDeviceInstrumentType).toString()))
                .andDo(document("StandardDeviceInstrumentType_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(standardDeviceInstrumentTypeRepository.findOne(standardDeviceInstrumentType.getId()).getName()).isEqualTo("hello");
        logger.info("断言数据更新成功");
        assertThat(standardDeviceInstrumentType.getMeasureScales().size()).isEqualTo(0);
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建并保存一个器具类别实体");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("name");
        standardDeviceInstrumentType.setPinyin("pinyin");
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/StandardDeviceInstrumentType/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceInstrumentType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度与实体仓库中对象长度相等");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<StandardDeviceInstrumentType> standardDeviceInstrumentTypes = (List<StandardDeviceInstrumentType>) standardDeviceInstrumentTypeRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(standardDeviceInstrumentTypes.size());
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("name");
        standardDeviceInstrumentType.setPinyin("pinyin");
        logger.info("保存该实体");
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/StandardDeviceInstrumentType/delete/" + standardDeviceInstrumentType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(standardDeviceInstrumentType).toString()))
                .andDo(document("StandardDeviceInstrumentType_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(standardDeviceInstrumentTypeRepository.findOne(standardDeviceInstrumentType.getId())).isNull();
    }

    @Test
    public void getTest() throws Exception {
        logger.info("新建一个实体并查询");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("name");
        standardDeviceInstrumentType.setPinyin("pinyin");
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);

        MvcResult mvcResult = this.mockMvc.perform(get("/StandardDeviceInstrumentType/" + standardDeviceInstrumentType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("StandardDeviceInstrumentType_get",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonObject.get("id").toString()).isEqualTo(standardDeviceInstrumentType.getId().toString());
        standardDeviceInstrumentTypeRepository.delete(standardDeviceInstrumentType);
    }

    @Test
    public void getByMeasureScaleId() throws Exception {
        logger.info("新建器具类别");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("qijuleibie");
        standardDeviceInstrumentType.setPinyin("pinyin");
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);
        logger.info("新建测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(standardDeviceInstrumentType);
        measureScaleRepository.save(measureScale);
        logger.info("测试");

        this.mockMvc.perform(get("/StandardDeviceInstrumentType/getByMeasureScaleId/" + measureScale.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("StandardDeviceInstrumentType_allByDisciplineId",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());

        logger.info("删除数据");
        standardDeviceInstrumentTypeRepository.delete(standardDeviceInstrumentType.getId());
        measureScaleRepository.delete(measureScale.getId());
    }

    @Test
    public void getAllByInstrumentFirstLevelTypeId() throws Exception {
        logger.info("新建一个一级器具类别");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setName("name");
        instrumentFirstLevelType.setPinyin("pinyin");
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        logger.info("新建一个标准器具类别");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("qijuleibie");
        standardDeviceInstrumentType.setPinyin("pinyin");
        standardDeviceInstrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);

        logger.info("测试");
        String content = this.mockMvc.perform(get("/StandardDeviceInstrumentType/getAllByInstrumentFirstLevelTypeId/" + instrumentFirstLevelType.getId())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceInstrumentType_getAllByInstrumentFirstLevelTypeId", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(1);
    }

    @Test
    public void pageByDisciplineId() throws Exception {
        logger.info("新建学科类别");
        Discipline discipline = disciplineService.getOneSavedDiscipline();
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("新建10个标准器器具类别");
        for(int i = 0; i < 10; i++) {
            StandardDeviceInstrumentType standardDeviceInstrumentType = StandardDeviceInstrumentTypeService.getOneStandardDeviceInstrumentType();
            standardDeviceInstrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
            standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);
        }

        String url = "/StandardDeviceInstrumentType/pageByDisciplineId/" + discipline.getId().toString() + "?page=0&size=5";

        logger.info("测试");
        String content = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentType_pageByDisciplineId", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.fromObject(content);
        System.out.println(jsonObject);
        assertThat(jsonObject.get("totalPages")).isEqualTo(2);
        assertThat(jsonObject.get("totalElements")).isEqualTo(10);
    }
}