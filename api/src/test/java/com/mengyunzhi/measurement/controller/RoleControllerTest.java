package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by 安强 on 2017/6/2.
 * 角色  控制器测试
 */
public class RoleControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(RoleControllerTest.class.getName());
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebAppMenuRepository webAppMenuRepository;
    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/Role/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Role_getAll", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getTest() throws Exception {
        logger.info("---- get测试 ----");
        //存角色
        Role role = new Role();
        roleRepository.save(role);

        //查角色
        MvcResult result = this.mockMvc.perform(get("/Role/get/" + role.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Role_get", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回的内容");

        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObject，并断言其存在id属性，且值就是我们刚刚增加的实体ID");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isEqualTo(role.getId());
    }

    @Test
    public void saveTest() throws Exception {
        logger.info("-------save测试---------");
        logger.info("创建两个Menu");
        Menu menu = new Menu();
        Menu menu1 = new Menu();

        logger.info("保存两个Menu");
        menuRepository.save(menu);
        menuRepository.save(menu1);

        logger.info("模拟角色数据");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "用户名");
        jsonObject.put("pinyin", "用户名拼音");

        logger.info("将菜单拼接成数组放入模拟数据中");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id", menu.getId());
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", menu1.getId());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonObject.put("menus", jsonArray);

        logger.info("发送数据");
        MvcResult result =  this.mockMvc.perform(post("/Role/save")
                                        .contentType("application/json")
                                        .content(jsonObject.toString())
                                        .header("x-auth-token", xAuthToken))
                                        .andExpect(status().is(201))
                                        .andDo(document("Role_save", preprocessResponse(prettyPrint())))
                                        .andReturn();
        logger.info("获取返回的数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObjec对象,并断言其存在Id属性， 且不为空");
        JSONObject jsonObject3 = JSONObject.fromObject(content);
        assertThat(jsonObject3.optLong("id")).isNotNull();

    }

    @Test
    public void update() throws Exception {
//        logger.info("创建一个角色, 并保存");
//        Role role = new Role();
//        roleRepository.save(role);
//
//        logger.info("创建两个前台菜单");
//        WebAppMenu webAppMenu1 = new WebAppMenu();
//        WebAppMenu webAppMenu2 = new WebAppMenu();
//        webAppMenuRepository.save(webAppMenu1);
//        webAppMenuRepository.save(webAppMenu2);
//        role.addWebAppMenu(webAppMenu1);
//        role.addWebAppMenu(webAppMenu2);
//
//        JSONObject jsonObject = JSONObject.fromObject(role);
//
//        logger.info("发送数据");
//        this.mockMvc.perform(put("/Role/" + role.getId().toString())
//                .contentType("application/json")
//                .content(jsonObject.toString())
//                .header("x-auth-token", xAuthToken))
//                .andExpect(status().is(204))
//                .andDo(document("Role_update", preprocessResponse(prettyPrint())));
//
//        Role role1 = roleRepository.findOne(role.getId());
//        assertThat(role1.getWebAppMenus().size()).isEqualTo(2);
//
//        logger.info("将前台菜单数据置空");
//        role1.setWebAppMenus(new HashSet<>());
//        this.mockMvc.perform(put("/Role/" + role1.getId().toString())
//                .contentType("application/json")
//                .content(JSONObject.fromObject(role1).toString())
//                .header("x-auth-token", xAuthToken))
//                .andExpect(status().is(204));
//
//        Role role2 = roleRepository.findOne(role.getId());
//        assertThat(role2.getWebAppMenus().size()).isEqualTo(0);
//        return;
    }
}