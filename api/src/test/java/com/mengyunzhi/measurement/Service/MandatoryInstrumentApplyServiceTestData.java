package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.controller.MandatoryInstrumentApplyController;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApply;
import com.mengyunzhi.measurement.repository.User;
import com.mengyunzhi.measurement.repository.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by panjie on 17/7/27.
 */
@Component
public class MandatoryInstrumentApplyServiceTestData {
    @Autowired
    protected UserService userService;
    public User save(MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply workMandatoryInstrumentApply) {
        Work work = WorkService.getOneWork();
        MandatoryInstrumentApply mandatoryInstrumentApply = MandatoryInstrumentApplyService.getOneMandatoryInstrumentApply();
        User user = userService.loginWithOneUser();
        workMandatoryInstrumentApply.setWork(work);
        workMandatoryInstrumentApply.setMandatoryInstrumentApply(mandatoryInstrumentApply);
        return user;
    }
}
