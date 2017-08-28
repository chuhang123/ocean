package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/8/11.
 * 强制检定类别
 */
public class MandatoryInstrumentTypeControllerTest extends ControllerTest{
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentTypeControllerTest.class.getName());
    @Autowired
    InstrumentFirstLevelTypeService instrumentFirstLevelTypeService;// 一级类别
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Autowired
    MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;    // 强制检定二级类别
    @Test
    public void getAllByInstrumentFirstLevelTypeId() throws Exception {
        logger.info("新建一个一级类别");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("新建2个二级强检类别");
        for (int i = 0; i < 2; i++) {
            MandatoryInstrumentType mandatoryInstrumentType = new MandatoryInstrumentType();
            mandatoryInstrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
            mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);
        }
        logger.info("发起请求");
        String url = "/MandatoryInstrumentType/getAllByInstrumentFirstLevelTypeId/" + instrumentFirstLevelType.getId().toString();
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentType_getAllByInstrumentFirstLevelTypeId", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言查询的数据条数为2");
        JSONArray jsonArray = JSONArray.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonArray.size()).isEqualTo(2);
    }
}
