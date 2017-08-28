package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Role;
import com.mengyunzhi.measurement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 角色 M层实现
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        roleRepository.save(role);
        return role;

    }

    @Override
    public List<Role> getAll() {
        List<Role> list = new ArrayList<Role>();
        list = (List<Role>) roleRepository.findAll();
        return list;
    }

    @Override
    //获取传来id的角色
    public Role get(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role update(Long id, Role role) throws EntityNotFoundException{
        Role oldRole = roleRepository.findOne(id);
        if (null == oldRole) {
            throw new EntityNotFoundException();
        } else {
            role.setId(id);
            roleRepository.save(role);
        }

        return role;
    }
}
