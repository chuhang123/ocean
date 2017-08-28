package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目 实体仓库测试
 */
public class DeviceInstrumentRepositoryTest extends RepositoryTest {
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    private MeasureScaleRepository measureScaleRepository;
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;

    @Test
    public void save() {

        //存精度
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);

        //存测量范围
        MeasureScale measureScale = new MeasureScale();
        measureScaleRepository.save(measureScale);

        //取id
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrumentRepository.save(deviceInstrument);

        //删除实体
        deviceInstrumentRepository.delete(deviceInstrument);
    }


}