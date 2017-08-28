package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 器具一级类别
 */
@RestController
@Api(tags = "InstrumentFirstLevelType 器具一级类别")
@RequestMapping("/InstrumentFirstLevelType")
public class InstrumentFirstLevelTypeController {
    @Autowired
    InstrumentFirstLevelTypeService instrumentFirstLevelTypeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = " 保存", nickname = "InstrumentFirstLevelType_save")
    public InstrumentFirstLevelType save(
            @ApiParam("器具一级类别") @RequestBody InstrumentFirstLevelType instrumentFirstLevelType){
        return instrumentFirstLevelTypeService.save(instrumentFirstLevelType);
    }

    @GetMapping("/getAllByDisciplineId/{disciplineId}")
    @ApiOperation(
            value = "getAllByDisciplineId 获取某个学科类的下的列表",
            nickname = "InstrumentFirstLevelType_getAllByDisciplineId")
    public List<InstrumentFirstLevelType> getAllByDisciplineId(
            @Param("学科类别id") @PathVariable("disciplineId") Long disciplineId) {
                return instrumentFirstLevelTypeService.getAllByDisciplineId(disciplineId);
    }
}
