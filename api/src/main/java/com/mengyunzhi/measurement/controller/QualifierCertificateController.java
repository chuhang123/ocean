package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.QualifierCertificateService;
import com.mengyunzhi.measurement.repository.Qualifier;
import com.mengyunzhi.measurement.repository.QualifierCertificate;
import com.mengyunzhi.measurement.repository.QualifierCertificateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/22.
 * 人员资质 C层
 */
@Api(tags = "QualifierCertificate (人员资质)", description = "人员资质实体")
@RestController
@RequestMapping("/QualifierCertificate")
public class QualifierCertificateController {
    private static Logger logger = Logger.getLogger(QualifierCertificateController.class.getName());

    @Autowired
    private QualifierCertificateRepository qualifierCertificateRepository;
    @Autowired
    private QualifierCertificateService qualifierCertificateService;

    @ApiOperation(value = "添加人员资质", notes = "本部门登录用户只能添加本部门人员的人员资质,如果用户试图通过url来给其他部门的人员添加资质的时候，会抛出SecurityException，提示用户没有此操作权限。", nickname = "QualifierCertificate_addQualifierCertificateOfCurrentUser")
    @PostMapping("/addQualifierCertificateOfCurrentUser")
    @ResponseStatus(HttpStatus.CREATED)
    public QualifierCertificate addQualifierCertificateOfCurrentUser(@RequestBody QualifierCertificate qualifierCertificate)
    {
        logger.info("添加人员资质");
        QualifierCertificate qualifierCertificate1 = qualifierCertificateService.addQualifierCertificateOfCurrentUser(qualifierCertificate);
        return qualifierCertificate1;
    }

    @ApiOperation(value = "更新人员资质", notes = "本部门登录用户只能编辑本部门人员的人员资质,如果用户试图通过url来给其他部门的人员编辑资质的时候，会抛出SecurityException，提示用户没有此操作权限。", nickname ="QualifierCertificate_updateQualifierCertificateOfCurrentUser")
    @PutMapping(value = "/updateQualifierCertificateOfCurrentUser/{id}")
    @ApiResponse(code = 204, message = "更新成功")
    public void updateQualifierCertificateOfCurrentUser(@ApiParam(value = "实体ID") @PathVariable Long id, @ApiParam(value = "人员资质实体") @RequestBody QualifierCertificate qualifierCertificate, HttpServletResponse response)
    {
        try{
            QualifierCertificate qualifierCertificate1 = qualifierCertificateService.updateQualifierCertificateOfCurrentUser(id, qualifierCertificate);
            response.setStatus(204);
        }
        catch (ObjectNotFoundException e)
        {
            response.setStatus(404);
        }
        return;
    }

    @ApiOperation(value = "delete",notes = "删除当前登录用户对应的部门的某个人员的人员资质信息", nickname = "QualifierCertificate_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        qualifierCertificateService.delete(id);
        return;
    }

    @ApiOperation(value = "获取所有数据", notes = "通过部门id获取该部门下对应的所有的人员的人员资质并进行分页后的信息", nickname = "QualifierCertificate_getAllByCurrentUser")
    @GetMapping("/getAllByCurrentUser/{id}")
    public Page<QualifierCertificate> getAllByDepartmentId(@ApiParam("人员id") @PathVariable Long id, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable)
    {
        Page<QualifierCertificate> qualifierCertificates = qualifierCertificateService.getAllByCurrentUser(id,pageable);
        return qualifierCertificates;
    }

}
