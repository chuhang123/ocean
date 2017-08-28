package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.QualifierService;

import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.Qualifier;
import com.mengyunzhi.measurement.repository.QualifierCertificate;
import com.mengyunzhi.measurement.repository.QualifierRepository;

import io.swagger.annotations.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/18.
 * 人员 C层
 */
@Api(tags = "Qualifier (人员)", description = "人员实体")
@RestController
@RequestMapping("/Qualifier")
public class QualifierController {
    private static Logger logger = Logger.getLogger(QualifierController.class.getName());

    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private QualifierService qualifierService;

    @ApiOperation(value = "获取所有数据", notes = "通过部门id获取该部门下对应的所有的人员并进行分页后的信息", nickname = "Qualifier_getAllPages")
    @GetMapping("/getAllByDepartmentId/{id}")
    public Page<Qualifier> getAllByDepartmentId(@ApiParam("部门id") @PathVariable Long id, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable)
    {
        Page<Qualifier> qualifiers = qualifierService.getAllByDepartmentId(id,pageable);
        return qualifiers;
    }

    @ApiOperation(value = "保存人员实体", notes = "本部门的用户在登录以后只可以保存本部门的人员", nickname = "Qualifier_addQualifierByCurrentLoginUserDepartment")
    @PostMapping("/addQualifierByCurrentLoginUserDepartment")
    @ResponseStatus(HttpStatus.CREATED)
    public Qualifier addQualifierByCurrentLoginUserDepartment(@RequestBody Qualifier qualifier)
    {
        logger.info("保存人员实体");
        Qualifier qualifier1 = qualifierService.addQualifierOfCurrentLoginUserDepartment(qualifier);
        return qualifier1;
    }

    @ApiOperation(value = "updateQualifierOfCurrentLoginUserDepartment", notes = "本部门登录用户修改本部门某个人员信息", nickname = "Qualifier_updateQualifierOfCurrentLoginUserDepartment")
    @ApiResponse(code = 204, message = "更新成功")
    @PutMapping(value = "/updateQualifierOfCurrentLoginUserDepartment/{id}")
    public void updateQualifierOfCurrentLoginUserDepartment(@ApiParam(value = "实体ID") @PathVariable Long id, @ApiParam(value = "人员实体") @RequestBody Qualifier qualifier, HttpServletResponse response)
    {
        logger.info("---- 更新实体 ----");
        logger.info("调用M层方法");
        try{
            qualifierService.updateQualifierOfCurrentLoginUserDepartment(id,qualifier);
            response.setStatus(204);
        } catch (ObjectNotFoundException e) {
            response.setStatus(404);
        }

        return;
    }


    @ApiOperation(value = "delete",notes = "删除当前登录用户对应的部门的某个人员信息", nickname = "Qualifier_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        qualifierService.delete(id);
        return;
    }

    @ApiOperation(value = "getAllByCurrentLoginUserDepartment", notes = "通过当前登录用户所在部门获取该部门所有人员信息", nickname = "Qualifier_getAllByCurrentLoginUserDepartment")
    @GetMapping("/getAllByCurrentLoginUserDepartment")
    public List<Qualifier> getAllByCurrentLoginUserDepartment()
    {
        return qualifierService.getAllByCurrentLoginUserDepartment();
    }

    @ApiOperation(value = "getAllByCurrentLoginUser", notes = "通过当前登录用户获取其所在部门全部人员信息并分页", nickname = "Qualifier_getAllByCurrentLoginUser")
    @GetMapping("/getAllByCurrentLoginUser")
    public Page<Qualifier> getAllByCurrentLoginUser(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable)
    {
        return qualifierService.getAllByCurrentLoginUser(pageable);
    }

    @ApiOperation(value = "pageAllOfCurrentUserBySpecification", notes = "通过查询条件及当前登录用户获取其所在部门全部人员信息并分页", nickname = "Qualifier_pageAllOfCurrentUserBySpecification")
    @GetMapping("/pageAllOfCurrentUserBySpecification")
    public Page<Qualifier> pageAllOfCurrentUserBySpecification(
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false, defaultValue = "0") Long districtId,
            @ApiParam(value = "人员资质名称") @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @ApiParam(value = "技术机构名称") @RequestParam(name = "departmentName", required = false, defaultValue = "") String departmentName,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return qualifierService.pageAllOfCurrentUserBySpecification(districtId, name, departmentName, pageable);
    }
}
