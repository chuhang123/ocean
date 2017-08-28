package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 使用@RepositoryRestRescource注解，解决跨域问题
 */
@RepositoryRestResource(collectionResourceRel = "AccuracyDisplayName" , path = "AccuracyDisplayName")
public interface AccuracyDisplayNameRepository extends PagingAndSortingRepository<AccuracyDisplayName, Long> {
}
