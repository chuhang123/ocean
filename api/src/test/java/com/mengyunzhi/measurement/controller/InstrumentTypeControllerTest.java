package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DisciplineService;
import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.Service.InstrumentTypeService;
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
 * Created by zhangjiahao on 2017/6/26.
 * 器具类别 C层test
 */
public class InstrumentTypeControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(InstrumentTypeControllerTest.class.getName());
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired private DisciplineRepository disciplineRepository;
    @Autowired private MeasureScaleRepository measureScaleRepository;   // 测量范围
    @Autowired private InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository; // 一级学科类别
    @Test
    public void save() throws Exception {
        logger.info("---- save方法测试 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangjiahao");
        jsonObject.put("pinyin", "Mr.Zhang");

        MvcResult result = this.mockMvc.perform(post("/InstrumentType/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("InstrumentType_save", preprocessResponse(prettyPrint())))
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
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        java.util.Set<MeasureScale> measureScales = new HashSet<>();     //关联测量范围
        MeasureScale measureScale = new MeasureScale();
        measureScales.add(measureScale);
        instrumentType.setMeasureScales(measureScales);

        instrumentTypeRepository.save(instrumentType);

        logger.info("修改实体，并断言状态码返回204");
        instrumentType.setName("hello");
        measureScales.remove(measureScale);
        instrumentType.setMeasureScales(measureScales);

        this.mockMvc.perform(put("/InstrumentType/update/" + instrumentType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(instrumentType).toString()))
                .andDo(document("instrumentType_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(instrumentTypeRepository.findOne(instrumentType.getId()).getName()).isEqualTo("hello");
        logger.info("断言数据更新成功");
        assertThat(instrumentType.getMeasureScales().size()).isEqualTo(0);
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建并保存一个器具类别实体");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentTypeRepository.save(instrumentType);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/InstrumentType/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("instrumentType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度与实体仓库中对象长度相等");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<InstrumentType> instrumentTypes = (List<InstrumentType>) instrumentTypeRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(instrumentTypes.size());
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        logger.info("保存该实体");
        instrumentTypeRepository.save(instrumentType);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/InstrumentType/delete/" + instrumentType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(instrumentType).toString()))
                .andDo(document("InstrumentType_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(instrumentTypeRepository.findOne(instrumentType.getId())).isNull();
    }

    @Test
    public void getTest() throws Exception {
        logger.info("新建一个实体并查询");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentTypeRepository.save(instrumentType);

        MvcResult mvcResult = this.mockMvc.perform(get("/InstrumentType/" + instrumentType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentType_get",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonObject.get("id").toString()).isEqualTo(instrumentType.getId().toString());
        instrumentTypeRepository.delete(instrumentType);
    }

    @Test
    public void pageByDisciplineId() throws Exception {
        logger.info("添加1个学科类别");
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("添加10个器具类别");
        for (int i = 0; i < 10; i++) {
            InstrumentType instrumentType = new InstrumentType();
            instrumentType.setName("name");
            instrumentType.setPinyin("pinyin");
            instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
            instrumentTypeRepository.save(instrumentType);
        }

        logger.info("获取返回值");
        String content = this.mockMvc.perform(get("/InstrumentType/pageByDisciplineId/" + discipline.getId().toString() + "?page=2&size=3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentType_pageByDisciplineId",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(10);
        assertThat(jsonObject.get("last")).isEqualTo(false);
        assertThat(jsonObject.get("totalPages")).isEqualTo(4);
    }

    @Test
    public void allByDisciplineId() throws Exception {
        logger.info("添加1个学科类别");
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("添加2个器具类别");
        HashSet<InstrumentType> instrumentTypes = new HashSet<>();
        InstrumentType instrumentType = InstrumentTypeService.getOneInstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);

        instrumentTypes.add(instrumentType);
        InstrumentType instrumentType1 = InstrumentTypeService.getOneInstrumentType();
        instrumentType1.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypes.add(instrumentType1);
        instrumentTypeRepository.save(instrumentTypes);

        logger.info("获取返回值");
        String content = this.mockMvc.perform(get("/InstrumentType/allByDisciplineId/" + discipline.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentType_allByDisciplineId",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(2);
        logger.info("删除数据(避免对事务的依赖)");

        disciplineRepository.delete(discipline);
        instrumentTypeRepository.delete(instrumentTypes);
    }

    @Test
    public void getByMeasureScaleId() throws Exception {
        logger.info("新建器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("qijuleibie");
        instrumentType.setPinyin("pinyin");
        instrumentTypeRepository.save(instrumentType);
        logger.info("新建测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(instrumentType);
        measureScaleRepository.save(measureScale);
        logger.info("测试");

        this.mockMvc.perform(get("/InstrumentType/allByDisciplineId/" + measureScale.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentType_allByDisciplineId",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());

        logger.info("删除数据");
        instrumentTypeRepository.delete(instrumentType.getId());
        measureScaleRepository.delete(measureScale.getId());
    }
}