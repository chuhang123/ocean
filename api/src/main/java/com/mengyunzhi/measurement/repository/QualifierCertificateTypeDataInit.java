package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/27.
 */
@Component
public class QualifierCertificateTypeDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered{
    private static Logger logger = Logger.getLogger(QualifierCertificateTypeDataInit.class.getName());
    @Autowired private QualifierCertificateTypeRepository qualifierCertificateTypeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<QualifierCertificateType> qualifierCertificateTypes = (List<QualifierCertificateType>) qualifierCertificateTypeRepository.findAll();
        if (qualifierCertificateTypes.size() == 0) {
            Set<QualifierCertificateType> qualifierCertificateTypes1 = new HashSet<>();

            QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
            qualifierCertificateType.setName("检定证书");
            qualifierCertificateType.setPinyin("jiandingzhengshu");
            qualifierCertificateTypes1.add(qualifierCertificateType);

            QualifierCertificateType qualifierCertificateType1 = new QualifierCertificateType();
            qualifierCertificateType1.setName("测试证书");
            qualifierCertificateType1.setPinyin("ceshizhengshu");
            qualifierCertificateTypes1.add(qualifierCertificateType1);

            QualifierCertificateType qualifierCertificateType2 = new QualifierCertificateType();
            qualifierCertificateType2.setName("校准证书");
            qualifierCertificateType2.setPinyin("jiaozhunzhengshu");
            qualifierCertificateTypes1.add(qualifierCertificateType2);

            qualifierCertificateTypeRepository.save(qualifierCertificateTypes1);
        }
    }


        @Override
        public int getOrder() {return HIGHEST_PRECEDENCE;}
}
