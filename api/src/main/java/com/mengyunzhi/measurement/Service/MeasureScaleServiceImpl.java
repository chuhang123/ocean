package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasureScale;
import com.mengyunzhi.measurement.repository.MeasureScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by panjie on 17/7/31.
 * 测量范围
 */
@Service("MeasureScaleService")
public class MeasureScaleServiceImpl implements MeasureScaleService {
    @Autowired private MeasureScaleRepository measureScaleRepository; // 测量范围
    @Override
    public MeasureScale getOneMeasureScale() {
        MeasureScale measureScale  = new MeasureScale();
        measureScale.setValue(CommonService.getRandomStringByLength(10));
        return measureScaleRepository.save(measureScale);
    }
}
