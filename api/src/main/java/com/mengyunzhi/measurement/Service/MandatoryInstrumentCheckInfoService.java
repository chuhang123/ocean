package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentCheckInfo;
import com.mengyunzhi.measurement.repository.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by chuhang on 17-8-2.
 * 强制器具检定信息的service
 */
public interface MandatoryInstrumentCheckInfoService extends InstrumentCheckInfoService {
    void save(InstrumentCheckInfo instrumentCheckInfo);

    Page<InstrumentCheckInfo> pageAllOfCurrentUser(Pageable pageable);

    void delete(Long id) throws ObjectNotFoundException, SecurityException;

    void update(Long id, InstrumentCheckInfo instrumentCheckInfo);

    //获取一个强检器具的所有检定信息
    Page<InstrumentCheckInfo> pageAllByMandatoryInstrumentId(Long mandatoryInstrumentId, Pageable pageable);

    //获取管理部门旗下的所有器具用户的检定信息
    Page<InstrumentCheckInfo> pageAllOfManagementUser(User currentUser, Pageable pageable);

    // 根据查询条件获取管理部门的强检器具鉴定信息
    Page<InstrumentCheckInfo> pageAllOfManagementDepartmentBySpecification(Integer year, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId, Long accuracyDisplayNameId, Long mandatoryInstrumentId, String name, Pageable pageable) throws SecurityException;

    // 根据查询条件获取器具用户的强检器具鉴定信息
    Page<InstrumentCheckInfo> pageAllOfMeasureUserBySpecification(Integer year, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Long checkResultId, Long mandatoryInstrumentId, String name, Pageable pageable);

    // 根据查询条件获取技术机构的强检器具鉴定信息
    Page<InstrumentCheckInfo> pageAllOfTechnicalInstitutionDepartmentBySpecification(Integer year, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId, Long accuracyDisplayNameId, Long mandatoryInstrumentId, String name, Pageable pageable);
}
