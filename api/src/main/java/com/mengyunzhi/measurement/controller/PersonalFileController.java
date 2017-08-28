package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.PersonalFileService;
import com.mengyunzhi.measurement.repository.PersonalFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 * 人员资质管理界面的增加
 */
@Api(tags = "PersonalFile (人员资质管理)", description = "人员资质管理 综合查询")
@RestController
@RequestMapping("/PersonalFile")
public class PersonalFileController {

    @Autowired
    private PersonalFileService personalFileService;

    @ApiOperation(value = "save (保存)", notes = "保存人员资质管理实体", nickname = "PersonalFile_save")
    @PostMapping("/save")
    //保存数据
    public PersonalFile savePersonalFile(@RequestBody PersonalFile personalFile) {

        //保存数据
        personalFileService.save(personalFile);
        return personalFile;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "PersonalFile_getAll")
    @GetMapping("/getAll")
    //获取全部数据
    public List<PersonalFile> getAll() {

        //获取全部数据
        return personalFileService.getAll();
    }

}
