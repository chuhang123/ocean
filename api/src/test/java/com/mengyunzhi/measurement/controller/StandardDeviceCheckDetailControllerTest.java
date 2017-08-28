package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetail;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-5-14.
 */
public class StandardDeviceCheckDetailControllerTest extends ControllerTest{
    static private Logger logger = Logger.getLogger(StandardDeviceCheckDetailControllerTest.class.getName());
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Autowired
    private StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Autowired
    private StandardDeviceRepository standardDeviceRepository;
    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("certificateNum", "证书编号");
        jsonObject.put("validityTime", "有效期至");
        jsonObject.put("checker", "检定员");
        jsonObject.put("examiner", "核验员");
        jsonObject.put("correctValue", "修正值");

        //标准器id
        JSONObject standardDeviceJsonObject = new JSONObject();
        //得到一个主标准器
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);
        standardDeviceJsonObject.put("id", standardDevice.getId());
        //标准器绑定西那供应的实体
        jsonObject.put("standardDevice", standardDeviceJsonObject);

        this.mockMvc.perform(post("/StandardDeviceCheckDetail/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceCheckDetail_save", preprocessResponse(prettyPrint())));
    }
    @Test
    public void update() throws Exception{
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setCalibrationDepartment("danweia");
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", standardDeviceCheckDetail.getId());
        jsonObject.put("calibrationDepartment", "danweib");

        
        this.mockMvc.perform(put("/StandardDeviceCheckDetail/update/" + standardDeviceCheckDetail.getId().toString())
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceCheckDetail_update", preprocessResponse(prettyPrint())));

        StandardDeviceCheckDetail standardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(standardDeviceCheckDetail.getId());
        assertThat(standardDeviceCheckDetail1.getCalibrationDepartment()).isEqualTo("danweib");
    }
    @Test
    public void deletetest() throws Exception{
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        this.mockMvc.perform(delete("/StandardDeviceCheckDetail/delete/"+standardDeviceCheckDetail.getId().toString())
                .contentType("application/jeson")
                .header("x-auth-token",xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceCheckDetail_update",preprocessResponse(prettyPrint())));
        StandardDeviceCheckDetail standardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(standardDeviceCheckDetail.getId());
        assertThat(standardDeviceCheckDetail1).isNull();
    }

//    @Test
//    public void getAllByStandardDevice() throws Exception{
//        //获取一个standardDevice(标准器)
//        StandardDevice standardDevice = new StandardDevice();
//        standardDeviceService.save(standardDevice);
//        //java对象转化为json对象
//        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(standardDevice);
//
//        //测试
//        this.mockMvc.perform(post("/StandardDeviceCheckDetail/getAllByStandardDevice")
//                .contentType("application/json")
//                .content(jsonObject.toString())
//                .header("x-auth-token", xAuthToken))
//                .andExpect(status().isOk())
//                .andDo(document("StandardDeviceCheckDetail_getAllByStandardDevice", preprocessResponse(prettyPrint())));
//    }

    @Test
    public void pageAllByStandardDeviceId() throws Exception {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceRepository.save(standardDevice);
        logger.info("新建一个标准器检定信息");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setStandardDevice(standardDevice);
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);

        logger.info("测试");
        this.mockMvc.perform(get("/StandardDeviceCheckDetail/pageAllByStandardDeviceId/" + standardDevice.getId().toString() + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                //.andDo(print())
                .andExpect(status().isOk())
                .andDo(document("StandardDeviceCheckDetail_pageAllByStandardDeviceId", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        standardDeviceCheckDetailRepository.delete(standardDeviceCheckDetail);
        standardDeviceRepository.delete(standardDevice);
    }
}