package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-7.
 */
public class CertifiedProductRepositoryTest extends RepositoryTest {
   @Autowired
    private CertifiedProductRepository certifiedProductRepository;

   @Test
   public void save() {
       CertifiedProduct certifiedProduct = new CertifiedProduct();
       certifiedProductRepository.save(certifiedProduct);

       assertThat(certifiedProduct.getId()).isNotNull();
   }
}