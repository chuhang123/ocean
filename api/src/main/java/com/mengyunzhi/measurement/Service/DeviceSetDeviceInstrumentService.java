package com.mengyunzhi.measurement.Service;

import org.hibernate.ObjectNotFoundException;

/**
 * Created by panjie on 17/7/19.
 * 计量标准装置：授权检定项目
 */
public interface DeviceSetDeviceInstrumentService {
    int countByAccuracyIdAndMeasureScaleIdAndDepartmentId(Long accuracyId, Long measureScaleId, Long departmentId) throws ObjectNotFoundException;
    void delete(Long accuracyId, Long measureScaleId, Long deviceSetId);
}
