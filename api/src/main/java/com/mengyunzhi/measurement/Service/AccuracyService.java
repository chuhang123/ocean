package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Accuracy;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
public interface AccuracyService {
    // 保存
    Accuracy save(Accuracy accuracy);

    Accuracy getOneAccuracy();
}
