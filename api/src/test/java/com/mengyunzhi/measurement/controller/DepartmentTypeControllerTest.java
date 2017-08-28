package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
public class DepartmentTypeControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(DepartmentTypeControllerTest.class.getName());
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository; // 部门类型

    // @author :panjie
    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("---- 创建一个部门类型实体 ----");
        DepartmentType departmentType = new DepartmentType();
        System.out.println(departmentType);
        logger.info("保存该实体");
        departmentTypeRepository.save(departmentType);
        logger.info("获取响应信息");
        MvcResult mvcResult = this.mockMvc.perform(get("/DepartmentType/")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DepartmentType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        List<DepartmentType> departmentTypeList = (List<DepartmentType>) departmentTypeRepository.findAll();

        logger.info("断言获取到的数据条数与直接使用repository的实体数相同");
        String content = mvcResult.getResponse().getContentAsString();
        JSONArray JSONArray = net.sf.json.JSONArray.fromObject(content);
        assertThat(JSONArray.size()).isEqualTo(departmentTypeList.size());
    }

}