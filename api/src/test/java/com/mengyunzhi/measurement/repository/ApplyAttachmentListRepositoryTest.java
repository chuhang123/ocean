
package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by administrator on 2017/5/20.
 */
public class ApplyAttachmentListRepositoryTest extends RepositoryTest{

    @Autowired
    private ApplyAttachmentListRepository applyAttachmentListRepository;            //申请附件列表仓库

    @Test
    public void findAll(){
        applyAttachmentListRepository.findAll();
    }

}