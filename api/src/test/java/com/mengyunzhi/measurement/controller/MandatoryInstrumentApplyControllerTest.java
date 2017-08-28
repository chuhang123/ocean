package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by liming on 17-7-15.
 */
public class MandatoryInstrumentApplyControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceTest.class.getName());
    @Autowired
    protected UserService userService;
    @Autowired
    protected WorkRepository workRepository;
    @Autowired
    protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired
    private MandatoryInstrumentApplyService mandatoryInstrumentApplyService; // 强制检定器具
    @Autowired
    private MandatoryInstrumentApplyServiceTestData mandatoryInstrumentApplyServiceTestData; // 强制检定申请测试数据
    @Autowired
    protected WorkflowTypeRepository workflowTypeRepository;        // 工作流类型
    @Autowired
    protected WorkflowNodeService workflowNodeService;              // 工作流结点
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private UserRepository userRepository;                //e 用户
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("DepartmentService")
    private DepartmentService departmentService; // 部门
    @Autowired
    MeasureScaleService measureScaleService;                 // 测试范围
    @Autowired
    AccuracyService accuracyService;                         // 精确度
    @Autowired
    DeviceSetService deviceSetService;                       // 标准装置
    @Autowired
    DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired
    DeviceInstrumentRepository deviceInstrumentRepository;       // 授权检定范围
    @Autowired
    MandatoryInstrumentServiceTestData mandatoryIntegratedServiceTestData;   // 测试数据

    @Test
    public void getPageOfCurrentDepartment() throws Exception {
        logger.info("设置部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("设置当前登录用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);
        logger.info("设置申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("设置工作");
        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);

        logger.info("测试");
        this.mockMvc.perform(get("/MandatoryInstrumentApply/getPageOfCurrentDepartment" + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrumentApply_getPageOfCurrentDepartment", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());

        logger.info("删除数据");
        workRepository.delete(work);
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void findById() throws Exception {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();

        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        assertThat(mandatoryInstrumentApplyService.findById(mandatoryInstrumentApply.getId())).isNotNull();
        MvcResult mvcResult = this.mockMvc.perform(get("/MandatoryInstrumentApply/findById/" + mandatoryInstrumentApply.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentApply_findById", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isEqualTo(mandatoryInstrumentApply.getId().intValue());
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
    }

    @Test
    public void save() throws Exception {
        logger.info("模拟用户登录");


        logger.info("实例化输入源");
        MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply workMandatoryInstrumentApply =
                new MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply();
        User user = mandatoryInstrumentApplyServiceTestData.save(workMandatoryInstrumentApply);
        this.loginByUser(user);

        logger.info("构造json串");
        JSONObject jsonObject = new JSONObject();
        JSONObject workJsonObject = JSONObject.fromObject(workMandatoryInstrumentApply.getWork());
        workJsonObject.remove("id");
        jsonObject.put("work", workJsonObject);

        JSONObject mandatoryInstrumentApplyJsonObject = JSONObject.fromObject(workMandatoryInstrumentApply.getMandatoryInstrumentApply());
        mandatoryInstrumentApplyJsonObject.remove("id");
        jsonObject.put("mandatoryInstrumentApply", mandatoryInstrumentApplyJsonObject);

        jsonObject.get("work");
        MvcResult mvcResult = this.mockMvc.perform(post("/MandatoryInstrumentApply/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().is(201))
                .andDo(document("MandatoryInstrumentApply_save", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObjectResult = JSONObject.fromObject(content);

    }

    @Test
    public void computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId() throws Exception {
        logger.info("新建精确度");
        Accuracy accuracy = accuracyService.getOneAccuracy();
        Accuracy accuracy1 = accuracyService.getOneAccuracy();
        logger.info("新建测量范围");
        MeasureScale measureScale = measureScaleService.getOneMeasureScale();
        logger.info("新建技术机构");
        Department department = departmentService.getOneTechnicalInstitutionDepartment();

        logger.info("新建10个强制检定器具");
        JSONArray jsonArray = new JSONArray();
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        mandatoryIntegratedServiceTestData
                .computerCheckAbilityByDepartmentIdOfMandatoryInstruments(
                        accuracy,
                        accuracy1,
                        measureScale,
                        department,
                        jsonArray,
                        mandatoryInstruments);

        logger.info("新建申请，并为申请加入强检器具");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setMandatoryInstruments(mandatoryInstruments);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        String url = "/MandatoryInstrumentApply/computeCheckAbilityBy/MandatoryInstrumentApplyId/"
                + mandatoryInstrumentApply.getId().toString() + "/DepartmentId/" +
                department.getId().toString();

        logger.info("模拟进行请求");
        MvcResult result = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrumentApply_computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(result.getResponse().getContentAsString());
        JSONArray jsonArray1 = JSONArray.fromObject(jsonObject.get("mandatoryInstruments"));
        assertThat(jsonArray1.size()).isEqualTo(10);

        int hasAbility = 0;
        int notHasAbility = 0;
        for (int index = 0; index < jsonArray1.size(); index++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray1.get(index);
            if ((boolean) jsonObject1.get("checkAbility") == true) {
                hasAbility++;
            } else {
                notHasAbility++;
            }
        }

        logger.info("断言其中有5个是符合要求的");
        assertThat(hasAbility).isEqualTo(5);
        assertThat(notHasAbility).isEqualTo(5);
    }

    @Test
    public void generateWordApplyByToken() throws Exception {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        String token = MandatoryInstrumentApplyService.generateTokenById(mandatoryInstrumentApply.getId());
        String url = "/MandatoryInstrumentApply/generateWordApplyByToken/" + token;
        logger.info("模拟进行请求");
        Boolean error = false;
        try {
            this.mockMvc
                    .perform(get(url)
                            .contentType("application/json")
                            .header("x-auth-token", xAuthToken))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
        } catch (Exception e) {
            error = true;
        }
        assertThat(error).isTrue();
    }

    @Test
    public void generateTokenById() throws Exception {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        String url = "/MandatoryInstrumentApply/generateTokenById/" + mandatoryInstrumentApply.getId().toString();
        MvcResult result = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentApply_generateTokenById", preprocessResponse(prettyPrint())))
                .andReturn();
        String md5 = result.getResponse().getContentAsString().replaceAll("[\"]", "");

        Long id = MandatoryInstrumentApplyService.getIdByToken(md5);
        assertThat(id).isEqualTo(mandatoryInstrumentApply.getId());
    }

}