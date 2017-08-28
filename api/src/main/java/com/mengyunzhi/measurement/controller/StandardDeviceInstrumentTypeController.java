package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.StandardDeviceInstrumentTypeService;
import com.mengyunzhi.measurement.jsonView.StandardDeviceInstrumentTypeJsonView;
import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by liming on 17-7-27.
 * 标准器类别Controller
 */
@Api(tags = "StandardDeviceInstrumentType 标准器类别", description = "标准器器具类别Controller")
@RequestMapping("/StandardDeviceInstrumentType")
@RestController
public class StandardDeviceInstrumentTypeController {
    private Logger logger = Logger.getLogger(StandardDeviceInstrumentTypeController.class.getName());

    @Autowired private StandardDeviceInstrumentTypeService standardDeviceInstrumentTypeService;

    @ApiOperation(value = "保存", notes = "标准器器具类别实体", nickname = "StandardDeviceInstrumentType_save")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public StandardDeviceInstrumentType save(@ApiParam(value = "器具类别实体") @RequestBody StandardDeviceInstrumentType standardDeviceInstrumentType)
    {
        standardDeviceInstrumentTypeService.save(standardDeviceInstrumentType);
        return standardDeviceInstrumentType;
    }

    @ApiOperation(value = "update(修改)", notes = "修改某条数据", nickname = "StandardDeviceInstrumentType_update")
    @ApiResponse(code = 204, message = "修改成功")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public void update(@ApiParam("ID") @PathVariable Long id, @RequestBody StandardDeviceInstrumentType standardDeviceInstrumentType)
    {
        logger.info("---- 更新实体 ----");
        logger.info("调用M层方法更新实体");
        standardDeviceInstrumentTypeService.update(id,standardDeviceInstrumentType);
        return;
    }

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "StandardDeviceInstrumentType_getAll")
    @GetMapping("/getAll")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public List<StandardDeviceInstrumentType> getAll()
    {
        return standardDeviceInstrumentTypeService.getAllStandardDeviceInstrumentTypes();
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "StandardDeviceInstrumentType_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        standardDeviceInstrumentTypeService.delete(id);
        return;
    }

    @ApiOperation(value = "get 查询数据", notes = "获取某个实体", nickname = "InstrumentType_get")
    @ApiResponse(code = 404, message = "要查询的实体不存在或已删除")
    @GetMapping("/{id}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public StandardDeviceInstrumentType get(@ApiParam(value = "id 实体ID") @PathVariable Long id) {
        StandardDeviceInstrumentType standardDeviceInstrumentType = standardDeviceInstrumentTypeService.get(id);
        return standardDeviceInstrumentType;
    }

    @ApiOperation(value = "pageByDisciplineId 获取某个学科的所有数据", notes = "进行数据分页", nickname = "InstrumentType_pageByDisciplineId")
    @GetMapping("/pageByDisciplineId/{id}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public Page<StandardDeviceInstrumentType> pageByDisciplineId(@ApiParam("学科id") @PathVariable Long id, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<StandardDeviceInstrumentType> standardDeviceInstrumentTypes = standardDeviceInstrumentTypeService.pageByDisciplineId(id, pageable);
        return standardDeviceInstrumentTypes;
    }

//    @ApiOperation(value = "allByDisciplineId 获取某个学科的所有数据", nickname = "InstrumentType_allByDisciplineId")
//    @GetMapping("allByDisciplineId/{id}")
//    public List<InstrumentType> allByDisciplineId(@ApiParam("学科id") @PathVariable Long id) {
//        List<InstrumentType> instrumentTypes = instrumentTypeService.getAllByDisciplineId(id);
//
//        return instrumentTypes;
//    }

    @ApiOperation(value = "getByMeasureScaleId 获取测量范围对应的标准器类别", nickname = "StandardDeviceInstrumentType_getByMeasureScaleId")
    @GetMapping("/getByMeasureScaleId/{measureScaleId}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public StandardDeviceInstrumentType getByMeasureScaleId(@ApiParam("测量范围ID") @PathVariable Long measureScaleId) {
        return standardDeviceInstrumentTypeService.getByMeasureScaleId(measureScaleId);
    }

    @ApiOperation(value = "getAllByInstrumentFirstLevelTypeId 获取一级器具类别下的标准器类别", nickname = "StandardDeviceInstrumentType_getAllByInstrumentFirstLevelTypeId")
    @GetMapping("/getAllByInstrumentFirstLevelTypeId/{instrumentFirstLevelTypeId}")
    @JsonView(StandardDeviceInstrumentTypeJsonView.Base.class)
    public List<StandardDeviceInstrumentType> getAllByInstrumentFirstLevelTypeId(@ApiParam("一级器具类别ID") @PathVariable(name = "instrumentFirstLevelTypeId") Long instrumentFirstLevelTypeId) {
        return standardDeviceInstrumentTypeService.getAllByInstrumentFirstLevelTypeId(instrumentFirstLevelTypeId);
    }
}
