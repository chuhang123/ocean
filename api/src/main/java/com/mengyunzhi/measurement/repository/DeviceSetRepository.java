package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-23.
 * 标准装置
 */
public interface DeviceSetRepository extends CrudRepository<DeviceSet, Long>, JpaSpecificationExecutor {
    Page<DeviceSet> findAll(Pageable pageable);
    List<DeviceSet> findAllByDepartmentId(Long departmentId);
    Page<DeviceSet> findAllByDepartment(Department department, Pageable pageable);
    // 某部门检定某个授权项目的个数
    @Query("select count(*) from #{#entityName} ds JOIN  ds.deviceInstruments dis where ds.department=:department and dis=:deviceInstrument")
    int countByDepartmentAndDeviceInstrument(@Param("department") Department department, @Param("deviceInstrument") DeviceInstrument deviceInstrument);
    // 获取当前登录管理部门的所有技术机构
    @Query("select d from #{#entityName} d where d.department.departmentType.name = '技术机构' and d.department.district in :districts")
    Page<DeviceSet> findAllByDisctricts(@Param("districts") List<District> districts, Pageable pageable);
}
