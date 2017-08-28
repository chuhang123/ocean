package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CheckResultService;
import com.mengyunzhi.measurement.repository.CheckResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liming on 17-7-27.
 * 检定结果Controller
 */
@Api(tags = "CheckResult (检定结果)", description = "检定结果实体对应的C层")
@RestController
@RequestMapping("/CheckResult")
public class CheckResultController {
    @Autowired protected CheckResultService checkResultService;

    @GetMapping("/getCheckResultTree")
    @ApiOperation(value = "getCheckResultTree (获取检定结果)", notes = "获取检定结果的树状结构", nickname = "CheckResult_getCheckResultTree")
    public List<CheckResult> getCheckResultTree() {
        List<CheckResult> checkResults = checkResultService.getCheckResultTree();
        return checkResults;
    }
}
