package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by administrator on 2017/5/24.
 */
@RepositoryRestResource(collectionResourceRel = "Accuracy", path = "Accuracy")
public interface AccuracyRepository extends PagingAndSortingRepository<Accuracy,Long> {
}
