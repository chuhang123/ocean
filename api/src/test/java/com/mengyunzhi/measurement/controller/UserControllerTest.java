package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/5/17.
 * 用户管理
 */
public class UserControllerTest extends ControllerTest {
    static Logger logger = Logger.getLogger(UserControllerTest.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void loginAndLoginOut() throws Exception {
        logger.info("------添加一个管理员用户，供模拟进行提交使用-------");
        logger.info("创建一个管理员角色");
        Role role = new Role();
        role.setName("AdminTest");
        roleRepository.save(role);

        User user = new User();
        String username = "testuser1123213213user";
        String password = "testpassword112321323password112321323";
        user.setStatus(0);
        user.setUsername(username);
        user.setPassword(password);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        logger.info("使用新创建的用户信息发起登录请求，并获取到用于后期认证的xAuthToken");
        logger.info("创建基于base64的认证信息");
        byte[] encodedBytes = Base64.encodeBase64((username + ":" + password).getBytes());

        logger.info("使用base64信息进行跨域登录认证");
        MvcResult mvcResult = this.mockMvc.perform(get("/User/login")
                .header("Authorization", "Basic " + new String(encodedBytes))
                .header("Origin", "http://www.someurl.com"))    // todo:修改为生产环境下的URL地址
                .andDo(document("User_login", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        logger.info("获取header中的Access-Control-Expose-Headers信息, 并断言其包含x-auth-token字段");
        String accessControlExposeHeaders = mvcResult.getResponse().getHeader("Access-Control-Expose-Headers");
        assertThat(accessControlExposeHeaders).isIn("x-auth-token");

        logger.info("使用错误的信息进行认证");
        this.mockMvc.perform(get("/User/login")
                .header("Authorization", "Basic " + new String(encodedBytes + "1")))
                .andExpect(status().is(401));

        String xAuthToken = mvcResult.getResponse().getHeader("x-auth-token");

        logger.info("当未传入x-auth-token时返回401提示用户登录");
        this.mockMvc.perform(get("/User/test")
                .header("x-auth-token", ""))
                .andExpect(status().is(401));

        logger.info("注销用户");
        this.mockMvc.perform(get("/User/logout")
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_logout", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());


        logger.info("继续使用原来的authToken进行数据请求.得到401 -- 要求用户认证");
        this.mockMvc.perform(get("/User/test")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(401));

        return;
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/User/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("User_getAll", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllPage() throws Exception {
        int i;
        logger.info("添加10个user实例");
        for (i = 0; i < 10; i++) {
            User user = new User();
            user.setName("zhangjiahao");
            user.setPassword("38579596");
            userRepository.save(user);
        }

        logger.info("发送模拟数据并获取返回值");
        String content = this.mockMvc.perform(get("/User/getAllPage" + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_PageAll", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info("断言返回结果");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(jsonObject.get("totalPages"));
        assertThat(jsonObject.get("last")).isEqualTo(false);
    }


    @Test
    public void getTest() throws Exception {
        //存用户
        User user = new User();
        userRepository.save(user);

        //取用户
        MvcResult result = this.mockMvc.perform(get("/User/get/" + user.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("User_get", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        logger.info("将返回的内容转化为json对象, 并断言刚保存的区域与出去来的区域id相等");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isEqualTo(user.getId());
    }

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "姓名");
        jsonObject.put("pinyin", "拼音");

        this.mockMvc.perform(post("/User/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("User_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void update() throws Exception {
        logger.info("----- 更新实体 -----");
        logger.info("新建一个实体");
        User user = new User();
        userRepository.save(user);
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("name", "hello");

        logger.info("修改实体, 并断言状态码返回204");
        this.mockMvc.perform(put("/User/update/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(userJsonObject.toString()))
                .andDo(document("User_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));
    }

    @Test
    public void deleteTest() throws Exception {
        //存用户
        User user = new User();
        userRepository.save(user);

        //删用户
        this.mockMvc.perform(delete("/User/delete/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        assertThat(userRepository.findOne(user.getId())).isNull();
    }

    @Test
    public void getCutUserWebAppMenus() throws Exception {
        this.mockMvc.perform(get("/User/getCurrentUserWebAppMenus/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
//                .andDo(print())
                .andDo(document("User_getCurrentUserWebAppMenus", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void checkUsernameIsExist() throws Exception {
        logger.info("断言不存的用户名查不到");
        String username = "sdfdsfefoxcvwefhewfjsdfjsdfjljwefjwefwefsff";
        MvcResult mvcResult = this.mockMvc.perform(get("/User/checkUsernameIsExist/" + username)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
//                .andDo(print())
                .andDo(document("User_checkUsernameIsExist", preprocessResponse(prettyPrint())))
                .andReturn();
        String status = mvcResult.getResponse().getContentAsString();
        assertThat(status).isEqualTo("false");

        logger.info("断言存在的用户名能查到");
        String username1 = "admin";
        User user = userRepository.findOneByUsername(username1);
        if (null != user) {
            MvcResult mvcResult1 = this.mockMvc.perform(get("/User/checkUsernameIsExist/" + username1)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .header("x-auth-token", xAuthToken))
//                .andDo(print())
                    .andReturn();
            assertThat(mvcResult1.getResponse().getContentAsString()).isEqualTo("true");
        }
    }

    @Test
    public void setUserStatusToNormalById() throws Exception {
        logger.info("新建一个用户,并断言状态为-1未审核");
        User user = new User();
        userRepository.save(user);
        assertThat(user.getStatus()).isEqualTo(-1);

        this.mockMvc.perform(get("/User/setUserStatusToNormalById/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_setUserStatusToNormalById", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        logger.info("断言数据修改成功");
        assertThat(userRepository.findOne(user.getId()).getStatus()).isEqualTo(0);
    }

    /**
     * 注册
     *
     * @throws Exception
     */
    @Test
    public void register() throws Exception {
        logger.info("实例化一个用户，一个部门");
        JSONObject jsonObjectUser = new JSONObject();
        jsonObjectUser.put("username", "sdfwefefsxvsfojojlsldfwef");
        jsonObjectUser.put("passWord", "dsfsdfsd");
        JSONObject jsonObjectDepartment = new JSONObject();
        jsonObjectDepartment.put("name", "我是某个公司的名称");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", jsonObjectUser);
        jsonObject.put("department", jsonObjectDepartment);
        String content = jsonObject.toString();
        this.mockMvc.perform(post("/User/register")
                .header("x-auth-token", xAuthToken)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
                .andDo(document("User_register", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201));
    }

    @Test
    public void updatePasswordAndNameOfCurrentUser() throws Exception {
        logger.info("创建一个用户对象");
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(20));
        user.setName("test");
        user.setPassword("123456");
        user.setStatus(0);
        userRepository.save(user);

        logger.info("使用这个用户登录");
        this.loginByUser(user);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "admin");
        jsonObject.put("password", "123456");
        jsonObject.put("rePassword", "admin");

        logger.info("修改实体, 并断言状态码返回204");
        this.mockMvc.perform(put("/User/updatePasswordAndNameOfCurrentUser/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andDo(document("User_updatePasswordAndNameOfCurrentUser", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言数据修改成功");
        assertThat(userRepository.findOne(user.getId()).getName()).isEqualTo("admin");
        assertThat(userRepository.findOne(user.getId()).getPassword()).isEqualTo("admin");
    }

    @Test
    public void resetPasswordById() throws Exception {
        logger.info("创建一个用户对象");
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(20));
        user.setName("test");
        user.setPassword("123456");
        user.setStatus(0);
        userRepository.save(user);

        logger.info("修改实体, 并断言状态码返回204");
        this.mockMvc.perform(put("/User/resetPasswordById/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_resetPasswordById", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言数据修改成功");
        assertThat(userRepository.findOne(user.getId()).getPassword()).isEqualTo("111111");
      
        logger.info("删除实体");
        userRepository.delete(user);
    }

    @Test
    public void freezeById() throws Exception {
        logger.info("创建一个用户对象");
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(20));
        user.setName("test");
        user.setPassword("123456");
        user.setStatus(0);
        userRepository.save(user);

        logger.info("修改实体, 并断言状态码返回204");
        this.mockMvc.perform(put("/User/freezeById/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_freezeById", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言数据修改成功");
        assertThat(userRepository.findOne(user.getId()).getStatus()).isEqualTo(1);

        logger.info("删除实体");
        userRepository.delete(user);
    }

    @Test
    public void pageAllBySpecification() throws Exception {
        String url = "/User/pageAllBySpecification?districtId=1&departmentTypeId=2&status=0&name=hello&page=0&size=2";
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_pageAllBySpecification", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void checkPasswordIsRight() throws Exception {
        logger.info("以当前用户密码作为参数，验证密码是否正确");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", "passwordwfwefwsdfvdvsdfwefewfew");

        MvcResult mvcResult = this.mockMvc.perform(post("/User/checkPasswordIsRight")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andDo(document("User_checkPasswordIsRight", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        logger.info("断言密码正确");
        String status = mvcResult.getResponse().getContentAsString();
        assertThat(status).isEqualTo("true");
    }
}