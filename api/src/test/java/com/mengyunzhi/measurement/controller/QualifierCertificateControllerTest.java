package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.QualifierCertificateService;
import com.mengyunzhi.measurement.Service.QualifierService;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/7/22.
 */
public class QualifierCertificateControllerTest extends ControllerTest{
    private static Logger logger = Logger.getLogger(QualifierControllerTest.class.getName());
    @Autowired
    private QualifierCertificateRepository qualifierCertificateRepository;
    @Autowired
    private QualifierCertificateService qualifierCertificateService;
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
    public void addQualifierCertificateOfCurrentUser() throws Exception {
        logger.info("---- addQualifierCertificateOfCurrentUser方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名及随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用用户：user 进行模拟登录时，");
        this.loginByUser(user);
        user.getDepartment().setCreateTime(null);
        user.getDepartment().setUpdateTime(null);

        logger.info("新建一个人员实体, 将人员添加到当前登录用户对应的部门上，并保存.");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        String qualifierName = "qualifier";
        qualifier.setName(qualifierName);
        qualifierRepository.save(qualifier);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("qualifier", qualifier);

        logger.info("人员资质实体中，根本就没有NAME字段! ");
//        jsonObject.put("name", "qualifierCertificate");

        logger.info("----------------------------------------------------------------------------------");
        String content = jsonObject.toString();
        MvcResult mvcResult = this.mockMvc.perform(post("/QualifierCertificate/addQualifierCertificateOfCurrentUser")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andDo(document("QualifierCertificate_addQualifierCertificateOfCurrentUser", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201))
                .andReturn();

        String content1 = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject1 = JSONObject.fromObject(content1);

        logger.info("获取返回的实体ID，并查询，断言其人员名称与前面设置的相同");
        int id = (int) jsonObject1.get("id");
        assertThat(qualifierCertificateRepository.findOne(new Long(id)).getQualifier().getName()).isEqualTo(qualifierName);

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifier);
    }

    @Test
    public void updateQualifierCertificateOfCurrentUser() throws Exception {
        logger.info("---- updateQualifierCertificateOfCurrentUser方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名及随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用用户：user 进行模拟登录时，");
        this.loginByUser(user);
        user.getDepartment().setCreateTime(null);
        user.getDepartment().setUpdateTime(null);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体");
        QualifierCertificate qualifierCertificate = new QualifierCertificate();
        qualifierCertificate.setQualifier(qualifier);
        qualifierCertificate.setCreateTime(5L);
        qualifierCertificateRepository.save(qualifierCertificate);

        logger.info("修改实体，并信状态码返回204");
        qualifierCertificate.setCreateTime(1L);
        logger.info("----------------------------------------------------------------------------------");
        this.mockMvc.perform(put("/QualifierCertificate/updateQualifierCertificateOfCurrentUser/" + qualifierCertificate.getId().toString())
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.fromObject(qualifierCertificate).toString()))
                .andDo(document("QualifierCertificate_updateQualifierCertificateOfCurrentUser", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204))
                .andReturn();

        logger.info("断言修改成功");
        assertThat(qualifierCertificateRepository.findOne(qualifierCertificate.getId()).getCreateTime()).isEqualTo(1L);
        assertThat(qualifierCertificateRepository.findOne(qualifierCertificate.getId()).getQualifier().getDepartment()).isEqualTo(user.getDepartment());

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifier);
        qualifierCertificateRepository.delete(qualifierCertificate);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("---- delete方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名及随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用用户：user 进行模拟登录时，");
        this.loginByUser(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        QualifierCertificate qualifierCertificate = new QualifierCertificate();
        qualifierCertificate.setQualifier(qualifier);
        qualifierCertificate.setCreateTime(5L);
        qualifierCertificateRepository.save(qualifierCertificate);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/QualifierCertificate/delete/" + qualifierCertificate.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(qualifierCertificate).toString()))
                .andDo(document("QualifierCertificate_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(qualifierRepository.findOne(qualifierCertificate.getId())).isNull();

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifier);
    }

    @Test
    public void getAllByDepartmentId() throws Exception {
        logger.info("---- getAllByDepartmentId方法测试 ----");
        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("Department");
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名及随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用用户：user 进行模拟登录时，");
        this.loginByUser(user);

        logger.info("新建一个人员实体");
        Qualifier qualifier = new Qualifier();
        qualifier.setDepartment(user.getDepartment());
        qualifier.setName("qualifier");
        qualifierRepository.save(qualifier);

        logger.info("新建一个人员资质实体A");
        QualifierCertificate qualifierCertificateA = new QualifierCertificate();
        qualifierCertificateA.setQualifier(qualifier);
        qualifierCertificateA.setCreateTime(1L);
        qualifierCertificateRepository.save(qualifierCertificateA);

        logger.info("新建一个人员资质实体B");
        QualifierCertificate qualifierCertificateB = new QualifierCertificate();
        qualifierCertificateB.setQualifier(qualifier);
        qualifierCertificateB.setCreateTime(2L);
        qualifierCertificateRepository.save(qualifierCertificateB);

        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get("/QualifierCertificate/getAllByCurrentUser/" + qualifier.getId().toString() + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("QualifierCertificate_getAllByCurrentUser",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(jsonObject.get("totalPages"));

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifier);
        qualifierCertificateRepository.delete(qualifierCertificateA);
        qualifierCertificateRepository.delete(qualifierCertificateB);
    }

}