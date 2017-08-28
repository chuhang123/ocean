package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DisciplineService;
import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
public class InstrumentFirstLevelTypeControllerTest extends ControllerTest {
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;  // 一级器具类别
    @Autowired
    DisciplineRepository disciplineRepository;  // 学科类别
    @Test
    public void save() throws Exception {
        InstrumentFirstLevelType instrumentFirstLevelType =
                InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        MvcResult mockMvc = this.mockMvc.perform(
                post("/InstrumentFirstLevelType/")
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.fromObject(instrumentFirstLevelType).toString()))
                .andDo(document("InstrumentFirstLevelType_save", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201))
                .andReturn();
        String content = mockMvc.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        int id = (int) jsonObject.get("id");
        assertThat(id).isGreaterThan(0);
        instrumentFirstLevelTypeRepository.delete((long) id);
    }

    @Test
    public void getAllByDisciplineId() throws Exception {
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);

        for (int i = 0; i < 10; i++) {
            InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
            instrumentFirstLevelType.setDiscipline(discipline);
            instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        }

        MvcResult mockMvc = this.mockMvc.perform(
                get("/InstrumentFirstLevelType/getAllByDisciplineId/" + discipline.getId().toString())
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("InstrumentFirstLevelType_getAllByDisciplineId", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200))
                .andReturn();

        assertThat(JSONArray.fromObject(mockMvc.getResponse().getContentAsString()).size()).isEqualTo(10);
    }

}