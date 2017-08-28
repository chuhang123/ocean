package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.Service.DeviceSetService;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
public class DeviceSetControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(DeviceSetControllerTest.class.getName());
    @Autowired protected AccuracyRepository accuracyRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;
    @Autowired protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected UserRepository userRepository;
    @Autowired protected UserService userService;
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    @Autowired
    DeviceSetService deviceSetService;      // 标准装置
    @Autowired protected DeviceSetServiceTestData deviceSetServiceTestData;
    @Autowired protected DistrictRepository districtRepository;

    protected DeviceSet deviceSet;      // 标准装置
    @Test
    public void save() throws Exception {
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);
        deviceSet = new DeviceSet();

        logger.info("发送模拟数据");
        JSONObject deviceSetJsonObject = JSONObject.fromObject(deviceSet);
        logger.info("将id至为null,防止后台保存数据后，不返回id的问题");
        deviceSetJsonObject.put("id", null);
        MvcResult result = this.mockMvc.perform(post("/DeviceSet/")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(deviceSetJsonObject.toString()))
                .andExpect(status().isOk())
                .andDo(document("DeviceSet_saveOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据,断言返回的信息中存在ID值，证明保存成功");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();
        logger.info("删除测试数据");
        deviceSetRepository.delete(Long.parseLong(jsonObject.get("id").toString()));

    }

    @Test
    public void updateDeviceInstrumentsById() throws Exception {
        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("name");
        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        Accuracy accuracy1 = new Accuracy();
        accuracyRepository.save(accuracy);
        accuracyRepository.save(accuracy1);
        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        MeasureScale measureScale1 = new MeasureScale();
        measureScaleRepository.save(measureScale);
        measureScaleRepository.save(measureScale1);
        logger.info("新建授权鉴定项目实体");
        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrumentRepository.save(deviceInstrument);
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrument1.setMeasureScale(measureScale1);
        deviceInstrument1.setAccuracy(accuracy1);
        deviceInstrumentRepository.save(deviceInstrument1);
        deviceInstruments.add(deviceInstrument);
        deviceInstruments.add(deviceInstrument1);
        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSetRepository.save(deviceSet);

        logger.info("前台传值的示例");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "name");
        jsonObject.put("code", "code");
        jsonObject.put("certificateNum", "certificateNum");
        jsonObject.put("pinyin", "pinyin");
        logger.info("前台如何绑定多主健实体信息");
        JSONObject deviceInstrumenJsonObject = new JSONObject();
        deviceInstrumenJsonObject.put("accuracyId", accuracy.getId());
        deviceInstrumenJsonObject.put("measureScaleId", measureScale.getId());
        JSONObject adddeviceInstrumenJsonObject = new JSONObject();
        adddeviceInstrumenJsonObject.put("id", deviceInstrumenJsonObject);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(adddeviceInstrumenJsonObject);
        jsonObject.put("deviceInstruments", jsonArray);

        logger.info("测试数据");
        this.mockMvc.perform(put("/DeviceSet/updateDeviceInstrumentsById/" + deviceSet.getId().toString())
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("DeviceSet_updateDeviceInstrumentsById", preprocessResponse(prettyPrint())));

        logger.info("删除实体");
        deviceInstrumentRepository.delete(deviceInstruments);
        accuracyRepository.delete(accuracy);
        accuracyRepository.delete(accuracy1);
        measureScaleRepository.delete(measureScale);
        measureScaleRepository.delete(measureScale1);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void getAllDeviceSetByTechnicalInstitutionId() throws Exception {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        Set<DeviceSet> deviceSets = new HashSet<>();
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSets.add(deviceSet);
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setDepartment(department);
        deviceSets.add(deviceSet1);
        deviceSetRepository.save(deviceSets);

        logger.info("断言");
        String content = this.mockMvc.perform(get("/DeviceSet/getAllDeviceSetByTechnicalInstitutionId/" + department.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceSet_getAllDeviceSetByTechnicallnstitutionId", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(2);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSets);
        departmentRepository.delete(department);
    }

    @Test
    public void getTest() throws Exception {
        logger.info("新建一个部门");
        Department department = new Department();
        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("name");
        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        Accuracy accuracy1 = new Accuracy();
        accuracyRepository.save(accuracy);
        accuracyRepository.save(accuracy1);
        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        MeasureScale measureScale1 = new MeasureScale();
        measureScaleRepository.save(measureScale);
        measureScaleRepository.save(measureScale1);
        logger.info("新建授权鉴定项目实体");
        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrument1.setMeasureScale(measureScale1);
        deviceInstrument1.setAccuracy(accuracy1);
        deviceInstruments.add(deviceInstrument);
        deviceInstruments.add(deviceInstrument1);
        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("模拟请求");
        String content = this.mockMvc.perform(get("/DeviceSet/" + deviceSet.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                //.andDo(print())
                .andDo(document("DeviceSet_get", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();

        logger.info("删除实体");
        deviceInstrumentRepository.delete(deviceInstruments);
        accuracyRepository.delete(accuracy);
        accuracyRepository.delete(accuracy1);
        measureScaleRepository.delete(measureScale);
        measureScaleRepository.delete(measureScale1);
        deviceSetRepository.delete(deviceSet);
        departmentRepository.delete(department);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("测试");
        this.mockMvc.perform(delete("/DeviceSet/" + deviceSet.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("DeviceSet_delete", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void update() throws Exception {
        logger.info("创建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("name");
        deviceSetRepository.save(deviceSet);
        logger.info("修改更新数据");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", deviceSet.getId());
        jsonObject.put("name", "haha");

        this.mockMvc.perform(put("/DeviceSet/" + deviceSet.getId().toString())
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("DeviceSet_update", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void pageAllByCurrentManagementDepartmentUser() throws Exception {
        logger.info("获取市管理部门User并设置为当前登录用户");
        User user = userRepository.findOneByUsername("user4");
        userService.setCurrentLoginUser(user);

        logger.info("选测试区县并添加标准装置");
        Department department = departmentRepository.findByName("测试区县技术机构");

        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        this.mockMvc.perform(get("/DeviceSet/pageAllOfCurrentUser" + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceSet_pageAllOfCurrentUser", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() throws Exception {
        String code = CommonService.getRandomStringByLength(10);
        String name = CommonService.getRandomStringByLength(10);
        Pageable pageable = new PageRequest(0, 2);
        Page<DeviceSet> deviceSets = null;
        DeviceSet deviceSet = new DeviceSet();
        Department department = new Department();
        District district = new District();

        deviceSetServiceTestData.pageAllOfCurrentUserBySpecification(name, code, department, district, deviceSet);

        String url = "/DeviceSet/pageAllOfCurrentUserBySpecification?districtId=0&departmentId=0&name=" + name + "&code="
                + code + "&page=0&size=2";

        String content = this.mockMvc
                        .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                        .andExpect(status().isOk())
                        .andDo(document("DeviceSet_pageAllOfCurrentUserBySpecification", preprocessResponse(prettyPrint())))
                        .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        //删除数据
        deviceSetRepository.delete(deviceSet);
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }
}
