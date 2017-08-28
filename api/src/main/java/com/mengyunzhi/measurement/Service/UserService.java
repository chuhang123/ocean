package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.User;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by panjie on 17/5/18.
 * 用户
 */
public interface UserService {
    Principal getCurrentLoginPrincipalUser();
    User getCurrentLoginUser();

    //获取所有用户
    List<User> getAll();

    //获取一个用户
    User get(Long id);

    //保存用户
    User save(User user);

    //更新用户
    void update(Long id, User user);

    //删除
    void delete(Long id);

    // 获取当前用户的前台菜单列表
    List<WebAppMenu> getCurrentUserWebAppMenus();

    // 设置当前登录用户（用于单元测试）
    void setCurrentLoginUser(User user);

    // 检测用户是否存在
    boolean checkUsernameIsExist(String username);

    // 通过ID设置用户的状态为正常
    void setUserStatusToNormalById(Long id) throws ObjectNotFoundException;

    // 设置某个用户的状态为正常
    void setUserStatusToNormal(User user);

    // 设置某个用户的状态
    void setUserStatus(User user, int status);

    // 用户注册
    void register(Department department, User user);

    // 获取进行分页以后的用户信息
    Page<User> getAllUser(Pageable pageable);

    static User getOneUser() {
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(20));
        user.setPassword(CommonService.getRandomStringByLength(40));
        user.setName("测试姓名");
        user.setStatus(0);
        return user;
    }

    User loginWithOneUser();
    // 清空当前登录的测试用户
    void clearCurrentTestLoginUser();
    // 更新用户的密码和姓名
    void updatePasswordAndNameOfCurrentUser(User user) throws SecurityException;
    //重置密码
    void resetPasswordById(Long id) throws ObjectNotFoundException;
    //冻结用户
    void freezeById(Long id)  throws ObjectNotFoundException;
    Page<User> pageAllByMapAndPageable(Map<String, Object> map, Pageable pageable);

    /**
     * 获取综合查询的分页信息
     * @param districtId 区域
     * @param departmentTypeId 部门类型
     * @param status    状态
     * @param departmentName 部门名称
     * @return
     */
    Page<User> pageAllBySpecification(Long districtId, Long departmentTypeId, Integer status, String departmentName, Pageable pageable);

    // 验证密码是否正确
    Boolean checkPasswordIsRight(User user);
}
