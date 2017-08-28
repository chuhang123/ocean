package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.controller.MandatoryInstrumentApplyController;
import com.mengyunzhi.measurement.repository.Apply;
import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.MandatoryInstrument;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApply;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by panjie on 17/7/14.
 * 强制检定器具申请审核
 */
public interface MandatoryInstrumentApplyService extends ApplyService{
    Map<String, Long> tokens = new HashMap<>();
    static MandatoryInstrumentApply getOneMandatoryInstrumentApply() {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setName("强制检定器具申请测试");
        return mandatoryInstrumentApply;
    }

    static String generateTokenById(Long id) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String token = CommonService.md5(id.toString() + timestamp.toString());
        MandatoryInstrumentApplyService.tokens.put(token, id);
        return token;
    }

    static Long getIdByToken(String token) {
        Long id = MandatoryInstrumentApplyService.tokens.get(token);
        return id;
    }

    static void deleteToken(String token) {
        if (null != MandatoryInstrumentApplyService.tokens.get(token)) {
            MandatoryInstrumentApplyService.tokens.remove(token);
        }
    }

    Page<Apply> getPageOfCurrentDepartment(Pageable pageable);
    MandatoryInstrumentApply findById(Long id);
    MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply save(MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply workMandatoryInstrumentApply);       // 保存工作
    // 更新
    MandatoryInstrumentApply update(MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException, ObjectNotFoundException;
    MandatoryInstrumentApply updateById(Long id, MandatoryInstrumentApply mandatoryInstrumentApply) throws ObjectNotFoundException;

    // 添加强检器具
    void addMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException;
    void addMandatoryInstrumentOfMandatoryInstrumentApplyId(MandatoryInstrument mandatoryInstrument, Long mandatoryInstrumentApplyId) throws ObjectNotFoundException;
    // 减少强检器具
    void subMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException;
    void subMandatoryInstrumentOfMandatoryInstrumentApplyId(MandatoryInstrument mandatoryInstrument, Long mandatoryInstrumentApplyId) throws ObjectNotFoundException;
    // 删除。同时删除强制检定器具及对应的工作信息。前提，当前用户为在办用户，流程未办结
    void delete(MandatoryInstrumentApply mandatoryInstrumentApply);
    // 当前用户是否可编辑
    boolean isCanEditOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply);
    // 计算某个部门对某个强检器具申请上的所有强检器具是否具备检定能力
    MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(Long mandatoryInstrumentApplyId, Long departmentId);
    MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(MandatoryInstrumentApply mandatoryInstrumentApply, Department department);
    File generateWordApplyByToken(String token) throws IOException, SecurityException;
    File generateWordApplyById(Long id) throws IOException;

}
