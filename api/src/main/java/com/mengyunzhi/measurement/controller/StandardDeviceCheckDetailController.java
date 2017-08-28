package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.StandardDeviceCheckDetailService;
import com.mengyunzhi.measurement.jsonView.StandardDeviceCheckDetailJsonView;
import com.mengyunzhi.measurement.repository.StandardDevice;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-5-14.
 */
@Api(tags = "StandardDeviceCheckDetail (标准器检定信息)", description = "标准装置 标准器检定信息")
@RestController
@RequestMapping("/StandardDeviceCheckDetail")
public class StandardDeviceCheckDetailController {
    @Autowired
    private StandardDeviceCheckDetailService standardDeviceCheckDetailService;

    @ApiOperation(value = "save (保存)", notes = "保存标准器检定信息实体", nickname = "StandardDeviceCheckDetail_save")
    @PostMapping("/save")
    public StandardDeviceCheckDetail save(@RequestBody StandardDeviceCheckDetail standardDeviceCheckDetail) {
        //保存
        return standardDeviceCheckDetailService.save(standardDeviceCheckDetail);
    }
    @ApiOperation(value = "update (编辑)", notes = "编辑标准检定信息实体", nickname = "StandardDeviceCheckDetail_update")
    @PutMapping("/update/{id}")
    private void update(@ApiParam(value = "编辑标准检定信息实体") @RequestBody StandardDeviceCheckDetail standardDeviceCheckDetail, @PathVariable Long id){
        standardDeviceCheckDetailService.update(standardDeviceCheckDetail,id);
        return;
    }

    @ApiOperation(value = "delete (删除)" , notes = "删除标准杆检定信息实体" , nickname = "StandardDeviceCheckDetail_delete")
    @DeleteMapping("/delete/{id}")
    private void delete(@ApiParam(value = "编辑标准检定信息实体") @PathVariable Long id) {
        standardDeviceCheckDetailService.delete(id);
        return;
    }

    @ApiOperation(value = "getAllByStandardDevice (获取检定信息)", notes = "根据standardDevice获取标准器相应的检定信息", nickname = "StandardDeviceCheckDetail_getAllByStandardDevice")
    @PostMapping("/getAllByStandardDevice")
    public List<StandardDeviceCheckDetail> getAllByStandardDevice(@RequestBody StandardDevice standardDevice) {
        return standardDeviceCheckDetailService.getAllByStandardDevice(standardDevice);
    }

    @JsonView(StandardDeviceCheckDetailJsonView.BaseJsonView.class)
    @ApiOperation(value = "pageAllByStandardDeviceId (获取检定信息分页)", notes = "根据standardDeviceId获取标准器的检定信息")
    @GetMapping("/pageAllByStandardDeviceId/{standardDeviceId}")
    public Page<StandardDeviceCheckDetail> pageAllByStandardDeviceId(@PathVariable Long standardDeviceId, @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return standardDeviceCheckDetailService.pageAllByStandardDeviceId(standardDeviceId, pageable);
    }
}
