package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.StandardDeviceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-14.
 */
public class StandardDeviceCheckDetailRepositoryTest extends RepositoryTest{
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Autowired
    private  StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Autowired
    private StandardDeviceRepository standardDeviceRepository;
    @Autowired
    private QualifierCertificateRepository qualifierCertificateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Test
    public void save() {
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        //保存
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        //断言
        assertThat(standardDeviceCheckDetail.getId()).isNotNull();
    }

}