package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentApplyService;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.repository.Apply;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApply;
import com.mengyunzhi.measurement.repository.Work;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by liming on 17-7-15.
 * 强检器具申请C层
 */
@RestController
@RequestMapping("/MandatoryInstrumentApply")
@Api(tags = "MandatoryInstrumentApply 强制检定器具申请")
public class MandatoryInstrumentApplyController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    protected MandatoryInstrumentApplyService mandatoryInstrumentApplyService;

    @GetMapping("/getPageOfCurrentDepartment")
    @ApiOperation(value = "/getPageOfCurrentDepartment 获取当前部门的申请列表", notes = "获取当前用户下的所有技术机构", nickname = "MandatoryInstrumentApply_getPageOfCurrentDepartment")
    public Page<Apply> getPageOfCurrentDepartment(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Apply> applies = mandatoryInstrumentApplyService.getPageOfCurrentDepartment(pageable);
        return applies;
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "/findById", notes = "获取强制检定器具申请", nickname = "MandatoryInstrumentApply_findById")
    public MandatoryInstrumentApply findById(@Param("申请ID") @PathVariable("id") Long id) {
        return mandatoryInstrumentApplyService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "/ 新建申请", notes = "新建申请", nickname = "MandatoryInstrumentApply_save")
    public WorkMandatoryInstrumentApply save(@ApiParam("强检器具申请") @RequestBody WorkMandatoryInstrumentApply workMandatoryInstrumentApply) {
        mandatoryInstrumentApplyService.save(workMandatoryInstrumentApply);
        return workMandatoryInstrumentApply;
    }

    @PatchMapping("/{mandatoryInstrumentApplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "/{mandatoryInstrumentApplyId} 更新申请", notes = "更新申请情况以及申请对应的强制检定器具", nickname = "MandatoryInstrumentApply_update_all")
    public MandatoryInstrumentApply updateAll(@ApiParam("强检申请ID") @PathVariable Long mandatoryInstrumentApplyId, @Param("强制检定申请") @RequestBody MandatoryInstrumentApply mandatoryInstrumentApply) {
        return mandatoryInstrumentApplyService.updateById(mandatoryInstrumentApplyId, mandatoryInstrumentApply);
    }

    @GetMapping("/computeCheckAbilityBy/MandatoryInstrumentApplyId/{mandatoryInstrumentApplyId}/DepartmentId/{departmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "computeCheckAbilityBy/MandatoryInstrumentApplyId/{MandatoryInstrumentApplyId}/DepartmentId/{departmentId} 计算某部门对某个强检申请上的所有强检器具是否具备检定能力",
            notes = "计算某部门对某个强检申请上的所有强检器具是否具备检定能力",
            nickname = "MandatoryInstrumentApply_computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId")
    @JsonView(ApplyJsonView.get.class)
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(
            @ApiParam("强制检定申请ID") @PathVariable("mandatoryInstrumentApplyId") Long mandatoryInstrumentApplyId,
            @ApiParam("部门ID") @PathVariable("departmentId") Long departmentId) {
        return mandatoryInstrumentApplyService.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(mandatoryInstrumentApplyId, departmentId);
    }

    @ApiOperation(value = "generateWordApplyByToken", notes = "通过token获取word自动化文档", nickname = "MandatoryInstrumentApply_generateWordApplyByToken")
    @GetMapping("/generateWordApplyByToken/{token}")
    public void generateWordApplyByToken(@Param("token 用于验证请求是否合法，每个TOKEN只能使用一次") @PathVariable("token") String token, HttpServletResponse response) throws IOException {
        File file = mandatoryInstrumentApplyService.generateWordApplyByToken(token);
        FileInputStream inputStream = new FileInputStream(file);
        response.setHeader("Content-Type", "application/msword");
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        file.deleteOnExit();
    }

    @ApiOperation(value = "generateTokenById", notes = "通过ID获取apply对应的token", nickname = "MandatoryInstrumentApply_generateTokenById")
    @GetMapping("/generateTokenById/{id}")
    public String generateTokenById(@ApiParam("id 申请ID") @PathVariable("id") Long id) throws Exception {
        return MandatoryInstrumentApplyService.generateTokenById(id);
    }

    static public class WorkMandatoryInstrumentApply {
        private Work work;
        private MandatoryInstrumentApply mandatoryInstrumentApply;

        public WorkMandatoryInstrumentApply() {
        }

        public Work getWork() {
            return work;
        }

        public void setWork(Work work) {
            this.work = work;
        }

        public MandatoryInstrumentApply getMandatoryInstrumentApply() {
            return mandatoryInstrumentApply;
        }

        public void setMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) {
            this.mandatoryInstrumentApply = mandatoryInstrumentApply;
        }
    }
}
