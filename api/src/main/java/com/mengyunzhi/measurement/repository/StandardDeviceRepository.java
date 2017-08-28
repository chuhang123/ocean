package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-9.
 * 标准器实体仓库
 */
public interface StandardDeviceRepository extends CrudRepository<StandardDevice, Long> {
    //通过标准装置id获取相应的标准器
    List<StandardDevice> findAllByDeviceSetId(Long deviceSetId);

    //通过标准装置id获取相应的标准器分页功能
    Page<StandardDevice> findAllByDeviceSetId(Long deviceSetId, Pageable pageable);
}
