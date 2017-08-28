package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.AccuracyDisplayName;
import com.mengyunzhi.measurement.repository.AccuracyDisplayNameRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by zhangjiahao on 2017/6/22.
 * 精度显示名称 M层测试
 */
public class AccuracyDisplayNameServiceImplTest extends ServiceTest{
    private Logger logger = Logger.getLogger(AccuracyDisplayNameServiceImplTest.class.getName());
    @Autowired
    private AccuracyDisplayNameRepository accuracyDisplayNameRepository;
    @Autowired
    private AccuracyDisplayNameService accuracyDisplayNameService;

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll ----");
        logger.info("新建一个实体");
        AccuracyDisplayName accuracyDisplayName = new AccuracyDisplayName();
        logger.info("保存该实体");
        accuracyDisplayNameRepository.save(accuracyDisplayName);
        logger.info("断言获取的数组与AccuracyDisplayNameRepository获取的数组大小相等");
        List<AccuracyDisplayName> list = (List<AccuracyDisplayName>) accuracyDisplayNameRepository.findAll();
        assertThat(accuracyDisplayNameService.getAll().size()).isEqualTo(list.size());
    }

}