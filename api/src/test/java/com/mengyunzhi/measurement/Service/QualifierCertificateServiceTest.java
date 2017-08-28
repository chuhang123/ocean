package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/7/22.
 */
public class QualifierCertificateServiceTest extends ServiceTest {
    private Logger logger = Logger.getLogger(QualifierCertificateServiceTest.class.getName());

    @Autowired
    private QualifierCertificateRepository qualifierCertificateRepository;
    @Autowired
    private QualifierCertificateService qualifierCertificateService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private QualifierService qualifierService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void addQualifierCertificateOfCurrentUser() throws Exception {
        logger.info("---- addQualifierCertificateOfCurrentUser方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("新建一个user实体，并设置该实体为当前用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体");
        QualifierCertificate qualifierCertificate = new QualifierCertificate();
        qualifierCertificate.setQualifier(qualifier);

        qualifierCertificateService.addQualifierCertificateOfCurrentUser(qualifierCertificate);
        assertThat(qualifierCertificate.getQualifier().getDepartment().getId()).isEqualTo(user.getDepartment().getId());

        logger.info("删除用来测试的实体");
        qualifierCertificateRepository.delete(qualifierCertificate);
        qualifierRepository.delete(qualifier);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void updateQualifierCertificateOfCurrentUser() throws Exception {
        logger.info("---- updateQualifierCertificateOfCurrentUser方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("新建一个user实体，并设置该实体为当前用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体");
        QualifierCertificate qualifierCertificate = new QualifierCertificate();
        qualifierCertificate.setQualifier(qualifier);
        qualifierCertificate.setCreateTime(2L);
        qualifierCertificateRepository.save(qualifierCertificate);

        logger.info("修改人员资质实体并断言修改成功");
        qualifierCertificate.setCreateTime(20L);
        qualifierCertificateService.updateQualifierCertificateOfCurrentUser(qualifierCertificate.getId(),qualifierCertificate);
        assertThat(qualifierCertificate.getCreateTime()).isEqualTo(20L);

        logger.info("删除用来测试的实体");
        qualifierCertificateRepository.delete(qualifierCertificate);
        qualifierRepository.delete(qualifier);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void getAllByCurrentUser() throws Exception {
        logger.info("---- getAllByCurrentUser方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个user实体并设置成为当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体A");
        QualifierCertificate qualifierCertificateA = new QualifierCertificate();
        qualifierCertificateA.setQualifier(qualifier);
        qualifierCertificateA.setCreateTime(2L);
        qualifierCertificateRepository.save(qualifierCertificateA);

        logger.info("新建一个人员资质实体B");
        QualifierCertificate qualifierCertificateB = new QualifierCertificate();
        qualifierCertificateB.setQualifier(qualifier);
        qualifierCertificateB.setCreateTime(2L);
        qualifierCertificateRepository.save(qualifierCertificateB);

        final PageRequest pageRequest = new PageRequest(1,1);

        Page<QualifierCertificate> qualifierCertificates = qualifierCertificateService.getAllByCurrentUser(qualifier.getId(), pageRequest);

        assertThat(qualifierCertificates.getTotalPages()).isEqualTo((int)qualifierCertificates.getTotalElements());
        assertThat(qualifierCertificates.getContent().size()).isEqualTo(1);

        logger.info("删除用来测试的实体");
        qualifierCertificateRepository.delete(qualifierCertificateA);
        qualifierCertificateRepository.delete(qualifierCertificateB);
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifier);
        return;
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- delete方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个user实体并设置成为当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体");
        QualifierCertificate qualifierCertificate = new QualifierCertificate();
        qualifierCertificate.setQualifier(qualifier);
        qualifierCertificate.setCreateTime(2L);
        qualifierCertificateRepository.save(qualifierCertificate);

        logger.info("删除人员资质实体");
        qualifierCertificateService.delete(qualifierCertificate.getId());

        logger.info("查询实体，并断言删除成功");
        QualifierCertificate qualifierCertificate1 = qualifierCertificateRepository.findOne(qualifier.getId());
        assertThat(qualifierCertificate1).isNull();

        logger.info("删除用来测试的实体");
        qualifierRepository.delete(qualifier);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

}