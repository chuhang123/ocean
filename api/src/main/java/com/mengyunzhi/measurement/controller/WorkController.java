package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.WorkService;
import com.mengyunzhi.measurement.input.WorkInput;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import com.mengyunzhi.measurement.repository.Work;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/7/18.
 * 工作
 */
@RestController
@RequestMapping("/Work")
@Api(tags = "Work 工作", description = "用户提交申请后，生成工作记录。 author:panjie")
public class WorkController {
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;

    @PatchMapping("/updateToDoingById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "updateToDoingById",
            notes = "更新某个ID记录的WORK为在办",
            nickname = "Work_updateToDoingById")
    public void updateToDoingById(@ApiParam("工作id") @PathVariable("id") Long id) {
        workService.updateToDoingByWorkId(id);
    }

    @GetMapping("/getOriginWorkByWorkId/{id}")
    @ApiOperation(value = "findFirstWorkById 获取首工作",
            notes = "获取某个工作对应的起点工作(即上一申请人提交的工作)",
            nickname = "Work_getOriginWorkByWorkId")
    public Work getOriginWorkByWorkId(@ApiParam("工作ID") @PathVariable("id") Long id) {
        return workService.getOriginWorkByWorkId(id);
    }

    @GetMapping("/getAllByApplyId/{applyId}")
    @ApiOperation(value = "getAllByApplyId 获取某个申请的所有记录",
            notes = "获取某个申请的所有记录 ID从小到大自然排序",
            nickname = "Work_getAllByApplyId")
    @JsonView(WorkJsonView.getAllByApplyId.class)
    public List<Work> getAllByApplyId(@ApiParam("申请ID") @PathVariable("applyId") Long applyId) {
        return workService.getAllByApplyId(applyId);
    }
    @PatchMapping("/auditById/{workId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "auditById 审核某个工作",
            notes = "审核某个工作（当前用户）",
            nickname = "Work_auditById")
    public void auditById(@ApiParam("工作ID") @PathVariable("workId") Long workId, @ApiParam("审核意见等") @RequestBody WorkInput.AuditByIdInput auditByIdInput) {
        workService.auditByWorkIdAndOpinionAndAuditDepartmentAndAuditTypeAndWorkflowNode(
                workId,
                auditByIdInput.getOpinion(),
                auditByIdInput.getDepartment(),
                auditByIdInput.getType(),
                auditByIdInput.getNextWorkflowNode()
        );
        return;
    }

}
