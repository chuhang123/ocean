package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.AccuracyService;
import com.mengyunzhi.measurement.repository.Accuracy;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
@RequestMapping("/Accuracy")
@Api(tags = "Accuracy 精度", description = "精度")
@RestController
public class AccuracyController {
    @Autowired
    private AccuracyService accuracyService; // 精度

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "/Accuracy/ 新增实体", notes = "增加一个新实体", nickname = "Accuracy_save")
    public Accuracy save(@ApiParam("Accuracy 精度实体") @RequestBody Accuracy accuracy) {
        accuracyService.save(accuracy);
        return accuracy;
    }
}
