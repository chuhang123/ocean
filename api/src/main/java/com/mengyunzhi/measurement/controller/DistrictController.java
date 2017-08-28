package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.DistrictService;
import com.mengyunzhi.measurement.jsonView.DistrictJsonView;
import com.mengyunzhi.measurement.repository.District;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-6-2.
 */
@Api(tags = "District (区域)", description = "区域实体对应的C层")
@RestController
@RequestMapping("/District")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @ApiOperation(value = "save (保存)", notes = "保存区域实体", nickname = "District_save")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)         //新增成功返回状态码201
    public District save(@ApiParam(value = "区域实体") @RequestBody District district) {
        districtService.save(district);
        return district;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "District_getAll")
    @GetMapping("/getAll")
    public List<District> getAll() {
        //取出所有的数据
        List<District> districts = districtService.getAll();
        return districts;
    }

    @ApiOperation(value = "get (获取一条数据)", notes = "获取一条数据", nickname = "District_get")
    @GetMapping("/get/{id}")
    public District get(@ApiParam(value = "区域实体id") @PathVariable Long id) {
        //获取一条数据
        District district = districtService.get(id);
        return district;
    }

    @ApiOperation(value = "delete", notes = "删除一条数据", nickname = "District_delete")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //删除成功返回状态码204
    public void delete(@ApiParam(value = "区域实体id") @PathVariable Long id) {
        //删除数据
        districtService.delete(id);
        return;
    }

    @ApiOperation(value = "getTreeById 获取某个ID的树", notes = "获取某个部门数据（包括子结点的树型结构）", nickname = "District_getTreeById")
    @GetMapping("/getTreeById/{id}")
    @JsonView(DistrictJsonView.WithSonDistricts.class)
    public District getTreeById(@ApiParam(value = "区域ID") @PathVariable Long id) {
        District district = districtService.get(id);
        districtService.getTreeByDistrict(district);
        return district;
    }

    @ApiOperation(value = "getRootDistrictTree 获取根区域", notes = "获取根区域（包括子结点的树型结构)", nickname = "District_getRootDistrictTree")
    @GetMapping("/getRootDistrictTree")
    @JsonView(DistrictJsonView.WithSonDistricts.class)
    public District getRootDistrictTree() {
        District district = districtService.getRootDistrictTree();
        return district;
    }
}
