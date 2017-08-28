package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.jsonView.UserJsonView;
import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.User;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 * Created by panjie on 17/5/17.
 *
 */
@Api(tags = "User 用户", description = "用户管理")
@RequestMapping("/User")
@RestController
public class UserController {
    static Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "login 登录", nickname = "User_login",
            notes="用户登录成功后，将会得到一个header 信息。客户端存储该值后，下次发送时做为header中的XXX进行发送")
    public User login(Principal user) {
        log.info("-----------------登录成功----------------");
        log.info("登录用户:" + user.getName());

        return userService.getCurrentLoginUser();
    }

    @GetMapping("/logout")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "logout 注销", nickname = "User_logout",
            notes = "参考资料：http://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/web/authentication/logout/SecurityContextLogoutHandler.html")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("-----------------注销----------------");
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 如果存在认证信息则调用注销操作
        if (null != authentication) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        return ;
    }

    @GetMapping("/getAll")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "getAll 获取所有用户",nickname = "User_getAll",notes = "获取所有用户")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/pageAllBySpecification")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "pageAllBySpecification", notes = "综合查询的分页信息", nickname = "User_pageAllBySpecification")
    public Page<User> pageAllBySpecification (
            @ApiParam("区域ID") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam("部门类型id") @RequestParam(name = "departmentTypeId", required = false) Long departmentTypeId,
            @ApiParam("用户状态") @RequestParam(name = "status", required = false) Integer status,
            @ApiParam("用户所在部门名称") @RequestParam(name = "departmentName", required = false) String departmentName,
            @ApiParam("分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.pageAllBySpecification(districtId, departmentTypeId, status, departmentName, pageable);
    }

    @PostMapping("/save")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "save 保存用户",nickname = "User_save",notes = "保存用户")
    public User save(@ApiParam(value = "用户") @RequestBody User user){
        userService.save(user);
        return user;
    }

    @GetMapping("/get/{id}")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "get 获取一个用户",nickname = "User_get",notes = "获取一个用户")
    public User get(@ApiParam(value = "实体id") @PathVariable Long id){
        return userService.get(id);
    }

    @ApiOperation(value = "update (修改)", notes = "修改某条数据", nickname = "User_update")
    @ApiResponse(code = 204, message = "更新成功")
    @PutMapping(value = "/update/{id}")
    @JsonView(UserJsonView.GetAll.class)
    public void update(@ApiParam(value = "实体ID") @PathVariable Long id, @ApiParam(value = "用户实体") @RequestBody User user, HttpServletResponse response) {
        log.info("---- 更新实体 -----");
        log.info("在数据更新中，可以直接调用M层的save方法.");
        try {
            userService.update(id, user);
            response.setStatus(204);
        } catch (ObjectNotFoundException e) {
            response.setStatus(404);
        }

        return;
    }

    @DeleteMapping("/delete/{id}")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "delete 保存用户",nickname = "User_delete",notes = "删除用户")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //删除成功返回状态码204
    public void delete(@ApiParam(value = "用户") @PathVariable Long id,HttpServletResponse response){
        log.info("---- 删除实体 -----");
        log.info("在数据删除中，可以直接调用M层的delete方法.");
        userService.delete(id);
        return;
    }

    @ApiOperation(value = "getAllPage 获取全部的user信息并进行分页", notes = "进行数据分页", nickname = "User_PageAll")
    @GetMapping("/getAllPage")
    @JsonView(UserJsonView.GetAll.class)
    public Page<User> getAllPage(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable)
    {
        Page<User> users = userService.getAllUser(pageable);
        return users;
    }

    @GetMapping("/getCurrentLoginUser")
    @ApiModelProperty(value = "获取当前登录用户信息")
    @JsonView(UserJsonView.GetAll.class)
    public User getCurrentLoginUser() {
        return userService.getCurrentLoginUser();
    }

    @GetMapping("/getCurrentUserWebAppMenus/")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "getCurrentUserWebAppMenus 获取当前用户的菜单权限列表", nickname = "User_getCurrentUserWebAppMenus", notes = "查询前台菜单权限")
    public List<WebAppMenu> getCutUserWebAppMenus() {
        return userService.getCurrentUserWebAppMenus();
    }

    @GetMapping("/checkUsernameIsExist/{username}")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "checkUsernameIsExist 检测用户名是否已经存在", nickname = "User_checkUsernameIsExist", notes = "存在：true,不存在:false @author:panjie")
    public boolean checkUsernameIsExist(@ApiParam(value = "用户名（邮箱），必须使用邮箱作为用户名") @PathVariable String username) {
        return userService.checkUsernameIsExist(username);
    }

    @GetMapping("/setUserStatusToNormalById/{id}")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "setUserStatusToNormalById 设置某个用户的状态为正常", notes = "author:panjie", nickname = "User_setUserStatusToNormalById")
    public void setUserStatusToNormalById(@ApiParam(value = "用户id") @PathVariable Long id) {
        userService.setUserStatusToNormalById(id);
        return;
    }



    @PostMapping("/register")
    @JsonView(UserJsonView.GetAll.class)
    @ApiOperation(value = "register 注册后户", notes = "用户注册时，同时注册部门及用户信息", nickname = "User_register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({@ApiResponse(code = 201, message = "操作成功"), @ApiResponse(code = 404, message = "数据绑定失败,请检查数据格式")} )
    public void register(@ApiParam(value = "用户注册信息：部门信息+用户信息") @RequestBody DepartmentUser departmentUser) {
        userService.register(departmentUser.getDepartment(), departmentUser.getUser());
        return;
    }

    @ApiOperation(value = "updatePasswordAndNameOfCurrentUser (修改当前用户的密码和姓名)", notes = "修改当前用户的密码和姓名", nickname = "User_updatePasswordAndNameOfCurrentUser")
    @PutMapping(value = "/updatePasswordAndNameOfCurrentUser/")
    @JsonView(UserJsonView.GetAll.class)
    public void updatePasswordAndNameOfCurrentUser(@ApiParam(value = "用户实体") @RequestBody User user,
                                      HttpServletResponse response) {
        log.info("更新用户的密码和姓名");
        try {
            userService.updatePasswordAndNameOfCurrentUser(user);
            response.setStatus(204);
        } catch (SecurityException e) {
            response.setStatus(205);
        }

        return;
    }


    @ApiOperation(value = "resetPasswordById (重置密码)", notes = "重置密码", nickname = "User_resetPasswordById")
    @ApiResponse(code = 204, message = "更新成功")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/resetPasswordById/{id}")
    @JsonView(UserJsonView.GetAll.class)
    public void resetPasswordById(@ApiParam(value = "用户实体id") @PathVariable Long id) {
        userService.resetPasswordById(id);
        return;
    }
  
    @ApiOperation(value = "freezeById (冻结用户)", notes = "冻结用户", nickname = "User_freezeById")
    @ApiResponse(code = 204, message = "更新成功")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/freezeById/{id}")
    @JsonView(UserJsonView.GetAll.class)
    public void freezeById(@ApiParam(value = "用户实体id") @PathVariable Long id) {
        userService.freezeById(id);
        return;
    }

    @ApiOperation(value = "checkPasswordIsRight (验证密码是否正确)", notes = "验证密码是否正确", nickname = "User_checkPasswordIsRight")
    @PostMapping(value = "/checkPasswordIsRight")
    public Boolean checkPasswordIsRight(@ApiParam(value = "用户密码") @RequestBody User user) {
        return userService.checkPasswordIsRight(user);
    }

    @ApiModel(value = "DepartmentUser 部门+用户")
    public static class DepartmentUser {
        @ApiModelProperty("部门")
        private Department department;
        @ApiModelProperty("用户")
        private User user;

        public DepartmentUser() {
        }


        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

}

