package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.QualifierCertificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhangjiahao on 2017/7/20.
 * 人员资质
 */
public interface QualifierCertificateService {
//    给当前人员添加人员资质
    QualifierCertificate addQualifierCertificateOfCurrentUser(QualifierCertificate qualifierCertificate) throws SecurityException;

//    编辑当前人员的资质
    QualifierCertificate updateQualifierCertificateOfCurrentUser(Long id, QualifierCertificate qualifierCertificate) throws SecurityException;

//    获取当前人员的全部人员资质并分页
    Page<QualifierCertificate> getAllByCurrentUser(Long qualifierId, Pageable pageable) throws SecurityException;

//    删除当前人员的人员资质
    void delete(Long qualifierCertificateId) throws SecurityException;
}



