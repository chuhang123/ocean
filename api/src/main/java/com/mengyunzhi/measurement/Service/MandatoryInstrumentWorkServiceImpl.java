package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.controller.MandatoryInstrumentWorkController;
import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.Work;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请
 */
@Service
public class MandatoryInstrumentWorkServiceImpl extends WorkServiceImpl implements MandatoryInstrumentWorkService {
    private Logger logger = Logger.getLogger(MandatoryInstrumentWorkServiceImpl.class.getName());
    @Autowired
    protected UserService userService;                  // 用户

    @Override
    public Page<Work> pageOfCurrentLoginUser(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageByDepartmentOfMandatoryInstrumentGroupByApply(department, pageable);
    }
}
