package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.jsonView.StandardDeviceJsonView;
import com.mengyunzhi.measurement.repository.StandardDevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-5-9.
 */
@Api(tags = "StandardDevice (标准装置 标准器)", description = "标准装置 标准器")
@RestController
@RequestMapping("/StandardDevice")
public class StandardDeviceController {
    @Autowired
    private StandardDeviceService standardDeviceService;

    @ApiOperation(value = "save (保存)", notes = "保存标准装置标准器实体", nickname = "standardDevice_save")
    @PostMapping("/save")
    public StandardDevice save(@RequestBody StandardDevice standardDevice) {
        return standardDeviceService.save(standardDevice);
    }

    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    @ApiOperation(value = "getAllByDeviceSetId (获取标准器)", notes = "通过标准装置id获取相应的标准器", nickname = "standardDevice_getAllByDeviceSetId")
    @GetMapping("/getAllByDeviceSetId/{deviceSetId}")
    public List<StandardDevice> getAllByDeviceSetId(@ApiParam(value = "标准装置ID") @PathVariable Long deviceSetId) {
        //返回数据
        return standardDeviceService.getAllByDeviceSetId(deviceSetId);
    }

    @ApiOperation(value = "update (更新)", notes = "更新标准器实体", nickname = "standardDevice_update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@ApiParam(value = "标准器ID") @PathVariable Long id, @ApiParam(value = "标准器实体") @RequestBody StandardDevice standardDevice) {
        standardDeviceService.update(id, standardDevice);
        return;
    }

    @ApiOperation(value = "delete (删除)", notes = "删除标准器", nickname = "standardDevice_delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "标准器ID") @PathVariable Long id) {
        standardDeviceService.delete(id);
        return;
    }


    @ApiOperation(value = "pageAllByDeviceSetId (获取所有的标准器)", notes = "通过标准装置id获取相应的标准器", nickname = "standardDevice_pageAllByDeviceSetId")
    @GetMapping("/pageAllByDeviceSetId/{deviceSetId}")
    public Page<StandardDevice> pageAllByDeviceSetId(@ApiParam(value = "标准装置ID") @PathVariable Long deviceSetId, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return standardDeviceService.pageAllByDeviceSetId(deviceSetId, pageable);
    }
}
