package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.Qualifier;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/7/18.
 * 人员 接口
 */
public interface QualifierService {
//    通过部门类型id获取该部门下全部的人员信息
    Page<Qualifier> getAllByDepartmentId(Long departmentId, Pageable pageable);

//    将人员添加到当前登录用户所在的部门中
    Qualifier addQualifierOfCurrentLoginUserDepartment(Qualifier qualifier);

//    更新当前部门的人员信息
    Qualifier updateQualifierOfCurrentLoginUserDepartment(Long id, Qualifier qualifier) throws ObjectNotFoundException, SecurityException;

//    删除人员
    void delete(Long id) throws SecurityException;

    //根据当前登录用户所在部门获取该部门所有人员id
    List<Qualifier> getAllByCurrentLoginUserDepartment();

//    根据当前登录用户获取其所在部门全部人员并分页
    Page<Qualifier> getAllByCurrentLoginUser(Pageable pageable);

    //通过查询条件及当前登录用户获取其所在部门全部人员信息并分页
    Page<Qualifier> pageAllOfCurrentUserBySpecification(Long districtId, String name, String departmentName, Pageable pageable);

}
