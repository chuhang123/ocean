package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Purpose;
import com.mengyunzhi.measurement.repository.PurposeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by zhangjiahao on 2017/6/13.
 */
public class PurposeServiceTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(PurposeServiceTest.class.getName());

    @Autowired
    private PurposeRepository purposeRepository;
    @Autowired
    private PurposeService purposeService;
    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll测试 ----");
        logger.info("存一条数据");
        Purpose purpose = new Purpose();
        purposeRepository.save(purpose);
        logger.info("取出所有数据");
        List<Purpose> purposes = purposeService.getAll();
        logger.info("断言");
        assertThat(purposes.size()).isNotEqualTo(0);
    }

}