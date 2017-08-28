package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.PurposeService;
import com.mengyunzhi.measurement.repository.Purpose;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/14.
 * 用途 C层
 */
@Api(tags = "Purpose (用途)", description = "器具使用信息 用途")
@RestController
@RequestMapping("/Purpose")
public class PurposeController {
    private static Logger logger = Logger.getLogger(PurposeController.class.getName());

    @Autowired
    private PurposeService purposeService;

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "Purpose_getAll")
    @GetMapping("/getAll")
    public List<Purpose> getAll(){
        return purposeService.getAll();
    }
}
