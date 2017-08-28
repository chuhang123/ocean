package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-6-3.
 * 申请M层  测试
 */
public class ApplyServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(ApplyServiceTest.class.getName());
    @Autowired @Qualifier("ApplyService")
    private ApplyService applyService;
    private Apply apply;
    @Autowired
    ApplyRepository applyRepository;        // 申请
    @Autowired ApplyServiceTestData applyServiceTestData;
    @Autowired UserRepository userRepository;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired UserService userService;
    @Before
    public void before() {
        logger.info("创建一个申请");
        apply = new Apply();
        logger.info("保存");
        userService.loginWithOneUser();
        applyService.save(apply);
    }
    @Test
    public void save() throws Exception {
        logger.info("--------save测试----------");
        logger.info("断言");
        assertThat(apply.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("-----------getAll测试-------------");
        logger.info("取出所有的数据");
        List<Apply> applies = applyService.getAll();
        logger.info("段言");
        assertThat(applies.size()).isNotEqualTo(0);
    }

    @Test
    public void get() throws Exception {
        logger.info("--------------get测试------------------");
        logger.info("取出数据");
        Apply apply1 = applyService.get(apply.getId());
        logger.info("断言");
        assertThat(apply1.getId()).isEqualTo(apply.getId());
    }

    @Test
    public void delete() throws Exception {
        logger.info("-----------------delete测试--------------------");
        logger.info("删除数据");
        applyService.delete(apply.getId());
        logger.info("断言");
        assertThat(applyService.get(apply.getId())).isNull();
    }

    @Test
    public void updateToDoneByApplyId() {
        logger.info("新建实体，实例化，设置为办结，并断言");
        Apply apply = applyService.getOneApply();
        applyRepository.save(apply);
        applyService.updateToDoneByApplyId(apply.getId());
        Apply apply1 = applyRepository.findOne(apply.getId());
        assertThat(apply1.getDone()).isTrue();
        applyRepository.delete(apply);
    }

    @Test
    public void saveWork() {
        Work work = WorkService.getOneWork();
        applyServiceTestData.saveWork(work);
        applyService.save(work);
        assertThat(work.getId()).isNotNull();
        assertThat(work.getApply().getId()).isNotNull();
    }

}