package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-7-27.
 * 标准器具类别Repository
 */
public interface StandardDeviceInstrumentTypeRepository extends CrudRepository<StandardDeviceInstrumentType, Long> {
    List<StandardDeviceInstrumentType> getAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId);
    @Query("select m from #{#entityName} m where m.instrumentFirstLevelType.discipline.id = :disciplineId")
    Page<StandardDeviceInstrumentType> pageByDisciplineId(@Param("disciplineId") Long disciplineId, Pageable pageable);
}
