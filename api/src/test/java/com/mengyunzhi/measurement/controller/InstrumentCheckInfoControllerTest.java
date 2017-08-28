package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.MandatoryInstrumentCheckInfoServiceTestData;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by chuhang on 17-8-1.
 * 器具检定信息的C层test
 */
public class InstrumentCheckInfoControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(DistrictControllerTest.class.getName());
    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private InstrumentProductionRepository instrumentProductionRepository;
    @Autowired
    protected MandatoryInstrumentCheckInfoServiceTestData mandatoryInstrumentCheckInfoServiceTestData;
    @Autowired
    protected MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    private InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Autowired
    private CheckResultRepository checkResultRepository;
    @Autowired
    private AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    @Test
    public void save() throws Exception {

        logger.info("创建一个关联检定结果实体");
        CheckResult checkResult = new CheckResult();
        checkResultRepository.save(checkResult);
        JSONObject checkResultJsonObject = new JSONObject();
        checkResultJsonObject.put("id", checkResult.getId());

        logger.info("创建一个关联器具生产信息");
        InstrumentProduction instrumentProduction = new InstrumentProduction();
        instrumentProductionRepository.save(instrumentProduction);
        JSONObject instrumentProductionJsonObject = new JSONObject();
        instrumentProductionJsonObject.put("id", instrumentProduction.getId());

        logger.info("创建一个器具使用信息");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        JSONObject mandatoryInstrumentJsonObject = new JSONObject();
        mandatoryInstrumentJsonObject.put("id", mandatoryInstrument.getId());

        logger.info("创建发送数据的json对象");
        JSONObject sendJsonObject = new JSONObject();
        sendJsonObject.put("certificateNum", "2323");
        sendJsonObject.put("cost", "3213");
        sendJsonObject.put("checkDate", "2017-03-29");
        sendJsonObject.put("checkResult", checkResultJsonObject);
        sendJsonObject.put("instrumentProduction", instrumentProductionJsonObject);
        sendJsonObject.put("mandatoryInstrument", mandatoryInstrumentJsonObject);

        MvcResult result = this.mockMvc.perform(post("/InstrumentCheckInfo/save")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(sendJsonObject.toString()))
                .andExpect(status().isOk())
                .andDo(document("InstrumentCheckInfo_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据,断言返回的信息中存在ID值，证明保存成功");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isNotNull();

        logger.info("删除实体");
        instrumentCheckInfoRepository.delete(Long.parseLong(jsonObject.get("id").toString()));
        checkResultRepository.delete(checkResult);
        instrumentProductionRepository.delete(instrumentProduction);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("创建一个部门实体");
        Department department = departmentRepository.findTopOneByName("河北省管理部门");
        departmentRepository.save(department);
        JSONObject departmentJsonObject = new JSONObject();
        departmentJsonObject.put("id", department.getId());

        logger.info("创建一个关联检定结果实体");
        CheckResult checkResult = new CheckResult();
        checkResultRepository.save(checkResult);
        JSONObject checkResultJsonObject = new JSONObject();
        checkResultJsonObject.put("id", checkResult.getId());

        logger.info("创建一个关联器具生产信息");
        InstrumentProduction instrumentProduction = new InstrumentProduction();
        instrumentProductionRepository.save(instrumentProduction);
        JSONObject instrumentProductionJsonObject = new JSONObject();
        instrumentProductionJsonObject.put("id", instrumentProduction.getId());

        logger.info("创建一个强检器具检定信息实体");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setCheckDepartment(department);
        instrumentCheckInfo.setCheckResult(checkResult);
        instrumentCheckInfo.setInstrumentProduction(instrumentProduction);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        logger.info("删除实体中的一条数据");
        this.mockMvc.perform(delete("/InstrumentCheckInfo/delete/" + instrumentCheckInfo.getId())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentCheckInfo_delete", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言是否删除成功");
        assertThat(instrumentCheckInfoRepository.findOne(instrumentCheckInfo.getId())).isNull();
    }

    public void pageAllOfCurrentUser() throws Exception {
        logger.info("准备测试数据");
        User user = userRepository.findOneByUsername("user3");
        Department department = departmentRepository.findByName("测试区县技术机构");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();

        mandatoryInstrumentCheckInfoServiceTestData.getOneMandatoryInstrumentCheckInfo(department, user, mandatoryInstrument, instrumentCheckInfo);

        logger.info("测试");
        this.mockMvc.perform(get("/InstrumentCheckInfo/pageAllOfCurrentUser")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentCheckInfo_save", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        userRepository.delete(user);
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
        departmentRepository.delete(department);
    }

    @Test
    public void update() throws Exception {
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        logger.info("更新");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", instrumentCheckInfo.getId());
        jsonObject.put("certificateNum", "123456789");

        this.mockMvc.perform(put("/InstrumentCheckInfo/" + instrumentCheckInfo.getId().toString())
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentCheckInfo_update", preprocessResponse(prettyPrint())));

        logger.info("断言");
        InstrumentCheckInfo instrumentCheckInfo1 = instrumentCheckInfoRepository.findOne(instrumentCheckInfo.getId());
        assertThat(instrumentCheckInfo1.getCertificateNum()).isEqualTo("123456789");

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
    }

    @Test
    public void getAllByMandatoryInstrumentId() throws Exception {
        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        this.mockMvc.perform(get("/InstrumentCheckInfo/pageAllByMandatoryInstrumentId/" + mandatoryInstrument.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("InstrumentCheckInfo_pageAllByMandatoryInstrumentId", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void pageAllOfManagementDepartmentBySpecification() throws Exception {
        logger.info("1.新建区域实体");
        District districtA = new District();
        District districtB = new District();
        logger.info("设置A为B的父区域");
        districtB.setParentDistrict(districtA);
        districtRepository.save(districtA);
        districtRepository.save(districtB);

        logger.info("2.器具用户实体");
        Department departmentA = new Department();
        Department departmentB = new Department();
        departmentA.setDistrict(districtA);
        departmentB.setDistrict(districtB);
        departmentRepository.save(departmentA);
        departmentRepository.save(departmentB);

        logger.info("3.检定机构实体");
        Department checkDepartmentA = new Department();
        Department checkDepartmentB = new Department();
        departmentRepository.save(checkDepartmentA);
        departmentRepository.save(checkDepartmentB);

        logger.info("4.学科类别实体");
        Discipline disciplineA = new Discipline();
        Discipline disciplineB = new Discipline();
        disciplineRepository.save(disciplineA);
        disciplineRepository.save(disciplineB);

        logger.info("5.一级类别实体");
        InstrumentFirstLevelType instrumentFirstLevelTypeA = new InstrumentFirstLevelType();
        InstrumentFirstLevelType instrumentFirstLevelTypeB = new InstrumentFirstLevelType();
        logger.info("关联学科类别");
        instrumentFirstLevelTypeA.setDiscipline(disciplineA);
        instrumentFirstLevelTypeB.setDiscipline(disciplineB);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeB);


        logger.info("6.准确度等级实体");
        AccuracyDisplayName accuracyDisplayNameA = new AccuracyDisplayName();
        AccuracyDisplayName accuracyDisplayNameB = new AccuracyDisplayName();
        accuracyDisplayNameRepository.save(accuracyDisplayNameA);
        accuracyDisplayNameRepository.save(accuracyDisplayNameB);

        logger.info("7.二级类别实体");
        InstrumentType instrumentTypeA = new InstrumentType();
        InstrumentType instrumentTypeB = new InstrumentType();
        logger.info("关联一级类别");
        instrumentTypeA.setInstrumentFirstLevelType(instrumentFirstLevelTypeA);
        instrumentTypeB.setInstrumentFirstLevelType(instrumentFirstLevelTypeB);
        logger.info("关联准确度等级");
        instrumentTypeA.setAccuracyDisplayName(accuracyDisplayNameA);
        instrumentTypeB.setAccuracyDisplayName(accuracyDisplayNameB);
        instrumentTypeRepository.save(instrumentTypeA);
        instrumentTypeRepository.save(instrumentTypeB);

        logger.info("8.检定结果实体");
        CheckResult checkResultA = new CheckResult();
        CheckResult checkResultB = new CheckResult();
        checkResultRepository.save(checkResultA);
        checkResultRepository.save(checkResultB);

        logger.info("9.新建强检器具");
        MandatoryInstrument mandatoryInstrumentA = new MandatoryInstrument();
        MandatoryInstrument mandatoryInstrumentB = new MandatoryInstrument();
        logger.info("设置强检器具名称");
        mandatoryInstrumentB.setName("mandatoryInstrumentB");
        mandatoryInstrumentA.setName("mandatoryInstrumentA");
        logger.info("关联器具用户");
        mandatoryInstrumentA.setDepartment(departmentA);
        mandatoryInstrumentB.setDepartment(departmentB);
        logger.info("关联二级类别");
        mandatoryInstrumentA.setInstrumentType(instrumentTypeA);
        mandatoryInstrumentB.setInstrumentType(instrumentTypeB);
        logger.info("关联技术机构");
        mandatoryInstrumentA.setCheckDepartment(checkDepartmentA);
        mandatoryInstrumentB.setCheckDepartment(checkDepartmentB);
        mandatoryInstrumentRepository.save(mandatoryInstrumentA);
        mandatoryInstrumentRepository.save(mandatoryInstrumentB);

        logger.info("10.新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfoA = new InstrumentCheckInfo();
        InstrumentCheckInfo instrumentCheckInfoB = new InstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);

        logger.info("设置检定日期");
        instrumentCheckInfoA.setCheckDate(new Date(1L));
        instrumentCheckInfoB.setCheckDate(new Date(Calendar.getInstance().getTimeInMillis()));
        logger.info("设置关联强检器具");
        instrumentCheckInfoB.setMandatoryInstrument(mandatoryInstrumentB);
        instrumentCheckInfoA.setMandatoryInstrument(mandatoryInstrumentA);

        logger.info("设置检定结果");
        instrumentCheckInfoA.setCheckResult(checkResultA);
        instrumentCheckInfoB.setCheckResult(checkResultB);
        logger.info("设置证书编号");
        instrumentCheckInfoA.setCertificateNum("certificateNumA");
        instrumentCheckInfoB.setCertificateNum("certificateNumB");
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);
        
        logger.info("获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(departmentA);
        userRepository.save(user);
        logger.info("使用用户user进行模拟登录时，");
        this.loginByUser(user);

        String url = "/InstrumentCheckInfo/pageAllOfManagementDepartmentBySpecification?" +
                "departmentId=" + departmentB.getId().toString() +
                "&districtId=" + districtA.getId().toString() +
                "&checkDepartmentId=" + checkDepartmentB.getId().toString() +
                "&disciplineId=" + disciplineB.getId().toString() +
                "&instrumentFirstLevelTypeId=" + instrumentFirstLevelTypeB.getId().toString() +
                "&instrumentTypeId=" + instrumentTypeB.getId().toString() +
                "&certificateNum=certificateNumB" +
                "&checkResultId=" + checkResultB.getId() +
                "&accuracyDisplayNameId=" + accuracyDisplayNameB.getId().toString() +
                "&mandatoryInstrumentId=" + mandatoryInstrumentB.getId().toString() +
                "&name=mandatoryInstrumentB&year=2017&page=0&size=2";

        MvcResult result = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentCheckInfo_pageAllOfManagementDepartmentBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据并断言");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);

        logger.info("断言测试成功");
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        districtRepository.delete(districtA);
        districtRepository.delete(districtB);
        departmentRepository.delete(checkDepartmentA);
        departmentRepository.delete(checkDepartmentB);
        departmentRepository.delete(departmentA);
        departmentRepository.delete(departmentB);
        accuracyDisplayNameRepository.delete(accuracyDisplayNameA);
        accuracyDisplayNameRepository.delete(accuracyDisplayNameB);
        instrumentTypeRepository.delete(instrumentTypeA);
        instrumentTypeRepository.delete(instrumentTypeB);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeB);
        disciplineRepository.delete(disciplineA);
        disciplineRepository.delete(disciplineB);
        checkResultRepository.delete(checkResultA);
        checkResultRepository.delete(checkResultB);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentA);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentB);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoA);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoB);
    }

    @Test
    public void pageAllOfMeasureUserBySpecification() throws Exception {
        logger.info("1.新建区域实体");
        District districtA = new District();
        District districtB = new District();
        logger.info("设置A为B的父区域");
        districtB.setParentDistrict(districtA);
        districtRepository.save(districtA);
        districtRepository.save(districtB);

        logger.info("2.器具用户实体");
        Department departmentA = new Department();
        Department departmentB = new Department();
        departmentA.setDistrict(districtA);
        departmentB.setDistrict(districtB);
        departmentRepository.save(departmentA);
        departmentRepository.save(departmentB);


        logger.info("4.学科类别实体");
        Discipline disciplineA = new Discipline();
        Discipline disciplineB = new Discipline();
        disciplineRepository.save(disciplineA);
        disciplineRepository.save(disciplineB);

        logger.info("5.一级类别实体");
        InstrumentFirstLevelType instrumentFirstLevelTypeA = new InstrumentFirstLevelType();
        InstrumentFirstLevelType instrumentFirstLevelTypeB = new InstrumentFirstLevelType();
        logger.info("关联学科类别");
        instrumentFirstLevelTypeA.setDiscipline(disciplineA);
        instrumentFirstLevelTypeB.setDiscipline(disciplineB);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeB);

        logger.info("7.二级类别实体");
        InstrumentType instrumentTypeA = new InstrumentType();
        InstrumentType instrumentTypeB = new InstrumentType();
        logger.info("关联一级类别");
        instrumentTypeA.setInstrumentFirstLevelType(instrumentFirstLevelTypeA);
        instrumentTypeB.setInstrumentFirstLevelType(instrumentFirstLevelTypeB);
        instrumentTypeRepository.save(instrumentTypeA);
        instrumentTypeRepository.save(instrumentTypeB);

        logger.info("8.检定结果实体");
        CheckResult checkResultA = new CheckResult();
        CheckResult checkResultB = new CheckResult();
        checkResultRepository.save(checkResultA);
        checkResultRepository.save(checkResultB);

        logger.info("9.新建强检器具");
        MandatoryInstrument mandatoryInstrumentA = new MandatoryInstrument();
        MandatoryInstrument mandatoryInstrumentB = new MandatoryInstrument();
        logger.info("设置强检器具名称");
        mandatoryInstrumentB.setName("mandatoryInstrumentB");
        mandatoryInstrumentA.setName("mandatoryInstrumentA");
        logger.info("关联器具用户");
        mandatoryInstrumentA.setDepartment(departmentA);
        mandatoryInstrumentB.setDepartment(departmentB);
        logger.info("关联二级类别");
        mandatoryInstrumentA.setInstrumentType(instrumentTypeA);
        mandatoryInstrumentB.setInstrumentType(instrumentTypeB);
        mandatoryInstrumentRepository.save(mandatoryInstrumentA);
        mandatoryInstrumentRepository.save(mandatoryInstrumentB);

        logger.info("10.新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfoA = new InstrumentCheckInfo();
        InstrumentCheckInfo instrumentCheckInfoB = new InstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);

        logger.info("设置检定日期");
        instrumentCheckInfoA.setCheckDate(new Date(1L));
        instrumentCheckInfoB.setCheckDate(new Date(Calendar.getInstance().getTimeInMillis()));
        logger.info("设置关联强检器具");
        instrumentCheckInfoB.setMandatoryInstrument(mandatoryInstrumentB);
        instrumentCheckInfoA.setMandatoryInstrument(mandatoryInstrumentA);

        logger.info("设置检定结果");
        instrumentCheckInfoA.setCheckResult(checkResultA);
        instrumentCheckInfoB.setCheckResult(checkResultB);
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);

        logger.info("获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(departmentA);
        userRepository.save(user);
        logger.info("使用用户user进行模拟登录时，");
        this.loginByUser(user);

        String url = "/InstrumentCheckInfo/pageAllOfMeasureUserBySpecification?" +
                "&disciplineId=" + disciplineB.getId().toString() +
                "&instrumentFirstLevelTypeId=" + instrumentFirstLevelTypeB.getId().toString() +
                "&instrumentTypeId=" + instrumentTypeB.getId().toString() +
                "&checkResultId=" + checkResultB.getId() +
                "&mandatoryInstrumentId=" + mandatoryInstrumentB.getId().toString() +
                "&name=mandatoryInstrumentB&year=2017&page=0&size=2";

        MvcResult result = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentCheckInfo_pageAllOfManagementDepartmentBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据并断言");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);

        logger.info("断言测试成功");
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        districtRepository.delete(districtA);
        districtRepository.delete(districtB);
        departmentRepository.delete(departmentA);
        departmentRepository.delete(departmentB);
        instrumentTypeRepository.delete(instrumentTypeA);
        instrumentTypeRepository.delete(instrumentTypeB);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeB);
        disciplineRepository.delete(disciplineA);
        disciplineRepository.delete(disciplineB);
        checkResultRepository.delete(checkResultA);
        checkResultRepository.delete(checkResultB);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentA);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentB);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoA);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoB);
    }

    @Test
    public void pageAllOfTechnicalInstitutionDepartmentBySpecification() throws Exception {
        logger.info("1.新建区域实体");
        District districtA = new District();
        District districtB = new District();
        logger.info("设置A为B的父区域");
        districtB.setParentDistrict(districtA);
        districtRepository.save(districtA);
        districtRepository.save(districtB);

        logger.info("2.器具用户实体");
        Department departmentA = new Department();
        Department departmentB = new Department();
        departmentA.setDistrict(districtA);
        departmentB.setDistrict(districtB);
        departmentRepository.save(departmentA);
        departmentRepository.save(departmentB);

        logger.info("3.获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(departmentB);
        userRepository.save(user);
        logger.info("使用用户user进行模拟登录时，");
        this.loginByUser(user);



        logger.info("4.学科类别实体");
        Discipline disciplineA = new Discipline();
        Discipline disciplineB = new Discipline();
        disciplineRepository.save(disciplineA);
        disciplineRepository.save(disciplineB);

        logger.info("5.一级类别实体");
        InstrumentFirstLevelType instrumentFirstLevelTypeA = new InstrumentFirstLevelType();
        InstrumentFirstLevelType instrumentFirstLevelTypeB = new InstrumentFirstLevelType();
        logger.info("关联学科类别");
        instrumentFirstLevelTypeA.setDiscipline(disciplineA);
        instrumentFirstLevelTypeB.setDiscipline(disciplineB);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelTypeB);


        logger.info("6.准确度等级实体");
        AccuracyDisplayName accuracyDisplayNameA = new AccuracyDisplayName();
        AccuracyDisplayName accuracyDisplayNameB = new AccuracyDisplayName();
        accuracyDisplayNameRepository.save(accuracyDisplayNameA);
        accuracyDisplayNameRepository.save(accuracyDisplayNameB);

        logger.info("7.二级类别实体");
        InstrumentType instrumentTypeA = new InstrumentType();
        InstrumentType instrumentTypeB = new InstrumentType();
        logger.info("关联一级类别");
        instrumentTypeA.setInstrumentFirstLevelType(instrumentFirstLevelTypeA);
        instrumentTypeB.setInstrumentFirstLevelType(instrumentFirstLevelTypeB);
        logger.info("关联准确度等级");
        instrumentTypeA.setAccuracyDisplayName(accuracyDisplayNameA);
        instrumentTypeB.setAccuracyDisplayName(accuracyDisplayNameB);
        instrumentTypeRepository.save(instrumentTypeA);
        instrumentTypeRepository.save(instrumentTypeB);

        logger.info("8.检定结果实体");
        CheckResult checkResultA = new CheckResult();
        CheckResult checkResultB = new CheckResult();
        checkResultRepository.save(checkResultA);
        checkResultRepository.save(checkResultB);

        logger.info("9.新建强检器具");
        MandatoryInstrument mandatoryInstrumentA = new MandatoryInstrument();
        MandatoryInstrument mandatoryInstrumentB = new MandatoryInstrument();
        logger.info("设置强检器具名称");
        mandatoryInstrumentB.setName("mandatoryInstrumentB");
        mandatoryInstrumentA.setName("mandatoryInstrumentA");
        logger.info("关联器具用户");
        mandatoryInstrumentA.setDepartment(departmentA);
        mandatoryInstrumentB.setDepartment(departmentB);
        logger.info("关联二级类别");
        mandatoryInstrumentA.setInstrumentType(instrumentTypeA);
        mandatoryInstrumentB.setInstrumentType(instrumentTypeB);
        logger.info("关联技术机构");
        mandatoryInstrumentA.setCheckDepartment(departmentA);
        mandatoryInstrumentB.setCheckDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrumentA);
        mandatoryInstrumentRepository.save(mandatoryInstrumentB);

        logger.info("10.新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfoA = new InstrumentCheckInfo();
        InstrumentCheckInfo instrumentCheckInfoB = new InstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);

        logger.info("设置检定日期");
        instrumentCheckInfoA.setCheckDate(new Date(1L));
        instrumentCheckInfoB.setCheckDate(new Date(Calendar.getInstance().getTimeInMillis()));
        logger.info("设置关联强检器具");
        instrumentCheckInfoB.setMandatoryInstrument(mandatoryInstrumentB);
        instrumentCheckInfoA.setMandatoryInstrument(mandatoryInstrumentA);

        logger.info("设置检定结果");
        instrumentCheckInfoA.setCheckResult(checkResultA);
        instrumentCheckInfoB.setCheckResult(checkResultB);
        logger.info("设置证书编号");
        instrumentCheckInfoA.setCertificateNum("certificateNumA");
        instrumentCheckInfoB.setCertificateNum("certificateNumB");
        instrumentCheckInfoRepository.save(instrumentCheckInfoA);
        instrumentCheckInfoRepository.save(instrumentCheckInfoB);


        String url = "/InstrumentCheckInfo/pageAllOfTechnicalInstitutionDepartmentBySpecification?" +
                "departmentId=" + departmentB.getId().toString() +
                "&districtId=" + districtA.getId().toString() +
                "&disciplineId=" + disciplineB.getId().toString() +
                "&instrumentFirstLevelTypeId=" + instrumentFirstLevelTypeB.getId().toString() +
                "&instrumentTypeId=" + instrumentTypeB.getId().toString() +
                "&certificateNum=certificateNumB" +
                "&checkResultId=" + checkResultB.getId() +
                "&accuracyDisplayNameId=" + accuracyDisplayNameB.getId().toString() +
                "&mandatoryInstrumentId=" + mandatoryInstrumentB.getId().toString() +
                "&name=mandatoryInstrumentB&year=2017&page=0&size=2";

        MvcResult result = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentCheckInfo_pageAllOfTechnicalInstitutionDepartmentBySpecification", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据并断言");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);

        logger.info("断言测试成功");
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        districtRepository.delete(districtA);
        districtRepository.delete(districtB);
        departmentRepository.delete(departmentA);
        departmentRepository.delete(departmentB);
        accuracyDisplayNameRepository.delete(accuracyDisplayNameA);
        accuracyDisplayNameRepository.delete(accuracyDisplayNameB);
        instrumentTypeRepository.delete(instrumentTypeA);
        instrumentTypeRepository.delete(instrumentTypeB);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeA);
        instrumentFirstLevelTypeRepository.delete(instrumentFirstLevelTypeB);
        disciplineRepository.delete(disciplineA);
        disciplineRepository.delete(disciplineB);
        checkResultRepository.delete(checkResultA);
        checkResultRepository.delete(checkResultB);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentA);
        mandatoryInstrumentRepository.delete(mandatoryInstrumentB);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoA);
        instrumentCheckInfoRepository.delete(instrumentCheckInfoB);
        userRepository.delete(user);
    }
}
