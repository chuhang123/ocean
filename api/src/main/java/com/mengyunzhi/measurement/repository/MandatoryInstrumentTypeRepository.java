package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 强制检定器具分类
 */
public interface MandatoryInstrumentTypeRepository extends CrudRepository<MandatoryInstrumentType, Long> {
    // 查找某个一级分类的所有信息
    List<MandatoryInstrumentType> findAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId);

    @Query("select m from #{#entityName} m where m.instrumentFirstLevelType.discipline.id = :disciplineId")
    Page<MandatoryInstrumentType> findAllByDisciplineId(@Param("disciplineId") Long disciplineId, Pageable pageable);
}
