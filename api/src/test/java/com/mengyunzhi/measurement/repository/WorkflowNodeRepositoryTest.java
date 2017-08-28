package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-22.
 */
public class WorkflowNodeRepositoryTest extends RepositoryTest{
    @Autowired
    private WorkflowNodeRepository workflowNodeRepository;
    static private Logger logger = Logger.getLogger(WorkflowNodeRepositoryTest.class.getName());

    @Test
    public void save() {
        logger.info("创建一个工作流节点");
        WorkflowNode workflowNode = new WorkflowNode();
        logger.info("保存");
        workflowNodeRepository.save(workflowNode);
        logger.info("断言保存成功");
        assertThat(workflowNode.getId()).isNotNull();
    }
}