package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentCertificateType;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfoRepository;

import java.util.List;

/**
 * Created by liming on 17-6-8.
 * 器具证书类别
 */
public interface InstrumentCertificateTypeService {
    //保存方法
    InstrumentCertificateType save(InstrumentCertificateType instrumentCertificateType);
    //获取一条记录
    InstrumentCertificateType get(Long id);
    //删除
    void delete(Long id);
    //获取所有数据
    List<InstrumentCertificateType> getAll();
}
