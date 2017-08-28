package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.InstrumentTypeService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentTypeService;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentTypeJsonView;
import com.mengyunzhi.measurement.repository.InstrumentType;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/26.
 * 器具类别 C层
 */
@Api(tags = "MandatoryInstrumentType 强制检定器具类别", description = "强制检定器具类别")
@RestController
@RequestMapping("/MandatoryInstrumentType")
public class MandatoryInstrumentTypeController {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentTypeController.class.getName());

    @Autowired
    @Qualifier("MandatoryInstrumentTypeService")
    private InstrumentTypeService instrumentTypeService;
    @Autowired
    private MandatoryInstrumentTypeService mandatoryInstrumentTypeService;  //  强制检定器具类别

    @ApiOperation(value = "保存", notes = "器具类别实体", nickname = "MandatoryInstrumentType_save")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public MandatoryInstrumentType save(@ApiParam(value = "器具类别实体") @RequestBody MandatoryInstrumentType mandatoryInstrumentType)
    {
        instrumentTypeService.save(mandatoryInstrumentType);
        return mandatoryInstrumentType;
    }

    @ApiOperation(value = "update(修改)", notes = "修改某条数据", nickname = "MandatoryInstrumentType_update")
    @ApiResponse(code = 204, message = "修改成功")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public void update(@ApiParam("ID") @PathVariable Long id, @RequestBody MandatoryInstrumentType mandatoryInstrumentType)
    {
        logger.info("---- 更新实体 ----");
        logger.info("调用M层方法更新实体");
        instrumentTypeService.update(id, mandatoryInstrumentType);
        return;
    }

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "MandatoryInstrumentType_getAll")
    @GetMapping("/getAll")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public List<InstrumentType> getAll()
    {
        return instrumentTypeService.getAll();
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "MandatoryInstrumentType_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        instrumentTypeService.delete(id);
        return;
    }

    @ApiOperation(value = "get 查询数据", notes = "获取某个实体", nickname = "MandatoryInstrumentType_get")
    @ApiResponse(code = 404, message = "要查询的实体不存在或已删除")
    @GetMapping("/{id}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public InstrumentType get(@ApiParam(value = "id 实体ID") @PathVariable Long id) {
        InstrumentType instrumentType = instrumentTypeService.get(id);
        return instrumentType;
    }

    @ApiOperation(value = "pageByDisciplineId 获取某个学科的所有数据", notes = "进行数据分页", nickname = "InstrumentType_pageByDisciplineId")
    @GetMapping("/pageByDisciplineId/{id}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public Page<MandatoryInstrumentType> pageByDisciplineId(@ApiParam("学科id") @PathVariable Long id, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<MandatoryInstrumentType> mandatoryInstrumentType = mandatoryInstrumentTypeService.getAllMandatoryInstrumentTypesByDisciplineId(id, pageable);
        return mandatoryInstrumentType;
    }

    @ApiOperation(value = "allByDisciplineId 获取某个学科的所有数据", nickname = "MandatoryInstrumentType_allByDisciplineId")
    @GetMapping("allByDisciplineId/{id}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public List<InstrumentType> allByDisciplineId(@ApiParam("学科id") @PathVariable Long id) {
        List<InstrumentType> instrumentTypes = instrumentTypeService.getAllByDisciplineId(id);

        return instrumentTypes;
    }

    @ApiOperation(value = "getByMeasureScaleId 获取测量范围对应的器具类别", nickname = "MandatoryInstrumentType_getByMeasureScaleId")
    @GetMapping("/getByMeasureScaleId/{measureScaleId}")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    public InstrumentType getByMeasureScaleId(@ApiParam("测量范围ID") @PathVariable Long measureScaleId) {
        return instrumentTypeService.getByMeasureScaleId(measureScaleId);
    }

    @ApiOperation(value = "getAllByInstrumentFirstLevelTypeId", notes = "获取某个一级类别下的所有数据", nickname = "MandatoryInstrumentType_getAllByInstrumentFirstLevelTypeId")
    @JsonView(MandatoryInstrumentTypeJsonView.Base.class)
    @GetMapping("/getAllByInstrumentFirstLevelTypeId/{instrumentFirstLevelTypeId}")
    public List<MandatoryInstrumentType> getAllByInstrumentFirstLevelTypeId(@ApiParam("一级类别ID") @PathVariable(name = "instrumentFirstLevelTypeId") Long instrumentFirstLevelTypeId) {
        return mandatoryInstrumentTypeService.getAllByInstrumentFirstLevelTypeId(instrumentFirstLevelTypeId);
    }

}
