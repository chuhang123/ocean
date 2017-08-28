package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 */
public interface DepartmentTypeRepository extends CrudRepository<DepartmentType, Long> {
    DepartmentType getByName(String name);
    DepartmentType findOneByName(String name);
    DepartmentType findOneByPinyin(String pinyin);
}
