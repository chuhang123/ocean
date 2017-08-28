package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.StandardFileService;
import com.mengyunzhi.measurement.repository.StandardFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
@Api(tags = "StandardFile (标准装置)", description = "标准装置 档案查询")
@RestController
@RequestMapping("/StandardFile")
public class StandardFileController {

    @Autowired
    private StandardFileService standardFileService;

    @ApiOperation(value = "save (保存)", notes = "保存标准装置实体", nickname = "StandardFile_save")
    @PostMapping("/save")
    public StandardFile save(@ApiParam(value = "标准装置实体") @RequestBody StandardFile standardFile) {
        standardFileService.save(standardFile);
        return standardFile;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "StandardFile_getAll")
    @GetMapping("/getAll")
    public List<StandardFile> getAll() {
        return standardFileService.getAll();
    }
}
