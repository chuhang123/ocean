package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.ApplyService;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.repository.Apply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-6-3.
 * 申请
 */
@Api(tags = "Apply (申请)", description = "申请实体的C层")
@RestController
@RequestMapping("/Apply")
public class ApplyController {
    @Autowired @Qualifier("ApplyService")
    private ApplyService applyService;

    @ApiOperation(value = "save (保存)", notes = "保存申请实体", nickname = "Apply_save")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(ApplyJsonView.get.class)
    public Apply save(@ApiParam(value = "apply实体") @RequestBody Apply apply) {
        //保存
        return applyService.save(apply);
    }

    @ApiOperation(value = "get (获取一个Apply)", notes = "获取一个Apply", nickname = "Apply_get")
    @GetMapping("/get/{id}")
    @JsonView(ApplyJsonView.get.class)
    public Apply get(@ApiParam(value = "applyId 申请ID") @PathVariable Long id) {
        return applyService.get(id);
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "Apply_getAll")
    @GetMapping("/getAll")
    @JsonView(ApplyJsonView.get.class)
    public List<Apply> getAll() {
        return applyService.getAll();
    }

    @ApiOperation(value = "delete", notes = "删除一条数据", nickname = "Apply_delete")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "applyId") @PathVariable Long id) {
        applyService.delete(id);
        return;
    }

    @ApiOperation(value = "/ 更新", notes = "更新基本信息 (关键信息由系统自动生成，不能由前台更新", nickname = "Apply_update")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Apply update(@ApiParam("id 申请id") @PathVariable Long id, @ApiParam("Apply 申请实体") @RequestBody Apply apply) {
        return applyService.update(id, apply);
    }
}
