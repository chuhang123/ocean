package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.QualifierService;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhangjiahao on 2017/7/18.
 */
public class QualifierControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(QualifierControllerTest.class.getName());
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
    @Autowired
    private DistrictRepository districtRepository;
    @Test
    public void getAllByDepartmentId() throws Exception {
        logger.info("---- getAllByDepartmentId方法测试 ----");
        logger.info("添加一个部门实体");
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

        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get("/Qualifier/getAllByDepartmentId/" + department.getId().toString() + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Qualifier_getAllPages", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(jsonObject.get("totalPages"));

        logger.info("删除用于测试的实体");
        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        departmentRepository.delete(department);
    }

    @Test
    @Transactional
    public void addQualifierByCurrentLoginUserDepartment() throws Exception {
        logger.info("---- 保存人员方法测试 ----");

        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个user实体");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);
        user.getDepartment().setCreateTime(null);
        user.getDepartment().setUpdateTime(null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "qualifierA");
        jsonObject.put("department", user.getDepartment());

        logger.info("----------------------------------------------------------------------------------");
        MvcResult mvcResult = this.mockMvc.perform(post("/Qualifier/addQualifierByCurrentLoginUserDepartment")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString()))
                .andDo(document("Qualifier_addQualifierByCurrentLoginUserDepartment", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject1 = JSONObject.fromObject(content);
        System.out.println(jsonObject1);
        assertThat(jsonObject1.get("name")).isEqualTo("qualifierA");

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
    }

    @Test
    public void updateQualifierOfCurrentLoginUserDepartmentTest() throws Exception {
        logger.info("---- 更新人员方法测试 ----");

        logger.info("新建一个部门实体");
        Department department = new Department();
        department.setName("department");
        departmentRepository.save(department);

        logger.info("新建一个user实体");
        User user = new User();
        userService.setCurrentLoginUser(user);
        user.setDepartment(department);
        userRepository.save(user);
        user.getDepartment().setCreateTime(null);
        user.getDepartment().setUpdateTime(null);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setName("qualifierA");
        qualifierA.setDepartment(user.getDepartment());
        qualifierRepository.save(qualifierA);

        logger.info("修改实体，并断言状态码返回204");
        qualifierA.setName("update");
        this.mockMvc.perform(put("/Qualifier/updateQualifierOfCurrentLoginUserDepartment/" + qualifierA.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(qualifierA).toString()))
                .andDo(document("Qualifier_updateQualifierOfCurrentLoginUserDepartment", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言修改成功");
        assertThat(qualifierRepository.findOne(qualifierA.getId()).getName()).isEqualTo("update");
        assertThat(qualifierRepository.findOne(qualifierA.getId()).getDepartment()).isEqualTo(user.getDepartment());

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
        qualifierRepository.delete(qualifierA);
    }


    @Test
    public void deleteTest() throws Exception {
        logger.info("---- 删除人员方法测试 ----");

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
        qualifierA.setDepartment(user.getDepartment());
        qualifierRepository.save(qualifierA);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/Qualifier/delete/" + qualifierA.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(qualifierA).toString()))
                .andDo(document("Qualifier_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(qualifierRepository.findOne(qualifierA.getId())).isNull();

        logger.info("删除用于测试的实体");
        departmentRepository.delete(department);
        userRepository.delete(user);
    }

    @Test
    public void getAllByCurrentLoginUserDepartment() throws Exception {
        logger.info("新建一个部门实体");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用用户:user进行模拟登录时，");
        this.loginByUser(user);

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

        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get("/Qualifier/getAllByCurrentLoginUserDepartment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Qualifier_getAllByCurrentLoginUserDepartment", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(2);
    }

    @Test
    public void getAllByCurrentLoginUser() throws Exception {
        logger.info("新建一个部门实体");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
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


        logger.info("使用用户:user进行模拟登录时，");
        this.loginByUser(user);
        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get("/Qualifier/getAllByCurrentLoginUser/" + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Qualifier_getAllByCurrentLoginUser", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(jsonObject.get("totalPages"));

        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() throws Exception {
        logger.info("新建区域实体");
        District districtA = new District();
        District districtB = new District();
        districtRepository.save(districtA);
        districtB.setParentDistrict(districtA);
        districtRepository.save(districtB);

        logger.info("新建部门实体");
        Department departmentA = new Department();
        departmentA.setName("departmentA");
        departmentA.setDistrict(districtA);
        departmentRepository.save(departmentA);

        Department departmentB = new Department();
        departmentB.setName("departmentB");
        departmentB.setDistrict(districtB);
        departmentRepository.save(departmentB);


        logger.info("获取一个带有随机用户名和随机密码的用户");
        User user = UserService.getOneUser();
        user.setDepartment(departmentA);
        userRepository.save(user);

        logger.info("新建一个人员实体A");
        Qualifier qualifierA = new Qualifier();
        qualifierA.setDepartment(departmentA);
        qualifierA.setName("qualifierA");
        qualifierRepository.save(qualifierA);

        logger.info("新建一个人员实体B");
        Qualifier qualifierB = new Qualifier();
        qualifierB.setName("qualifierB");
        qualifierB.setDepartment(departmentB);
        qualifierRepository.save(qualifierB);


        logger.info("使用用户user进行模拟登录时，");
        this.loginByUser(user);

        String url = "/Qualifier/pageAllOfCurrentUserBySpecification?name=qualifierB&departmentName=departmentB&districtId" +
                districtA.getId().toString() + "&page=1&size=2";

        logger.info("发送模拟数据");
        String content = this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("Qualifier_pageAllOfCurrentUserBySpecification", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        qualifierRepository.delete(qualifierA);
        qualifierRepository.delete(qualifierB);
        userRepository.delete(user);
        departmentRepository.delete(departmentA);
        departmentRepository.delete(departmentB);

        // 一个神奇的问题，后续解决
//        logger.info("新建区域实体");
//        District districtA = new District();
//        District districtB = new District();
//        //District districtC = new District();
//        districtRepository.save(districtA);
//        //districtRepository.save(districtC);
//        logger.info("districtB设置为districtC的子区域");
//        //districtB.setParentDistrict(districtC);
//        districtRepository.save(districtB);
//
//
//        logger.info("新建部门实体");
//        Department departmentA = new Department();
//        departmentA.setName("departmentA");
//        departmentA.setDistrict(districtA);
//        departmentRepository.save(departmentA);
//
//        Department departmentB = new Department();
//        departmentB.setName("departmentB");
//        departmentB.setDistrict(districtB);
//        departmentRepository.save(departmentB);
//
//        Department departmentC = new Department();
//        departmentB.setName("departmentC");
//        //departmentB.setDistrict(districtC);
//        departmentRepository.save(departmentC);
//
//        logger.info("获取一个带有随机用户名和随机密码的用户");
//        User user = UserService.getOneUser();
//        user.setDepartment(departmentB);
//        userRepository.save(user);
//
//        logger.info("使用用户user进行模拟登录时，");
//        this.loginByUser(user);
//
//        logger.info("新建一个人员实体A");
//        Qualifier qualifierA = new Qualifier();
//        qualifierA.setDepartment(departmentA);
//        qualifierA.setName("qualifierA");
//        qualifierRepository.save(qualifierA);
//
//        logger.info("新建一个人员实体B");
//        Qualifier qualifierB = new Qualifier();
//        qualifierB.setName("qualifierB");
//        qualifierB.setDepartment(departmentB);
//        qualifierRepository.save(qualifierB);
//
//        Qualifier qualifierC = new Qualifier();
//        qualifierB.setName("qualifierC");
//        qualifierB.setDepartment(departmentC);
//        qualifierRepository.save(qualifierC);
//
//
//
//        String url = "/Qualifier/pageAllOfCurrentUserBySpecification?" +
//                "name=qualifierB" + "&page=1&size=2";
//
//        logger.info("发送模拟数据");
//        String content = this.mockMvc.perform(get(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .header("x-auth-token", xAuthToken))
//                .andDo(document("Qualifier_pageAllOfCurrentUserBySpecification", preprocessResponse(prettyPrint())))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        logger.info("断言返回结果");
//        JSONObject jsonObject = JSONObject.fromObject(content);
//        assertThat(jsonObject.get("totalElements")).isEqualTo(1);
//
//        qualifierRepository.delete(qualifierA);
//        qualifierRepository.delete(qualifierB);
//        //qualifierRepository.delete(qualifierC);
//        districtRepository.delete(districtA);
//        districtRepository.delete(districtB);
//        //districtRepository.delete(districtC);
//        userRepository.delete(user);
//        departmentRepository.delete(departmentA);
//        departmentRepository.delete(departmentB);
        //departmentRepository.delete(departmentC);
    }
}