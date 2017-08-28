package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-16.
 */
public interface DistrictTypeRepository extends CrudRepository<DistrictType, Long> {
    DistrictType findOneByName(String name);
}
