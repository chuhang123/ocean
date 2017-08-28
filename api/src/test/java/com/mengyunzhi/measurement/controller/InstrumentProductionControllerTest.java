package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-7-7.
 * 器具生产信息C层测试文件
 */
public class InstrumentProductionControllerTest extends ControllerTest{
    static private Logger logger = Logger.getLogger(InstrumentProductionControllerTest.class.getName());
    @Autowired      //部门实体仓库
    private DepartmentRepository departmentRepository;
    @Autowired      //器具类别实体仓库
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired      //器具生产信息实体仓库
    private InstrumentProductionRepository instrumentProductionRepository;
    //用于测试的数据
    private Department department;
    private InstrumentType instrumentType;
    private Set<InstrumentProduction> instrumentProductions = new HashSet<>();

//    @Before
//    public void addData() {
//
//
//    }
//    @After
//    public void deleteData() {
//
//    }
    @Test
    public void getByDepartmentIdAndLicenseNum() throws Exception {
        logger.info("创建一个部门");
        department = new Department();
        departmentRepository.save(department);
        logger.info("创建一个器具类别");
        instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentTypeRepository.save(instrumentType);
        logger.info("准备测试数据");
        for (int i = 0; i < 20; i++) {
            InstrumentProduction instrumentProduction = new InstrumentProduction();
            instrumentProduction.setLicenseNum("abc" + i);
            instrumentProduction.setManufacturerDepartment(department);
            instrumentProduction.setInstrumentType(instrumentType);
            instrumentProductions.add(instrumentProduction);
        }
        instrumentProductionRepository.save(instrumentProductions);
        logger.info("------------test--------------");
        String content = this.mockMvc.perform(get("/InstrumentProduction/getByDepartmentIdAndLicenseNum/" + department.getId().toString() + "/abc")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("InstrumentProduction_getByDepartmentIdAndLicenseNum", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray jsonArray = JSONArray.fromObject(content);
        logger.info("断言数组长度是10");
        assertThat(jsonArray.size()).isEqualTo(10);
        logger.info("删除实体");
        departmentRepository.delete(department);
        instrumentTypeRepository.delete(instrumentType);
        instrumentProductionRepository.delete(instrumentProductions);
    }

}