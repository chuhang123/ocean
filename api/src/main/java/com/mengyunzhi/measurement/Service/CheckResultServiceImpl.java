package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CheckResult;
import com.mengyunzhi.measurement.repository.CheckResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by liming on 17-7-26.
 * 检定结果Service实现
 */
@Service
public class CheckResultServiceImpl implements CheckResultService {
    static private Logger logger = Logger.getLogger(CheckResultServiceImpl.class.getName());
    @Autowired
    protected CheckResultRepository checkResultRepository;
    @Override
    public List<CheckResult> getCheckResultTree() {
        logger.info("获取检查结果的根节点");
        List<CheckResult> checkResults  = checkResultRepository.findAllByParentCheckResultIsNull();
        for (CheckResult checkResult: checkResults) {
            getTreeByCheckResult(checkResult);
        }
        return checkResults;
    }

    @Override
    public CheckResult getTreeByCheckResult(CheckResult checkResult) {
        List<CheckResult> checkResults = checkResultRepository.findAllByParentCheckResult(checkResult);
        for (CheckResult checkResult1: checkResults) {
            this.getTreeByCheckResult(checkResult1);
        }
        checkResult.setSonCheckResults(checkResults);
        return checkResult;
    }
}
