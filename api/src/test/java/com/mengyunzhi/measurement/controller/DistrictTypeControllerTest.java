package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.DistrictType;
import com.mengyunzhi.measurement.repository.DistrictTypeRepository;
import com.mengyunzhi.measurement.repository.User;
import com.mengyunzhi.measurement.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-6-2.
 */
public class DistrictTypeControllerTest extends ControllerTest{
    static private Logger logger = Logger.getLogger(DistrictTypeController.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DistrictTypeRepository districtTypeRepository;
    @Test
    public void save() throws Exception {
        logger.info("创建一个用户");
        User user = new User();
        logger.info("保存一个用户");
        userRepository.save(user);
        logger.info("模拟传送数据（区域类型实体）");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "名称");
        jsonObject.put("pinyin", "拼音");
        JSONObject userId = new JSONObject();
        userId.put("id", user.getId());
        jsonObject.put("createUser", userId);
        logger.info("发送数据");
        MvcResult result =  this.mockMvc.perform(post("/DistrictType/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DistrictType_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObject, 并断言其存在的id属性, 且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("创建一个区域类型实体");
        DistrictType districtType = new DistrictType();
        logger.info("保存实体");
        districtTypeRepository.save(districtType);
        logger.info("取出数据");
        MvcResult result = this.mockMvc.perform(get("/DistrictType/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DistrictType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("获取返回值");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObject, 并断言其长度不是0");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isNotEqualTo(0);
    }

}