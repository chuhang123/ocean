package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-5-17.
 * 工作流类型
 */
public class WorkflowTypeRepositoryTest extends RepositoryTest{
    @Autowired
    private WorkflowTypeRepository workflowTypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void save() {
        //新建一个工作流实体
        WorkflowType workflowType = new WorkflowType();
        //新建一个用户实体
        User user = new User();
        userRepository.save(user);

        //保存并断言
        workflowTypeRepository.save(workflowType);
        assertThat(workflowType.getId()).isNotNull();
        userRepository.delete(user);
        workflowTypeRepository.delete(workflowType);
    }
}