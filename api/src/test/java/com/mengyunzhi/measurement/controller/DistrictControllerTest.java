package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-6-2.
 */
public class DistrictControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(DistrictControllerTest.class.getName());
    private JSONObject jsonObject;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DistrictTypeRepository districtTypeRepository;

    @Test
    public void save() throws Exception {
        logger.info("创建一个区域");
        District district = new District();
        logger.info("保存一个区域");
        districtRepository.save(district);

        logger.info("创建一个User");
        User user = new User();
        logger.info("保存User");
        userRepository.save(user);

        logger.info("创建一个区域类型");
        DistrictType districtType = new DistrictType();
        logger.info("保存区域类型");
        districtTypeRepository.save(districtType);

        logger.info("模拟数据");
        jsonObject = new JSONObject();
        jsonObject.put("name", "区域名称");
        jsonObject.put("pinyin", "拼音");

        logger.info("关联User");
        JSONObject userId = new JSONObject();
        jsonObject.put("userId", user.getId());

        logger.info("关联自己");
        JSONObject districtId = new JSONObject();
        districtId.put("id", district.getId());

        logger.info("关联区域类型");
        JSONObject districtTypeId = new JSONObject();
        districtTypeId.put("districtTypeId", districtTypeId);

        jsonObject.put("districtType", districtTypeId);
        jsonObject.put("createUser", userId);
        jsonObject.put("parentDistrict", districtId);
        logger.info("保存");

        MvcResult result = this.mockMvc.perform(post("/District/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("District_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObject对象, 并断言其存在id属性, 且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("创建一个区域");
        District district = new District();
        logger.info("保存一个区域");
        districtRepository.save(district);

        logger.info("取出所有的数据");
        MvcResult result = this.mockMvc.perform(get("/District/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("District_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        logger.info("将返回的内容转化为jsonArray对象， 并断言其长度不为0");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isNotEqualTo(0);
    }

    @Test
    public void getOneDistrict() throws Exception {
        logger.info("创建一个区域");
        District district = new District();
        logger.info("保存一个区域");
        districtRepository.save(district);

        logger.info("发送请求");
        MvcResult result = this.mockMvc.perform(get("/District/get/" + district.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("District_get", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        logger.info("将返回的内容转化为json对象, 并断言刚保存的区域与出去来的区域id相等");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isEqualTo(district.getId());
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("创建一个区域");
        District district = new District();
        logger.info("保存一个区域");
        districtRepository.save(district);
        logger.info("发送请求");
        this.mockMvc.perform(delete("/District/delete/" + district.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("District_delete", preprocessResponse(prettyPrint())));

        logger.info("断言取出从数据库里取出的对象为null");
        assertThat(districtRepository.findOne(district.getId())).isNull();
    }

    @Test
    public void getTreeById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/District/getTreeById/1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("District_getTreeById", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getRootDistrictTree() throws  Exception {
        logger.info("模拟发起请求");
        String url = "/District/getRootDistrictTree";
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(document("District_getRootDistrictTree", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        logger.info("解析json数据");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();
        JSONArray jsonArray = (JSONArray) jsonObject.get("sonDistricts");
        assertThat(jsonArray.size()).isGreaterThan(0);
    }

}