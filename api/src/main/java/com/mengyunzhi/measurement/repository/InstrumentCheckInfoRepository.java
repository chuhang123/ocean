package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-25.
 */
public interface InstrumentCheckInfoRepository extends CrudRepository<InstrumentCheckInfo, Long>, JpaSpecificationExecutor {
    @Query("select i from #{#entityName} i where i.checkDepartment=:checkDepartment order by i.mandatoryInstrument.name")
    Page<InstrumentCheckInfo> getAllByCheckDepartment(@Param("checkDepartment") Department checkDepartment, Pageable pageable);

//    @Query("select i from #{#entityName} i where i.mandatoryInstrument.id = :mandatoryInstrumentId")
    Page<InstrumentCheckInfo> findAllByMandatoryInstrumentId(Long mandatoryInstrumentId, Pageable pageable);

    @Query("select i from #{#entityName} i where i.mandatoryInstrument.department = :department")
    Page<InstrumentCheckInfo> findAllByDepartment(@Param("department") Department department, Pageable pageable);

    @Query("select i from #{#entityName} i where i.mandatoryInstrument.department.district in :districts")
    Page<InstrumentCheckInfo> findAllByDistricts(@Param("districts")List<District> districts, Pageable pageable);
}
