package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
public interface InstrumentFirstLevelTypeRepository extends CrudRepository<InstrumentFirstLevelType, Long> {
    List<InstrumentFirstLevelType> findAllByDisciplineId(Long disciplineId);
}
