package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.repository.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by panjie on 17/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/asciidoc/snippets")
@Transactional
public abstract class ControllerTest {
    private static Logger logger = Logger.getLogger(ControllerTest.class.getName());

    // 用于进行认证的xAuthToken
    protected String xAuthToken;
    @Autowired
    protected UserService userService;
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired protected DepartmentRepository departmentRepository; // 部门

    @Before
    public void before() throws Exception {
        logger.info("------ 添加一个管理员用户，供模拟进行提交使用 -------");

        logger.info("创建一个用户，并赋予管理员角色");
        User user = new User();
        String username = "usersdfdfwef23dfvdfwewef";
        String password = "passwordwfwefwsdfvdvsdfwefewfew";
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(0);
        Set<Role> roles = new HashSet<Role>();

//        roles.add(role);
        user.setRoles(roles);


        logger.info("为管理员用户添加到管理部门");
        Department department = departmentRepository.findTopOneByName("河北省管理部门");
        user.setDepartment(department);
        userRepository.save(user);

        logger.info("使用新创建的用户信息发起登录请求，并获取到用于后期认证的xAuthToken");
        logger.info("创建基于base64的认证信息");
        this.loginByUser(user);
    }

    public void loginByUser(User user) throws Exception {
        logger.info("清除测试登录用户，防止在获取当前登录用户时，返回在其它单元测试中，增加的测试登录信息");
        userService.clearCurrentTestLoginUser();

        logger.info("使用base64信息进行登录认证");
        byte[] encodedBytes = Base64.encodeBase64((user.getUsername() + ":" + user.getPassword()).getBytes());

        MvcResult mvcResult = this.mockMvc.perform(get("/User/login")
                .header("Authorization", "Basic " + new String(encodedBytes)))
                .andReturn();
        logger.info("登录成功，获取header中返回的x-auth-token");
        xAuthToken = mvcResult.getResponse().getHeader("x-auth-token");
    }
}
