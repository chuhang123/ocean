package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DeviceInstrument;
import com.mengyunzhi.measurement.repository.DeviceSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目
 */
public interface DeviceInstrumentService {
    void save(DeviceSet deviceSet);

    Page<?> pageAllByCurrentUserOfDeviceInstrument(Pageable pageable);

    Page<?> pageAllOfCurrentUserOfManagement(Pageable pageable);
    // 综合查询授权检定项目信息
    Page<DeviceInstrument> pageByDeviceSetOfCurrentUser(DeviceSet deviceSet, Pageable pageable);
    Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(Long deviceSetId, Pageable pageable);

    //综合查询条件授权检定项目 name, deviceSetId, districtId, departmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId
    Page<DeviceInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long deviceSetId, Long districtId, Long departmentId, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable);
    DeviceInstrument getOneUnSavedDeviceInstrument();

}
