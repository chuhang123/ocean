package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertificateStatus;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/8/3.
 * 证书状态 接口
 */
public interface CertificateStatusService {
//    获取全部的证书状态
    List<CertificateStatus> getAll();
}
