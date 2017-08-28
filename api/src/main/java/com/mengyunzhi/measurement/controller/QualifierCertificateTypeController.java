package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.QualifierCertificateTypeService;
import com.mengyunzhi.measurement.repository.QualifierCertificateType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * Created by zhangjiahao on 2017/6/10.
 * 资格证类别 C层
 */
@Api(tags = "QualifierCertificateType(资格证类别)", description = "人员资质管理 资格证类别实体")
@RestController
@RequestMapping("/QualifierCertificateType")
public class QualifierCertificateTypeController {
    private static Logger logger = Logger.getLogger(QualifierCertificateTypeController.class.getName());
    @Autowired
    private QualifierCertificateTypeService qualifierCertificateTypeService;

    @ApiOperation(value = "保存", notes = "保存资格证类别实体", nickname = "QualifierCertificateType_save")
    @PostMapping("/save")
    public QualifierCertificateType save(@ApiParam(value = "资格证类别实体") @RequestBody QualifierCertificateType qualifierCertificateType)
    {
        qualifierCertificateTypeService.save(qualifierCertificateType);
        return qualifierCertificateType;
    }

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "QualifierCertificateType_getAll")
    @GetMapping("/getAll")
    public List<QualifierCertificateType> getAll()
    {
        return qualifierCertificateTypeService.getAll();
    }

    @ApiOperation(value = "update (修改)", notes = "修改某条数据", nickname = "QualifierCertificate_update")
    @ApiResponse(code = 204, message = "更新成功")
    @PutMapping("/update/{id}")
    public void update(@ApiParam("ID") @PathVariable Long id, @ApiParam(value = "资格证类别实体") @RequestBody QualifierCertificateType qualifierCertificateType, HttpServletResponse response)
    {
        logger.info("---- 更新实体 ----");
        logger.info("在数据更新中，可以直接调用M层的方法");
        try{
            qualifierCertificateTypeService.update(id, qualifierCertificateType);
            response.setStatus(204);
        } catch(ObjectNotFoundException e){
            response.setStatus(404);
        }

        return;
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "QualifierCertificateType_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        qualifierCertificateTypeService.delete(id);
        return;
    }

    @ApiOperation(value = "getAllByDisciplineId", notes = "通过学科id获取全部的资格证类别信息", nickname = "QualifierCertificateType_getAllByDisciplineId")
    @GetMapping("/getAllByDisciplineId/{disciplineId}")
    public Page<QualifierCertificateType> getAllByDisciplineId(@ApiParam(value = "学科ID") @PathVariable Long disciplineId, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable)
    {
        Page<QualifierCertificateType> qualifierCertificateTypes = qualifierCertificateTypeService.getAllByDisciplineId(disciplineId, pageable);
        return qualifierCertificateTypes;
    }


}
