package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liming on 17-5-13.
 * 标准器检定信息
 */
public interface StandardDeviceCheckDetailRepository extends CrudRepository<StandardDeviceCheckDetail, Long> {
    List<StandardDeviceCheckDetail> findAllByStandardDevice(StandardDevice standardDevice);
    //获取所有的标准器检定信息通过标准器ID
    Page<StandardDeviceCheckDetail> findAllByStandardDevice(StandardDevice standardDevice, Pageable pageable);
}
