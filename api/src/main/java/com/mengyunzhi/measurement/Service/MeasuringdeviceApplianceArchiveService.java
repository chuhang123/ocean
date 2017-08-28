package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasuringdeviceApplianceArchive;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
public interface MeasuringdeviceApplianceArchiveService {
    //保存单个对象
    MeasuringdeviceApplianceArchive save(MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive);

    //返回数据表中的所有实体
    List<MeasuringdeviceApplianceArchive> getAll();
}
