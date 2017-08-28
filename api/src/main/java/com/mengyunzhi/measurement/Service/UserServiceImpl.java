package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.UserSpecs;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panjie on 17/5/18.
 * 用户
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository; // 部门

    @Autowired
    private PostRepository postRepository; // 岗位
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired
    private DistrictTypeRepository districtTypeRepository;   // 区域类型
    @Autowired
    private DistrictRepository districtRepository;           // 区域
    @Autowired
    private WebAppMenuRepository webAppMenuRepository;    // 前台菜单
    @Autowired
    private DistrictService districtService;     // 区域

    @ApiModelProperty("当前登录认证用户")
    private Principal currentLoginPrincipalUser;

    @ApiModelProperty("当前登录用户")
    private User currentTestLoginUser;

    @Override
    @ApiOperation("获取当前登录的认证用户")
    public Principal getCurrentLoginPrincipalUser() {
        currentLoginPrincipalUser = SecurityContextHolder.getContext().getAuthentication();
        return currentLoginPrincipalUser;
    }

    @Override
    @ApiOperation("获取当前登录的用户")
    public User getCurrentLoginUser() {
        if (null != this.currentTestLoginUser) {
            return this.currentTestLoginUser;
        } else {
            String username = getCurrentLoginPrincipalUser().getName();
            User currentLoginUser = userRepository.findOneByUsername(username);
            return currentLoginUser;
        }
    }


    @Override
    @ApiOperation("获取所有用户")
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        list = (List<User>) userRepository.findAll();
        return list;
    }

    @Override
    @ApiOperation("获取所有用户")
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @ApiOperation("保存用户")
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @ApiOperation("更新用户")
    public void update(Long id, User user) {
        logger.info("查找要更新的实体是否存在");
        User oldUser = userRepository.findOne(id);
        if (oldUser == null) {
            throw new ObjectNotFoundException(404, "相关记录已删除或未找到");
        }
        logger.info("更新角色、状态、岗位信息");
        oldUser.setRoles(user.getRoles());
        oldUser.setStatus(user.getStatus());
        oldUser.setPosts(user.getPosts());

        userRepository.save(oldUser);
        return;

    }

    @Override
    @ApiOperation("删除用户")
    public void delete(Long id) {
        logger.info("查找要更新的实体是否存在");
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ObjectNotFoundException(404, "要删除的实体不存在");
        } else {
            userRepository.delete(id);
        }
    }

    @Override
    public List<WebAppMenu> getCurrentUserWebAppMenus() {
        List<WebAppMenu> webAppMenus = webAppMenuRepository.findAllByUserId(this.getCurrentLoginUser().getId());
        return webAppMenus;
    }

    @Override
    public void setCurrentLoginUser(User user) {
        this.currentTestLoginUser = user;
    }

    /**
     * 检测用户是否已存在
     *
     * @param username 用户名
     * @return
     * @author: panjie
     */
    @Override
    public boolean checkUsernameIsExist(String username) {
        User user = userRepository.findOneByUsername(username);
        if (null == user) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 设置某个用户ID的状态为正常
     *
     * @param id 用户id
     * @author: panjie
     */
    @Override
    public void setUserStatusToNormalById(Long id) throws ObjectNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ObjectNotFoundException(404, "不存在id为" + id.toString() + "的用户信息");
        }
        this.setUserStatusToNormal(user);
        return;
    }

    /**
     * 设置用户状态为正常
     *
     * @param user 用户
     * @author panjie
     */
    @Override
    public void setUserStatusToNormal(User user) {
        this.setUserStatus(user, 0);
        return;
    }

    /**
     * 设置用户的状态
     *
     * @param user   用户
     * @param status 状态: -1 未审核； 0 正常； 1 冻结; 2 其它
     * @author panjie
     */
    public void setUserStatus(User user, int status) {
        user.setStatus(status);
        userRepository.save(user);
        return;
    }

    /**
     * 用户注册
     *
     * @param department 部门
     * @param user       用户
     * @author panjie
     */
    @Override
    public void register(Department department, User user) {
        logger.info("保存部门");
        departmentRepository.save(department);

        logger.info("保存用户，对为其设置刚刚保存的部门及初始岗位");
        user.setDepartment(department);
        userRepository.save(user);
    }

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @Override
    public User loginWithOneUser() {
        logger.info("新建区域类型");
        DistrictType districtType = DistrictTypeService.getOneDistrictType();
        districtTypeRepository.save(districtType);

        logger.info("新建区域");
        District district = districtService.getOneDistrict();
        district.setDistrictType(districtType);
        districtRepository.save(district);

        logger.info("新建部门类型及部门");
        DepartmentType departmentType = DepartmentTypeService.getOneDepartmentType();
        departmentTypeRepository.save(departmentType);
        Department department = DepartmentService.getOneDepartment();

        logger.info("设置部门类型，部门所在区域");
        department.setDepartmentType(departmentType);
        department.setDistrict(district);
        departmentRepository.save(department);

        logger.info("新建用户，并设置部门");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);
        this.setCurrentLoginUser(user);
        return user;
    }

    @Override
    public void clearCurrentTestLoginUser() {
        this.currentTestLoginUser = null;
    }

    @Override
    public void updatePasswordAndNameOfCurrentUser(User user) {
        logger.info("获取当前用户");
        User currentUser = this.getCurrentLoginUser();

        if (!currentUser.getPassword().equals(user.getPassword())) {
            logger.info("原密码错误");
            throw new SecurityException("原密码错误");
        }

        logger.info("更新用户密码和名称");
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getRePassword());
        userRepository.save(currentUser);

        return;
    }

    @Override
    public void resetPasswordById(Long id) {
        logger.info("获取当前用户");
        User user = userRepository.findOne(id);
        if (null == user) {
            throw new ObjectNotFoundException(404, "不存在此用户");
        }

        logger.info("重置密码");
        user.setPassword("111111");
        userRepository.save(user);

        return;
    }

    @Override
    public void freezeById(Long id) {
        logger.info("查找用户");
        User user = userRepository.findOne(id);
        if (null == user) {
            logger.info("不存在此用户");
            throw new ObjectNotFoundException(404, "不存在此用户");
        }

        logger.info("设置用户的状态");
        this.setUserStatus(user, 1);
        return;
    }

    @Override
    public Page<User> pageAllByMapAndPageable(Map<String, Object> map, Pageable pageable) {
        Specification specification = UserSpecs.base(map);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public Page<User> pageAllBySpecification(Long districtId, Long departmentTypeId, Integer status, String departmentName, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        logger.info("传入了区域ID");
        if (null != districtId) {
            List<District> districts = districtService.getSonsListByDistrictId(districtId);
            map.put("districts", districts);
        }

        map.put("departmentTypeId", departmentTypeId);
        map.put("status", status);
        map.put("departmentName", departmentName);
        return this.pageAllByMapAndPageable(map, pageable);
    }

    @Override
    public Boolean checkPasswordIsRight(User user) {
        logger.info("获取当前用户");
        User currentUser = this.getCurrentLoginUser();

        logger.info("验证密码是否正确");
        if (currentUser.getPassword().equals(user.getPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}