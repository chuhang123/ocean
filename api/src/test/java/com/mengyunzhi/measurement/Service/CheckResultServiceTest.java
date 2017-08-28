package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CheckResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-7-26.
 * 检定结果ServiceTest
 */
public class CheckResultServiceTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(CheckResultServiceTest.class.getName());
    @Autowired protected CheckResultService checkResultService;
    @Test
    public void getCheckResultTree() throws Exception {
        List<CheckResult> checkResults = checkResultService.getCheckResultTree();
        return;
    }

}