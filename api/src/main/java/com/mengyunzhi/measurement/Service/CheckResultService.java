package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.CheckResult;

import java.util.List;

/**
 * Created by liming on 17-7-26.
 * 检查定结果Service
 */
public interface CheckResultService {
    //获取检定结果的根节点及树状结构
    List<CheckResult> getCheckResultTree();
    //获取某个检定结果的树状结构
    CheckResult getTreeByCheckResult(CheckResult checkResult);
}
