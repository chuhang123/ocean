package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentWorkService;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import com.mengyunzhi.measurement.repository.Work;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请 工作
 */
@RestController
@RequestMapping("/MandatoryInstrumentWork")
@Api(tags = "MandatoryInstrumentWork 强制检定器具申请工作", description = "强制检定器具申请工作")
public class MandatoryInstrumentWorkController {
    @Autowired protected MandatoryInstrumentWorkService mandatoryInstrumentWorkService; // 强制检定工作

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUser")
    @ApiOperation(value = "pageOfCurrentLoginUser 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUser")
    public Page<Work> pageOfCurrentLoginUser( @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUser(pageable);
        return works;
    }
}
