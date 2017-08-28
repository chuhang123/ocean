package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-19.
 * 器具类别实休
 * 使用了分页进行查询：
 * 具体查看官方文档：
 * http://docs.spring.io/spring-data/data-commons/docs/1.1.0.RELEASE/api/org/springframework/data/domain/Pageable.html
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
 * http://docs.spring.io/spring-data/data-commons/docs/1.1.0.RELEASE/api/org/springframework/data/domain/Page.html
 */
public interface InstrumentTypeRepository extends CrudRepository<InstrumentType, Long>{

    @Query("select i from #{#entityName} i where i.instrumentFirstLevelType.discipline.id = :disciplineId")
    Page<InstrumentType> findAllByDisciplineId(@Param("disciplineId") Long disciplineId, Pageable pageable);

    @Query("select i from #{#entityName} i where i.instrumentFirstLevelType.discipline.id = :disciplineId")
    List<InstrumentType> findAllByDisciplineId(@Param("disciplineId") Long disciplineId);

    Page<InstrumentType> findAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId, Pageable pageable);

    List<InstrumentType> findAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId);
}
