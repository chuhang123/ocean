package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liming on 17-5-17.
 * 申请类型
 */
public interface ApplyTypeRepository extends CrudRepository<ApplyType, Long> {
    ApplyType findOneByName(String name);
    ApplyType findOneByWebAppMenuId(Long webAppMenuId);
}
