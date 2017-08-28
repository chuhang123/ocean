package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/8/15.
 * 证书状态 数据初始化
 */
@Component
public class CertificateStatusDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered{
    private Logger logger = Logger.getLogger(CertificateStatusDataInit.class.getName());
    @Autowired
    private CertificateStatusRepository certificateStatusRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        List<CertificateStatus> certificateStatuse1 = (List<CertificateStatus>) certificateStatusRepository.findAll();
        if (certificateStatuse1.size() == 0)
        {
            logger.info("添加证书状态类型");
            HashSet<CertificateStatus> certificateStatuses = new HashSet<CertificateStatus>();
            certificateStatuses.add(new CertificateStatus("存档","cundang",20170816L,20170817L,null));
            certificateStatusRepository.save(certificateStatuses);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
