package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/15.
 * 部门
 */
public class DepartmentControllerTest extends ControllerTest {

    private Logger logger = Logger.getLogger(DepartmentControllerTest.class.getName());
    @Autowired
    private DepartmentServiceTestData departmentServiceTestData; // M层测试数据
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired
    private DistrictService districtService;     // 区域
    @Autowired private MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定器具
    @Autowired private DistrictRepository districtRepository;   // 区域
    @Test
    public void getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser() throws Exception {
        logger.info("准备基础测试数据");
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        logger.info("调用数据准备");
        departmentServiceTestData.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(workflowNode, mandatoryInstrument);

        logger.info("设置登录用户");
        User user = departmentServiceTestData.getUser();
        this.loginByUser(user);

        logger.info("拼接URL并发起请求");
        String url = "/Department/getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser/workflowNodeId/" +
                workflowNode.getId().toString() +
                "/mandatoryInstrumentId/" +
                mandatoryInstrument.getId().toString();
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Department_getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言获取了一个拥有审核能力的部门");
        JSONArray jsonArray = JSONArray.fromObject(mvcResult.getResponse().getContentAsString());
        JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
        assertThat(jsonObject1.get("checkAbility")).isEqualTo(true);
        return;
    }


    @Test
    public void getAllByWorkflowNodeIdOfCurrentLoginUser() throws Exception {
        WorkflowNode workflowNode = workflowNodeRepository.findTopOneByName("区县级器具用户");
        this.mockMvc
                .perform(get("/Department/getAllByWorkflowNodeIdOfCurrentLoginUser/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Department_getAllByWorkflowNodeIdOfCurrentLoginUser", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllTechnicalInstitutionsByDistrictId() throws Exception {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        District district = districtService.getOneDistrict();
        for (int i = 0; i < 10; i++) {
            Department department = new Department();
            department.setDepartmentType(departmentType);
            department.setDistrict(district);
            departmentRepository.save(department);
        }

        MvcResult mvcResult = this.mockMvc
                .perform(get("/Department/getAllTechnicalInstitutionsByDistrictId/" + district.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Department_getAllTechnicalInstitutionsByDistrictId", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(10);
    }

    @Test
    public void pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts() throws Exception {
        String pinyin = CommonService.getRandomStringByLength(11);
        User user = departmentServiceTestData.pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(pinyin);

        this.loginByUser(user);
        String url = "/Department/pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts/" + pinyin + "?page=0&size=2";
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Department_pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts", preprocessResponse(prettyPrint())))
                .andReturn();
        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("content");
        assertThat(jsonArray.size()).isEqualTo(2);
        assertThat(jsonObject.get("totalElements")).isEqualTo(41);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("新建一个部门实体");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("删除实体");
        this.mockMvc.perform(delete("/Department/delete/" + department.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Department_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(departmentRepository.findOne(department.getId())).isNull();
    }

    @Test
    public void getAllInstrumentUserByDistrictId() throws Exception {
        logger.info("新建一个区载");
        District district = new District();
        districtRepository.save(district);

        DepartmentType departmentType = departmentTypeRepository.findOneByName("器具用户");
        logger.info("在这个区域上新建2个器具用户");
        for (int i = 0; i < 2; i++) {
            Department department = new Department();
            department.setDistrict(district);
            department.setDepartmentType(departmentType);
            departmentRepository.save(department);
        }

        logger.info("查询并断言获取的数据为2");
        String url = "/Department/getAllInstrumentUserByDistrictId/" + district.getId().toString();
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Department_getAllInstrumentUserByDistrictId", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONArray jsonArray = JSONArray.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonArray.size()).isEqualTo(2);
    }


}
