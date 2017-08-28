package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/5/5.
 * 强检器具
 */

public class MandatoryInstrumentControllerTest extends ControllerTest {

    private static Logger logger = Logger.getLogger(MandatoryInstrumentControllerTest.class.getName());
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
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
    private AccuracyRepository accuracyRepository;       // 准确度
    @Autowired
    private MeasureScaleRepository measureScaleRepository;   // 测试范围
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
    @Autowired
    private MandatoryInstrumentServiceTestData mandatoryInstrumentServiceTestData;
    @Autowired
    private DistrictRepository districtRepository;               // 区域

    @Test
    public void save() throws Exception {
        User user = userService.loginWithOneUser();
        this.loginByUser(user);
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setName("hello");
        Accuracy accuracy = new Accuracy();
        accuracy.setValue("hello");
        accuracyRepository.save(accuracy);
        MeasureScale measureScale = new MeasureScale();
        measureScale.setValue("hello");
        measureScaleRepository.save(measureScale);
        mandatoryInstrument.setAccuracy(accuracy);
        mandatoryInstrument.setMeasureScale(measureScale);
        JSONObject jsonObject = JSONObject.fromObject(mandatoryInstrument);
        jsonObject.remove("id");
        String content = jsonObject.toString();

        MvcResult result = this.mockMvc.perform(
                post("/MandatoryInstrument/")
                        .contentType("application/json")
                        .content(content)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("MandatoryInstrument_save", preprocessResponse(prettyPrint())))
                .andReturn();
        JSONObject jsonObject1 = JSONObject.fromObject(result.getResponse().getContentAsString());
        int id = (int) jsonObject1.get("id");
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(Long.valueOf(id));
        assertThat(mandatoryInstrument1.getCreateUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void computerCheckAbilityByDepartmentIdOfMandatoryInstruments() throws Exception {
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
        String content = jsonArray.toString();
        String url = "/MandatoryInstrument/computerCheckAbilityByDepartmentIdOfMandatoryInstruments/" + department.getId().toString();
        logger.info("模拟进行请求");
        MvcResult result = this.mockMvc
                .perform(post(url)
                        .contentType("application/json")
                        .content(content)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrument_computerCheckAbilityByDepartmentIdOfMandatoryInstruments", preprocessResponse(prettyPrint())))
                .andReturn();
        JSONArray jsonArray1 = JSONArray.fromObject(result.getResponse().getContentAsString());
        assertThat(jsonArray1.size()).isEqualTo(10);
        int hasAbility = 0;
        int notHasAbility = 0;
        for (Object aJsonArray1 : jsonArray1) {
            JSONObject jsonObject = (JSONObject) aJsonArray1;
            if ((boolean) jsonObject.get("checkAbility") == true) {
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
    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentId() throws Exception {
        User user = userService.loginWithOneUser();

        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        this.mandatoryInstrumentServiceTestData.updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest(
                user.getDepartment(), mandatoryInstrumentApply
        );
        Department checkDepartment = departmentService.getOneCompleteDepartment();
        String url = "/MandatoryInstrument/updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId/" + checkDepartment.getId().toString();
        JSONArray jsonArray = new JSONArray();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", mandatoryInstrument.getId());
            jsonArray.add(jsonObject);
        }

        String content = jsonArray.toString();
        logger.info("模拟进行请求");
        this.loginByUser(user);

        MvcResult result = this.mockMvc
                .perform(post(url)
                        .contentType("application/json")
                        .content(content)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrument_updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId", preprocessResponse(prettyPrint())))
                .andReturn();

        List<MandatoryInstrument> mandatoryInstruments1 = mandatoryInstrumentRepository.findAllByMandatoryInstrumentApplyId(mandatoryInstrumentApply.getId());
        assertThat(mandatoryInstruments1.size()).isEqualTo(5);
        int j = 0;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments1) {
            if (mandatoryInstrument.getCheckDepartment() != null &&
                    mandatoryInstrument.getCheckDepartment().getId() == checkDepartment.getId()) {
                j++;
            }
        }
        assertThat(j).isEqualTo(3);
    }

    @Test
    public void updateCheckCycleAndFirstCheckDate() throws Exception {
        logger.info("查找用户所在的部门实体，使用户所在的部门等同于器具检查部门");
        Department department = departmentRepository.findTopOneByName("河北省管理部门");
        ;
        departmentRepository.save(department);
        JSONObject departmentJsonObject = new JSONObject();
        departmentJsonObject.put("id", department.getId());

        logger.info("新建强检器具实体");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setCheckCycle(30);
        mandatoryInstrument.setCheckDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("创建发送数据的json对象");
        JSONObject sendJsonObject = new JSONObject();
        sendJsonObject.put("checkCycle", 60);
        sendJsonObject.put("firstCheckDate", 1502755200000L);
        sendJsonObject.put("checkDepartment", departmentJsonObject);

        logger.info(sendJsonObject.toString());
        this.mockMvc
                .perform(post("/MandatoryInstrument/updateCheckCycleAndFirstCheckDate/" + mandatoryInstrument.getId().toString())
                        .contentType("application/json")
                        .content(sendJsonObject.toString())
                        .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_updateCheckCycleAndFirstCheckDate", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言强检器具的周期是否更新成功");
        assertThat(mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId()).getCheckCycle()).isEqualTo(60);
        assertThat(mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId()).getFirstCheckDate()).isEqualTo(new Date(1502755200000L));
        logger.info("删除实体");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("查找用户所在的部门实体，使用户所在的部门等同于器具检查部门");
        Department department = departmentRepository.findTopOneByName("河北省管理部门");
        ;
        departmentRepository.save(department);
        JSONObject departmentJsonObject = new JSONObject();
        departmentJsonObject.put("id", department.getId());

        logger.info("新建强检器具实体");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_NEW);
        mandatoryInstrument.setDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("删除实体中的一条数据");
        this.mockMvc.perform(delete("/MandatoryInstrument/delete/" + mandatoryInstrument.getId())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_delete", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言是否删除成功");
        assertThat(mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId())).isNull();
    }

    @Test
    public void pageAllOfCurrentUser() throws Exception {
        logger.info("获取技术机构登录用户");
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);

        logger.info("取出测试区县技术机构");
        Department department = departmentRepository.findByName("测试区县技术机构");

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setCheckDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        this.mockMvc.perform(get("/MandatoryInstrument/pageAllOfCurrentUser?page=0&size=2")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_pageAllOfCurrentUser", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() throws Exception {
        User user = userService.loginWithOneUser();
        String name = CommonService.getRandomStringByLength(10);
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setDepartment(user.getDepartment());
        InstrumentType instrumentType = new InstrumentType();
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        Discipline discipline = new Discipline();

        mandatoryInstrumentServiceTestData.pageAllOfCurrentUserBySpecification(name, mandatoryInstrument, instrumentType, instrumentFirstLevelType, discipline);
        String url = "/MandatoryInstrument/pageAllOfCurrentUserBySpecification?instrumentTypeFirstLevelId=" +
                instrumentFirstLevelType.getId().toString() +
                "&name=" +
                "&page=0&size=2";

        this.loginByUser(user);
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrument_pageAllOfCurrentUserBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);
    }

    @Test
    public void pageAllOfCurrentManageDepartmentBySpecification() throws Exception {
        logger.info("新建立两个区域，父区域和子区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("使用用户登录");
        User user = userService.loginWithOneUser();
        user.getDepartment().setDistrict(district1);
        departmentRepository.save(user.getDepartment());

        String name = CommonService.getRandomStringByLength(10);
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setDepartment(user.getDepartment());
        InstrumentType instrumentType = new InstrumentType();
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        Discipline discipline = new Discipline();

        mandatoryInstrumentServiceTestData.pageAllOfCurrentUserBySpecification(name, mandatoryInstrument, instrumentType, instrumentFirstLevelType, discipline);
        String url = "/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification?id=" +
                mandatoryInstrument.getId().toString() +
                "&page=0&size=2";

        this.loginByUser(user);
        logger.info("只查询ID");
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrument_pageAllOfCurrentUserBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("查询子区域 ");
        url = "/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification?districtId=" +
                district1.getId().toString() +
                "&page=0&size=2";
        mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andReturn();
        content = mvcResult.getResponse().getContentAsString();
        jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("查询父区域");
        logger.info("将当前用户的部门区域设置为父区域");
        user.getDepartment().setDistrict(district);
        departmentRepository.save(user.getDepartment());
        url = "/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification?districtId=" +
                district.getId().toString() +
                "&instrumentTypeFirstLevelId=" +
                instrumentFirstLevelType.getId().toString() +
                "&page=0&size=2";
        mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andReturn();
        content = mvcResult.getResponse().getContentAsString();
        jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("取被指定的器具");
        url = "/MandatoryInstrument/pageAllOfCurrentTechnicalInstitutionBySpecification?page=0&size=2";
        mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andReturn();
        content = mvcResult.getResponse().getContentAsString();
        jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(0);

        mandatoryInstrument.setCheckDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrument_pageAllOfCurrentTechnicalInstitutionBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();
        content = mvcResult.getResponse().getContentAsString();
        jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);
    }


    @Test
    public void update() throws Exception {
        logger.info("模拟用户登录");
        User user = userService.loginWithOneUser();

        logger.info("新建强检器具并保存");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        mandatoryInstrumentService.save(mandatoryInstrument);

        String name = CommonService.getRandomStringByLength(20);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);

        String url = "/MandatoryInstrument/" + mandatoryInstrument.getId().toString();
        logger.info("模拟登录完成更新部分字段操作");
        this.loginByUser(user);
        this.mockMvc.perform(patch(url)
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_update", preprocessResponse(prettyPrint())));

        logger.info("断言，并恢复数据");
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId());
        assertThat(mandatoryInstrument1.getName()).isEqualTo(name);
        mandatoryInstrumentRepository.delete(mandatoryInstrument.getId());
    }

    @Test
    public void getAllOfCurrentUser() throws Exception {
        logger.info("获取技术机构登录用户");
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);

        logger.info("取出测试区县技术机构");
        Department department = departmentRepository.findByName("测试区县技术机构");

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setCheckDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        this.mockMvc.perform(get("/MandatoryInstrument/getAllOfCurrentUser")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_getAllOfCurrentUser", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void pageByCheckDepartmentOfCurrentDepartment() throws Exception {
        logger.info("生成一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建10个强检器具并指定检定部门");
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            mandatoryInstrument.setName(CommonService.getRandomStringByLength(10));
            mandatoryInstrument.setCheckDepartment(user.getDepartment());
            mandatoryInstruments.add(mandatoryInstrument);
        }
        mandatoryInstrumentRepository.save(mandatoryInstruments);

        logger.info(" 模拟进行登录");
        String url = "/MandatoryInstrument/pageByCheckDepartmentOfCurrentDepartment?page=0&size=2";
        this.loginByUser(user);
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_pageByCheckDepartmentOfCurrentDepartment", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());

        logger.info("断言获取到的数据总数为10条");
        assertThat(jsonObject.get("totalElements")).isEqualTo(10);
    }

    @Test
    public void updateFixSiteAndName() throws Exception {
        logger.info("模拟用户登录");
        User user = userService.loginWithOneUser();

        logger.info("新建强检器具并保存");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        mandatoryInstrumentService.save(mandatoryInstrument);

        String name = CommonService.getRandomStringByLength(20);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("fixSite", name);

        String url = "/MandatoryInstrument/" + mandatoryInstrument.getId().toString();
        logger.info("模拟登录完成更新部分字段操作");
        this.loginByUser(user);
        this.mockMvc.perform(patch(url)
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrument_updateFixSiteAndName", preprocessResponse(prettyPrint())));

        logger.info("断言，并恢复数据");
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId());
        assertThat(mandatoryInstrument1.getName()).isEqualTo(name);
        assertThat(mandatoryInstrument1.getFixSite()).isEqualTo(name);
        mandatoryInstrumentRepository.delete(mandatoryInstrument.getId());

    }

    @Test
    public void setStatusToBackById() throws Exception {
        User user = userService.loginWithOneUser();

        logger.info("调用准备好的数据");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentServiceTestData.setStatusToBackById(user);
        String url = "/MandatoryInstrument/setStatusToBackById/" + mandatoryInstrument.getId().toString();

        logger.info("模拟登录");
        this.loginByUser(user);
        this.mockMvc.perform(put(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrument_setStatusToBackById", preprocessResponse(prettyPrint())));

        logger.info("状态状态已变更");
        assertThat(mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId()).getStatus()).isEqualTo(InstrumentEmploymentInfo.STATUS_BACKED);
    }
}