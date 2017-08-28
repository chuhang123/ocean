package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/7/6.
 * 工作流结点初始化数据
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkFlowNodeDataInitTest {
    @Autowired WorkflowNodeRepository workflowNodeRepository;       // 工作流结点
    @Test
    public void test() {
        List<WorkflowNode> workflowNodes = (List<WorkflowNode>) workflowNodeRepository.findAll();
        assertThat(workflowNodes.size()).isGreaterThanOrEqualTo(6);
    }
}
