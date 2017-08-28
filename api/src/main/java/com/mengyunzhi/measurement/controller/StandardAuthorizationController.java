package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.StandardAuthorizationServie;
import com.mengyunzhi.measurement.repository.StandardAuthorization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-5-7.
 */
@Api(tags = "StandardAuthorization (标准装置)", description = "标准装置 机构授权检定项目")
@RestController
@RequestMapping("/StandardAuthorization")
public class StandardAuthorizationController {

    @Autowired
    private StandardAuthorizationServie standardAuthorizationServie;

    @ApiOperation(value = "save (保存)", notes = "保存标准装置实体", nickname = "StandardAuthorization_save")
    @PostMapping("/save")
    public StandardAuthorization save(@ApiParam(value = "器具实体") @RequestBody StandardAuthorization standardAuthorization) {
        standardAuthorizationServie.save(standardAuthorization);
        return standardAuthorization;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "StandardAuthorization_getAll")
    @GetMapping("/getAll")
    public List<StandardAuthorization> getAll() {
        return standardAuthorizationServie.getAll();
    }

}
