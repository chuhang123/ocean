package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liming on 17-4-28.
 * 实现实体对数据库的操作
 */
public interface CertifiedProductRepository extends PagingAndSortingRepository<CertifiedProduct,Long> {
}
