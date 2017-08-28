package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Created by liming on 17-5-9.
 */
public class StandardDeviceControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(StandardDeviceControllerTest.class.getName());
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    protected StandardDeviceService standardDeviceService;

    @Test
    public void getAllByStandardFileId() throws Exception {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("测试");

        String content = this.mockMvc.perform(get("/StandardDevice/getAllByDeviceSetId/" + deviceSet.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_getAllByStandardFile", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(1);
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void save() throws Exception {
        logger.info("新建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        JSONObject deviceSetJsonObject = new JSONObject();
        deviceSetJsonObject.put("id", deviceSet.getId());
        logger.info("新建一个标准器");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceSet", deviceSetJsonObject);

        logger.info("保存");
        this.mockMvc.perform(post("/StandardDevice/save")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_save", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);

    }

    @Test
    public void update() throws Exception {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setName("name");
        standardDeviceService.save(standardDevice);
        logger.info("更改名字");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "newName");
        this.mockMvc.perform(put("/StandardDevice/update/" + standardDevice.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().is(204))
                .andDo(document("StandardDevice_update", preprocessResponse(prettyPrint())));

        logger.info("删除实体");
        standardDeviceRepository.delete(standardDevice);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);
        this.mockMvc.perform(delete("/StandardDevice/delete/" + standardDevice.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("StandardDevice_delete", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageAllByDeviceSetId() throws Exception {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("测试");

        this.mockMvc.perform(get("/StandardDevice/getAllByDeviceSetId/" + deviceSet.getId().toString() + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_pageAllByStandardFile", preprocessResponse(prettyPrint())));
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }
}