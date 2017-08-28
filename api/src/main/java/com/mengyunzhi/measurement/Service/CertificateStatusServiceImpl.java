package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertificateStatus;
import com.mengyunzhi.measurement.repository.CertificateStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/8/3.
 * 证书状态接口层实现
 */
@Service
public class CertificateStatusServiceImpl implements CertificateStatusService {
    @Autowired
    private CertificateStatusRepository certificateStatusRepository;

    @Override
    public List<CertificateStatus> getAll() {
        List<CertificateStatus> certificateStatuses = (List<CertificateStatus>)certificateStatusRepository.findAll();
        return certificateStatuses;
    }
}
