package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.AccuracyService;
import com.mengyunzhi.measurement.Service.DeviceSetService;
import com.mengyunzhi.measurement.Service.MeasureScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/19.
 * 计量标准装置：授权检定项目 = N:N
 */
@Component
public class DeviceSetRepositoryTestDataInit {
    private Logger logger = Logger.getLogger(DeviceSetRepositoryTestDataInit.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;   // 部门
    @Autowired private DeviceSetRepository deviceSetRepository;     // 标准装置
    @Autowired private AccuracyRepository accuracyRepository;       // 精度
    @Autowired private MeasureScaleRepository measureScaleRepository;   // 测量范围
    @Autowired private DeviceInstrumentRepository deviceInstrumentRepository;   // 授权检定项目
    @Autowired private DeviceSetService deviceSetService;                   // 标准装置
    @Autowired private AccuracyService accuracyService;                     // 精确度
    @Autowired private MeasureScaleService measureScaleService;             // 测量范围

    public void countByDeviceInstrumentAndDeviceSetDepartment(DeviceInstrument deviceInstrument, Department department) {
        logger.info("实例化一个部门");
        departmentRepository.save(department);

        logger.info("为这个部门实例化一个标准装置");
        DeviceSet deviceSet = deviceSetService.getOneDeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("实例化一个精度");
        Accuracy accuracy = accuracyService.getOneAccuracy();
        accuracyRepository.save(accuracy);

        logger.info("实例化一个测试范围");
        MeasureScale measureScale = measureScaleService.getOneMeasureScale();
        measureScaleRepository.save(measureScale);

        logger.info("实例化一个 授权检定项目");
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("为标准装置添加一个授权检定项目");
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);
    }
}
