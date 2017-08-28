package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chuhang on 17-5-23.
 * 强检器具
 * 实现了JpaSpecificationExecutor，则自动增加：
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#specifications
 */
public interface MandatoryInstrumentRepository extends PagingAndSortingRepository<MandatoryInstrument, Long>, JpaSpecificationExecutor {
    List<MandatoryInstrument> findAllByMandatoryInstrumentApplyId(Long instrumentApplyId);

    Page<MandatoryInstrument> findAllByCheckDepartment(Department checkDepartment, Pageable pageable);

    Page<MandatoryInstrument> findAllByDepartment(Department department, Pageable pageable);

    @Query("select i from #{#entityName} i where i.checkDepartment.district in :districts")
    Page<MandatoryInstrument> findAllByDistricts(@Param("districts") List<District> districts, Pageable pageable);

    List<MandatoryInstrument> findAllByCheckDepartment(Department department);
}
