package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/19.
 * 计量标准装置：授权检定项目
 */
public class DeviceSetDeviceInstrumentControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(DeviceSetDeviceInstrumentControllerTest.class.getName());
    @Autowired
    private DeviceSetRepositoryTestDataInit deviceSetRepositoryTestDataInit;
    @Autowired
    private MeasureScaleRepository measureScaleRepository;
    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;

    @Test
    public void countByAccuracyIdAndMeasureScaleIdAndDepartmentId() throws Exception {
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        Department department = new Department();
        deviceSetRepositoryTestDataInit.countByDeviceInstrumentAndDeviceSetDepartment(deviceInstrument, department);
        logger.info("调用方法，并断言返回的条目数为1");
        String url = "/DeviceSetDeviceInstrument/countByAccuracyIdAndMeasureScaleIdAndDepartmentId" +
                "/accuracyId/" +
                deviceInstrument.getAccuracy().getId().toString() +
                "/measureScaleId/" +
                deviceInstrument.getMeasureScale().getId().toString() +
                "/departmentId/" +
                department.getId().toString();

        MvcResult result = this.mockMvc
                .perform(get(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("DeviceSetDeviceInstrument_countByAccuracyIdAndMeasureScaleIdAndDepartmentId", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("1");
    }

    @Test
    public void deleteTest() throws Exception {
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

        logger.info("请求路由" + accuracy.getId() + measureScale.getId().toString() + deviceSet.getId().toString());
        logger.info("请求路由" + accuracy1.getId() + measureScale1.getId().toString() + deviceSet.getId().toString());
        String url = "/DeviceSetDeviceInstrument/" +
                "accuracyId/" +
                accuracy.getId().toString() +
                "/measureScaleId/" +
                measureScale.getId().toString() +
                "/deviceSetId/" +
                deviceSet.getId().toString();

        logger.info("测试" + deviceSet.getDeviceInstruments().size());
        this.mockMvc.perform(delete(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("DeviceSetDeviceInstrument_delete", preprocessResponse(prettyPrint())));

        logger.info("断言删除成功, 中间表与deviceSet关联的本来应该有2条数据，删除后，中间表有一条数据");
        Set<DeviceInstrument> deviceInstruments1 = deviceSetRepository.findOne(deviceSet.getId()).getDeviceInstruments();
        assertThat(deviceInstruments1.size()).isEqualTo(1);

        logger.info("删除数据");
        deviceInstrumentRepository.delete(deviceInstruments);
        accuracyRepository.delete(accuracy);
        accuracyRepository.delete(accuracy1);
        measureScaleRepository.delete(measureScale);
        measureScaleRepository.delete(measureScale1);
        deviceSetRepository.delete(deviceSet);
    }


}