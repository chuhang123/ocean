package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertifiedProduct;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liming on 17-4-28.
 */
public class CertifiedProductRepository1ServiceTest extends ServiceTest{

    @Autowired
    private CertifiedProductService certifiedProductService;

    @Test
    public void save() throws Exception {
    }

    @Test
    public void getAll() throws Exception {

        //打印数据
        System.out.println(certifiedProductService.getAll());
    }

}