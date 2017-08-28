package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhangjiahao on 2017/5/27.
 * 人员资质
 */
public interface QualifierCertificateRepository extends CrudRepository<QualifierCertificate , Long> {
    Page<QualifierCertificate> findAllByQualifierId(Long qualifierId, Pageable pageable);
}
