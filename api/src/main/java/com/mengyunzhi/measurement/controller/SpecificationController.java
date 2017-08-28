package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.SpecificationService;
import com.mengyunzhi.measurement.repository.Specification;
import com.mengyunzhi.measurement.repository.SpecificationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/27.
 * 规格型号 C层
 */
@Api(tags = "Specification (规格型号)", description = "标准器 规格型号")
@RestController
@RequestMapping("/Specification")
public class SpecificationController {
    private Logger logger = Logger.getLogger(SpecificationController.class.getName());


    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private SpecificationRepository specificationRepository;

    @ApiOperation(value = "保存", notes = "规格型号实体", nickname = "Specification_save")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Specification save(@ApiParam(value = "规格型号实体") @RequestBody Specification specification){
        specificationService.save(specification);
        return specification;
    }

    @ApiOperation(value = "update(更新)", notes = "更新某条数据", nickname = "Specification_update")
    @ApiResponse(code = 204, message = "修改成功")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@ApiParam("ID") @PathVariable Long id, @RequestBody Specification specification, HttpServletResponse response)
    {
        logger.info("---- 更新实体 ----");       
        specificationService.update(id, specification);
        return;
    }

    @ApiOperation(value = "getAll(获取所有数据)", notes = "获取所有数据", nickname = "Specification_getAll")
    @GetMapping("/getAll")
    public List<Specification> getAll()
    {
        return specificationService.getAll();
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "Specification_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        specificationService.delete(id);
        return;
    }
}
