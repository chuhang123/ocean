package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyService;
import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-6-3.
 * 申请C层 测试
 */
public class ApplyControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(ApplyControllerTest.class.getName());
    @Autowired          //用户实体仓库
    private UserRepository userRepository;
    @Autowired          //申请类型实体仓库
    private ApplyTypeRepository applyTypeRepository;
    @Autowired          //工作流类型实体仓库
    private WorkflowTypeRepository workflowTypeRepository;
    @Autowired          //申请实体仓库
    private ApplyRepository applyRepository;
    @Autowired
    private ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("ApplyService")
    private ApplyService applyService;

    @Test
    public void save() throws Exception {
        logger.info("---------------测试save方法----------------");

        User user = userService.loginWithOneUser();

        logger.info("创建一个申请类型");
        ApplyType applyType = applyTypeService.getOneApplyType();
        logger.info("保存");
        applyTypeRepository.save(applyType);

        logger.info("创建一个工作流类型");
        WorkflowType workflowType = new WorkflowType();
        logger.info("保存工作流类型");
        workflowTypeRepository.save(workflowType);


        logger.info("模拟发送用户");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "名称");
        jsonObject.put("contactNumber", "电话");
        jsonObject.put("postalcode", "邮政编码");
        jsonObject.put("isDone", true);
        jsonObject.put("contactName", "联系人姓名");

        logger.info("user与申请关联");
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("id", user.getId());
        jsonObject.put("createUser", userJsonObject);

        logger.info("与工作流类型-申请类型相关联");
        JSONObject workflowTypeApplyTypeJsonObject = new JSONObject();
        JSONObject applyTypeJsonObject = new JSONObject();
        applyTypeJsonObject.put("id", applyType.getId());
        workflowTypeApplyTypeJsonObject.put("applyType", applyTypeJsonObject);

        logger.info("与工作流类型关联");
        JSONObject workflowTypeJsonObject = new JSONObject();
        workflowTypeJsonObject.put("id", workflowType.getId());
        workflowTypeApplyTypeJsonObject.put("workflowType", workflowTypeJsonObject);

        jsonObject.put("workflowTypeApplyType", workflowTypeApplyTypeJsonObject);

        logger.info("发送数据请求");
        MvcResult result = this.mockMvc.perform(post("/Apply/save")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("Apply_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObjec对象,并断言其存在Id属性， 且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言申请实体id存在且不为空");
        assertThat(jsonObject1.optLong("id")).isNotNull();

        logger.info("断言中间表保存成功,申请类型实体id,工作流类型实体id存在且与保存到中间表之前的两个实体的ID相等");
    }

    @Test
    public void getTest() throws Exception {
        logger.info("--------------测试get方法------------------");
        logger.info("创建一个申请");
        Apply apply = new Apply();
        logger.info("保存");
        applyRepository.save(apply);
        logger.info("发送请求");
        MvcResult result = this.mockMvc.perform(get("/Apply/get/" + apply.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Apply_get", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonObjec对象,并断言其存在Id属性， 且与保存apply的相等");
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isEqualTo(apply.getId());
    }

    @Test
    public void getAll() throws Exception {
        logger.info("-----------测试getAll方法------------");
        logger.info("创建一个申请");
        Apply apply = new Apply();
        logger.info("保存");
        applyRepository.save(apply);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/Apply/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Apply_get", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为jsonArray对象, 并断言数组长度不为0");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isNotEqualTo(0);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("-----------测试delete方法------------");
        logger.info("创建一个申请");
        Apply apply = new Apply();
        logger.info("保存");
        applyRepository.save(apply);
        logger.info("发送模拟数据");
        this.mockMvc.perform(delete("/Apply/delete/" + apply.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("Apply_delete", preprocessResponse(prettyPrint())));
        logger.info("断言刚才保存的apply已经删除");
        assertThat(applyRepository.findOne(apply.getId())).isNull();
    }

    @Test
    public void update() throws Exception {
        Apply apply = applyService.getOneApply();
        String name = CommonService.getRandomStringByLength(10);
        apply.setUpdateTime(null);
        apply.setApplyTime(null);
        apply.setContactName(name);
        String content = JSONObject.fromObject(apply).toString();
        this.mockMvc.perform(put("/Apply/" + apply.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(content))
                .andExpect(status().is(204))
                .andDo(document("Apply_update", preprocessResponse(prettyPrint())));
        Apply apply1 = applyRepository.findOne(apply.getId());
        assertThat(apply1.getContactName()).isEqualTo(name);
    }

}