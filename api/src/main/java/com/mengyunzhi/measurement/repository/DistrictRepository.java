package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by administrator on 2017/5/19.
 * 区域
 */
public interface DistrictRepository extends CrudRepository<District, Long> {
    District getByName(String name);
    List<District> getAllByParentDistrictId(Long id);
    List<District> getAllByParentDistrict(District district);
    District findTopOneByParentDistrictIsNull();
}
