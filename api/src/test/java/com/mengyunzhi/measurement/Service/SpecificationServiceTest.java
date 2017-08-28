package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Specification;
import com.mengyunzhi.measurement.repository.SpecificationRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/6/27.
 * 规格型号 M层测试
 */
public class SpecificationServiceTest extends ServiceTest {
    private Logger logger = Logger.getLogger(SpecificationServiceTest.class.getName());
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private SpecificationRepository specificationRepository;

    @Test
    public void save() throws Exception {
        logger.info("---- save方法测试 ----");
        logger.info("新建一个实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        Specification specification1 = specificationRepository.findOne(specification.getId());
        logger.info("断言保存成功");
        assertThat(specification1).isNotNull();
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- delete方法测试 ----");
        logger.info("新建一个实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        logger.info("删除该实体");
        specificationRepository.delete(specification.getId());

        logger.info("查询实体并断言该实体删除成功");
        Specification specification1 = specificationRepository.findOne(specification.getId());
        assertThat(specification1).isNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("---- update方法测试 ----");
        logger.info("新建一个实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        logger.info("修改该实体");
        specification.setCost(4L);
        specificationService.update(specification.getId(), specification);

        logger.info("断言修改成功");
        assertThat(specification.getCost()).isEqualTo(4L);
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("新建一个实体并保存");
        Specification specification = new Specification();
        specification.setValue("value");
        specificationRepository.save(specification);

        List<Specification> list = new ArrayList<Specification>();
        logger.info("取出所有数据");
        list = specificationService.getAll();
        logger.info("断言取出的对象大小不为空");
        assertThat(list.size()).isNotEqualTo(0);
    }
}