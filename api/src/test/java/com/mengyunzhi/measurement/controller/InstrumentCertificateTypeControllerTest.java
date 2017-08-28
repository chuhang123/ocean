package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentCertificateTypeService;
import com.mengyunzhi.measurement.repository.InstrumentCertificateType;
import com.mengyunzhi.measurement.repository.InstrumentCertificateTypeRepository;
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
 * Created by zhangjiahao on 2017/8/3.
 * 器具证书类型C层测试文件
 */
public class InstrumentCertificateTypeControllerTest extends ControllerTest{
    private Logger logger = Logger.getLogger(InstrumentCertificateTypeControllerTest.class.getName());
    @Autowired
    private InstrumentCertificateTypeRepository instrumentCertificateTypeRepository;
    @Autowired
    private InstrumentCertificateTypeService instrumentCertificateTypeService;

    @Test
    public void getAll() throws Exception {
        logger.info("---- 测试getAll方法 ----");
        List<InstrumentCertificateType> instrumentCertificateTypes = (List<InstrumentCertificateType>) instrumentCertificateTypeRepository.findAll();
        logger.info("新建一个器具证书类型");
        InstrumentCertificateType instrumentCertificateType = new InstrumentCertificateType();
        instrumentCertificateTypeRepository.save(instrumentCertificateType);

        MvcResult mvcResult = this.mockMvc.perform(get("/InstrumentCertificateType/getAll")
                .contentType("application/json")
                .header("x-auth-token",xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentCertificateType_getAll",preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(instrumentCertificateTypes.size()+1);

    }

}