package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by liming on 17-5-18.
 * 加入@RepositoryRestResource注解，解决跨域的问题
 */
@RepositoryRestResource(collectionResourceRel = "QualifierCertificateType" , path = "QualifierCertificateType")
public interface QualifierCertificateTypeRepository extends PagingAndSortingRepository<QualifierCertificateType, Long> {
    Page<QualifierCertificateType> findAllByDisciplineId(Long disciplineId, Pageable pageble);
}
