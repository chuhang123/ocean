package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertifiedProduct;
import com.mengyunzhi.measurement.repository.CertifiedProductRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
@Service
public class CertifiedProductServicelmpl implements CertifiedProductService {

    @Autowired
    private CertifiedProductRepository certifiedProductRepository;
    @Override
    public CertifiedProduct save(CertifiedProduct certifiedProduct) {
        try {
            //保存数据
            certifiedProductRepository.save(certifiedProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return certifiedProduct;
    }

    @Override
    public List<CertifiedProduct> getAll() {

        List<CertifiedProduct> list = new ArrayList<CertifiedProduct>();
        list = (List<CertifiedProduct>) certifiedProductRepository.findAll();
        return list;
    }
}