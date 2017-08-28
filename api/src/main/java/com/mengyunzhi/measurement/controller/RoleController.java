package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.RoleService;
import com.mengyunzhi.measurement.jsonView.RoleJsonView;
import com.mengyunzhi.measurement.repository.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 角色 控制器
 */
@Api(tags = "Role (角色)", description = "角色")
@RestController
@RequestMapping("/Role")
public class RoleController {
    private static Logger logger = Logger.getLogger(RoleController.class.getName());
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "getAll (获取所有角色)", notes = "获取所有角色", nickname = "Role_getAll")
    @JsonView(RoleJsonView.GetAll.class)
    @GetMapping("/getAll")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @ApiOperation(value = "get (获取一个角色)", notes = "获取一个角色", nickname = "Role_get")
    @GetMapping(value = "/get/{id}")
    public Role get(@ApiParam(value = "角色ID") @PathVariable Long id){
        return roleService.get(id);
    }

    @ApiOperation(value = "save (保存)", notes = "保存角色", nickname = "Role_save")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Role save(@ApiParam(value = "角色实体") @RequestBody Role role) {
        return roleService.save(role);
    }

    @ApiOperation(value = "/ 更新", notes = "更新角色", nickname = "Role_update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Role update(@ApiParam(value = "角色实体") @RequestBody Role role, @ApiParam("实体ID") @PathVariable Long id) {
        return roleService.update(id, role);
    }
}
