package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liming on 17-4-28.
 * 封装了对数据库的操作
 */
public interface MeasuringdeviceApplianceArchiveRepository extends PagingAndSortingRepository<MeasuringdeviceApplianceArchive, Long> {
}
