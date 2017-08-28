package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目 实体仓库
 */
public interface DeviceInstrumentRepository extends CrudRepository<DeviceInstrument, Long>, JpaSpecificationExecutor<DeviceInstrument> {
    DeviceInstrument findTopByAccuracyAndMeasureScale(Accuracy accuracy, MeasureScale measureScale);

    //左连接查处所有的数据
    @Query("select deviceInstrument, deviceSet, instrumentType from DeviceSet deviceSet left JOIN deviceSet.deviceInstruments as deviceInstrument inner join deviceInstrument.measureScale.instrumentType as instrumentType  where deviceSet.department=:department and deviceInstrument is not null")
    Page<?> findAllByDepartmentOfDeviceInstrument(@Param("department") Department department, Pageable pageable);

    //获取管理部门所管辖的技术机构的授权检定项目
    @Query("select deviceInstrument, deviceSet, instrumentType from DeviceSet deviceSet left JOIN deviceSet.deviceInstruments as deviceInstrument inner join deviceInstrument.measureScale.instrumentType as instrumentType where deviceSet.department.departmentType.name='技术机构' and deviceSet.department.district in :districts and deviceInstrument is not null order by deviceSet.department")
    Page<?> findAllByDistricts(@Param("districts") List<District> districts, Pageable pageable);

    //左连接查处所有的数据
    @Query("SELECT d FROM #{#entityName} d JOIN d.deviceSets ds WHERE ds.department = :department AND (ds.id = :deviceSetId OR :deviceSetId = NULL)")
    Page<DeviceInstrument> findAllByDeviceSetDepartmentAndDeviceSetId(@Param("department") Department department, @Param("deviceSetId") Long deviceSetId, Pageable pageable);
}
