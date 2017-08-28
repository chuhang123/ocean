package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

/**
 * Created by liming on 17-7-20.
 * 授权检定项目save实体
 */
public class DeviceInstrumentControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(DeviceInstrumentControllerTest.class.getName());
    @Autowired
    protected AccuracyRepository accuracyRepository;
    @Autowired
    protected MeasureScaleRepository measureScaleRepository;
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DeviceInstrumentService deviceInstrumentService;        // 授权检定项目
    @Autowired
    private DeviceSetService deviceSetService;   //  标准装置
    @Autowired
    private DeviceInstrumentServiceTestData deviceInstrumentServiceTestData;    // 数据准备
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private AccuracyService accuracyService;
    @Autowired
    private MeasureScaleService measureScaleService;

    @Test
    public void save() throws Exception {
        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScaleRepository.save(measureScale);

        logger.info("前台如何绑定多主健实体信息");
        JSONObject deviceInstrumenJsonObject = new JSONObject();
        deviceInstrumenJsonObject.put("accuracyId", accuracy.getId());
        deviceInstrumenJsonObject.put("measureScaleId", measureScale.getId());
        JSONObject adddeviceInstrumenJsonObject = new JSONObject();
        adddeviceInstrumenJsonObject.put("id", deviceInstrumenJsonObject);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(adddeviceInstrumenJsonObject);

        logger.info("新建标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        deviceInstrumenJsonObject.put("id", deviceSet.getId());
        deviceInstrumenJsonObject.put("deviceInstruments", jsonArray);

        logger.info("测试数据");
        this.mockMvc.perform(post("/DeviceInstrument/save")
                .contentType("application/json")
                .content(deviceInstrumenJsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("DeviceInstrument_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageAllByCurrentUserOfDeviceInstrument() throws Exception {
        logger.info("创建一个部门");
        Department department = new Department();
        DepartmentType departmentType = departmentTypeRepository.getByName("技术机构");
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");
        deviceSet.setDepartment(department);

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
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument1.setMeasureScale(measureScale1);
        deviceInstrument1.setAccuracy(accuracy1);

        deviceInstrumentRepository.save(deviceInstrument);
        deviceInstrumentRepository.save(deviceInstrument1);
        deviceInstruments.add(deviceInstrument);
        deviceInstruments.add(deviceInstrument1);

        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSetRepository.save(deviceSet);

        logger.info("模拟请求");
        this.mockMvc.perform(get("/DeviceInstrument/pageAllByCurrentUserOfDeviceInstrument" + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_pageAllByCurrentUserOfDeviceInstrument", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageByDeviceSetOfCurrentUser() throws Exception {

        DeviceSet deviceSet = new DeviceSet();
        User loginUser = deviceInstrumentServiceTestData.pageByDeviceSetOfCurrentUser(deviceSet);

        this.loginByUser(loginUser);
        String url = "/DeviceInstrument/pageByDeviceSetIdOfCurrentUser/" + deviceSet.getId().toString() + "?page=0&size=2";
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("DeviceInstrument_pageByDeviceSetIdOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String content1 = mvcResult.getResponse().getContentAsString();
        assertThat(JSONObject.fromObject(content1).get("totalElements")).isEqualTo(2);

        url = "/DeviceInstrument/pageByDeviceSetIdOfCurrentUser/0?page=0&size=2";
        MvcResult mvcResult1 = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_pageByDeviceSetIdOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String response = mvcResult1.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(response);
        assertThat(jsonObject.get("totalElements")).isEqualTo(4);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() throws Exception {
        logger.info("新建两个区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("使用用户登录");
        User user = userService.loginWithOneUser();
        user.getDepartment().setDistrict(district);
        departmentRepository.save(user.getDepartment());

        String name = CommonService.getRandomStringByLength(10);        // 名称
        Pageable pageable = new PageRequest(0, 2);          //分页信息

        logger.info("创建一个授权检定装置");
        Accuracy accuracy = accuracyService.getOneAccuracy();
        MeasureScale measureScale = measureScaleService.getOneMeasureScale();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("创建标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName(name);
        deviceSet.setDepartment(user.getDepartment());
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);

        String url = "/DeviceInstrument/pageAllOfCurrentUserBySpecification?name=" + name + "&page=0&size=2";

        String content = this.mockMvc
                        .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                        .andExpect(status().isOk())
                        .andDo(document("DeviceInstrument_pageByDeviceSetIdOfCurrentUser", preprocessResponse(prettyPrint())))
                        .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);
    }
}