package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasureScale;
import com.mengyunzhi.measurement.repository.MeasureScaleRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentType;
import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentTypeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-27.
 * 标准器类别Service TEST
 */
public class StandardDeviceInstrumentTypeServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(StandardDeviceInstrumentTypeServiceTest.class.getName());
    @Autowired protected StandardDeviceInstrumentTypeRepository standardDeviceInstrumentTypeRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;
    @Autowired protected StandardDeviceInstrumentTypeService standardDeviceInstrumentTypeService;
    @Test
    public void getByMeasureScaleId() throws Exception {
        logger.info("标准器类别");
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        standardDeviceInstrumentType.setName("name");
        standardDeviceInstrumentType.setPinyin("pinyin");
        standardDeviceInstrumentTypeRepository.save(standardDeviceInstrumentType);
        logger.info("新建一个测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(standardDeviceInstrumentType);
        measureScaleRepository.save(measureScale);
        logger.info("测试");
        StandardDeviceInstrumentType standardDeviceInstrumentType1 = standardDeviceInstrumentTypeService.getByMeasureScaleId(measureScale.getId());

        logger.info("断言");
        assertThat(standardDeviceInstrumentType1.getId()).isEqualTo(standardDeviceInstrumentType.getId());
        logger.info("删除数据");
        measureScaleRepository.delete(measureScale);
        standardDeviceInstrumentTypeRepository.delete(standardDeviceInstrumentType);
    }

}