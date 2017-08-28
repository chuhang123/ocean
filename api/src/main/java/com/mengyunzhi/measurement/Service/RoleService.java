package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Role;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 角色 M层
 */
public interface RoleService {
    //保存方法
    Role save(Role role);

    //获取index界面数据
    List<Role> getAll();

    //获取当前id的角色
    Role get(Long id);

    // 更新
    Role update(Long id, Role role) throws EntityNotFoundException;
}
