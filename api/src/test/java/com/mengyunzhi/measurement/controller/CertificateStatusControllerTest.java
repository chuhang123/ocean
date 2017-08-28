package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CertificateStatusService;
import com.mengyunzhi.measurement.repository.CertificateStatus;
import com.mengyunzhi.measurement.repository.CertificateStatusRepository;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/8/3.
 * 证书状态 C层测试
 */
public class CertificateStatusControllerTest extends ControllerTest{
    private Logger logger = Logger.getLogger(CertificateStatusControllerTest.class.getName());
    @Autowired
    private CertificateStatusRepository certificateStatusRepository;
    @Autowired
    private CertificateStatusService certificateStatusService;
    @Test
    public void getAll() throws Exception {
        logger.info("---- 测试getAll方法 ----");
        List<CertificateStatus> certificateStatuses1 = (List<CertificateStatus>) certificateStatusRepository.findAll();
        CertificateStatus certificateStatus = new CertificateStatus();
        certificateStatusRepository.save(certificateStatus);
        MvcResult result = this.mockMvc.perform(get("/CertificateStatus/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Certificate_getAll",preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为JSONArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(certificateStatuses1.size()+1);
    }

}