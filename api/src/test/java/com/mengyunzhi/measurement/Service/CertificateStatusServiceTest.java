package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CertificateStatus;
import com.mengyunzhi.measurement.repository.CertificateStatusRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by zhangjiahao on 2017/8/3.
 * 证书状态 M层测试
 */
public class CertificateStatusServiceTest extends ServiceTest{
    private Logger logger = Logger.getLogger(CertificateStatusServiceTest.class.getName());

    @Autowired
    private CertificateStatusRepository certificateStatusRepository;
    @Autowired
    private CertificateStatusService certificateStatusService;

    @Test
    public void getAllTest() throws Exception {
        logger.info("----getAll测试----");
        logger.info("新建一个证书状态实体");
        List<CertificateStatus> certificateStatuse1 = (List<CertificateStatus>) certificateStatusRepository.findAll();
        CertificateStatus certificateStatus = new CertificateStatus();

        certificateStatus.setCreateTime(2L);
        certificateStatusRepository.save(certificateStatus);
        logger.info("调用getAll方法并断言取出来的数据不为空");
        List<CertificateStatus> certificateStatuses = certificateStatusService.getAll();
        assertThat(certificateStatuses.size()).isEqualTo(certificateStatuse1.size()+1);
    }

}