package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/7/18.
 * 人员 M层test
 */
public class QualifierServiceTest extends ServiceTest {
    private static Logger logger = Logger.getLogger(QualifierServiceTest.class.getName());

    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private QualifierService qualifierService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void getAllByDepartmentId() throws Exception {
        logger.info("---- getAllByDepartmentId方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setName("qualifierA");
        qualifierA.setDepartment(department);
        qualifierRepository.save(qualifierA);

        logger.info("新建一个人员实体B");
        Qualifier qualifierB = new Qualifier();
        qualifierB.setName("qualifierB");
        qualifierB.setDepartment(department);
        qualifierRepository.save(qualifierB);

        final PageRequest pageRequest = new PageRequest(1, 1);

        Page<Qualifier> qualifiers = qualifierService.getAllByDepartmentId(department.getId(), pageRequest);
        assertThat(qualifiers.getTotalPages()).isEqualTo((int) qualifiers.getTotalElements());
        assertThat(qualifiers.getContent().size()).isEqualTo(1);

        logger.info("删除用于测试的实体");
        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        departmentRepository.delete(department);
        return;
    }

    @Test
    public void addQualifierOfCurrentLoginUserDepartmentTest() throws Exception {
        logger.info("---- addQualifierByCurrentLoginUserDepartment方法测试 ---- ");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个user实体");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setName("qualifierA");
        qualifierService.addQualifierOfCurrentLoginUserDepartment(qualifierA);
        assertThat(qualifierA.getDepartment()).isEqualTo(user.getDepartment());

        logger.info("删除用于测试的实体");
        qualifierRepository.delete(qualifierA);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void updateQualifierOfCurrentLoginUserDepartment() throws Exception {
        logger.info("---- updateQualifierOfCurrentLoginUserDepartment方法测试 ---- ");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("添加一个用户并设置为当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setDepartment(user.getDepartment());
        qualifierA.setName("qualifierA");
        qualifierRepository.save(qualifierA);

        logger.info("修改实体A");
        qualifierA.setName("update");
        qualifierService.updateQualifierOfCurrentLoginUserDepartment(qualifierA.getId(),qualifierA);

        logger.info("断言修改成功");
        assertThat(qualifierA.getName()).isEqualTo("update");
        assertThat(qualifierA.getDepartment()).isEqualTo(user.getDepartment());

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        qualifierRepository.delete(qualifierA);
        userRepository.delete(user);
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- delete方法测试 ----");
        logger.info("新建一个部门实体A");
        Department departmentA = new Department();
        departmentA.setName("departmentA");
        departmentRepository.save(departmentA);

        logger.info("新建一个user实体,并设置为当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(departmentA);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setName("qualifierA");
        qualifierA.setDepartment(user.getDepartment());
        qualifierRepository.save(qualifierA);

        logger.info("删除该实体");
        qualifierService.delete(qualifierA.getId());

        logger.info("查询该实体，并断言删除成功");
        Qualifier qualifier = qualifierRepository.findOne(qualifierA.getId());
        assertThat(qualifier).isNull();

        logger.info("删除用于测试的实体");
        userRepository.delete(user);
        departmentRepository.delete(departmentA);
    }

    @Test
    public void getAllByCurrentLoginUserDepartment() throws Exception {
        logger.info("getAllByCurrentLoginUserDepartment方法测试");
        logger.info("新建一个部门实体");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("新建一个用户并设置成当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setDepartment(department);
        qualifierA.setName("qualifierA");
        qualifierRepository.save(qualifierA);

        logger.info("新建一个人员实体B");
        Qualifier qualifierB = new Qualifier();
        qualifierB.setName("qualifierB");
        qualifierB.setDepartment(department);
        qualifierRepository.save(qualifierB);

        List<Qualifier> qualifiers = qualifierService.getAllByCurrentLoginUserDepartment();
        assertThat(qualifiers).size().isEqualTo(2);

        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void getAllByCurrentLoginUser() throws Exception {
        logger.info("新建一个部门实体");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("新建一个用户并设置成当前登录用户");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setDepartment(department);
        qualifierRepository.save(qualifierA);

        logger.info("新建人员实体B");
        Qualifier qualifierB = new Qualifier();
        qualifierB.setDepartment(department);
        qualifierRepository.save(qualifierB);

        final PageRequest pageRequest = new PageRequest(1,1);
        Page<Qualifier> qualifiers = qualifierService.getAllByCurrentLoginUser(pageRequest);
        assertThat(qualifiers.getTotalPages()).isEqualTo((int) qualifiers.getTotalElements());
        assertThat(qualifiers.getContent().size()).isEqualTo(1);

        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

}