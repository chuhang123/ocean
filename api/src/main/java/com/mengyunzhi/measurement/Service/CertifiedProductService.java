package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertifiedProduct;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
public interface CertifiedProductService {

    //保存方法
    CertifiedProduct save(CertifiedProduct certifiedProduct);

    //获取表里边的全部数据
    List<CertifiedProduct> getAll();
}