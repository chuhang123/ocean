package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.QualifierSpecs;
import net.sf.json.JSONObject;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by zhangjiahao on 2017/7/18.
 * 人员 M层实现
 */
@Service
public class QualifierServiceImpl implements QualifierService {
    private static Logger logger = Logger.getLogger(QualifierServiceImpl.class.getName());
    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public Page<Qualifier> getAllByDepartmentId(Long departmentId, Pageable pageable) {
        Page<Qualifier> qualifiers = qualifierRepository.findAllByDepartmentId(departmentId, pageable);
        return qualifiers;
    }

    @Override
    public Qualifier addQualifierOfCurrentLoginUserDepartment(Qualifier qualifier) {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        qualifier.setDepartment(department);
        qualifierRepository.save(qualifier);
        return qualifier;
    }

    @Override
    public Qualifier updateQualifierOfCurrentLoginUserDepartment(Long id, Qualifier qualifier) throws ObjectNotFoundException, SecurityException{
        logger.info("查找要更新的实体是否存在");
        Qualifier qualifier1 = qualifierRepository.findOne(id);
        if (qualifier1 == null)
        {
            throw new ObjectNotFoundException(404, "要修改的实体不存在");
        }

        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        if (user.getDepartment().getId() == qualifier.getDepartment().getId())
        {
            qualifier.setId(id);
            qualifierRepository.save(qualifier);
            return qualifier;
        }
        else
        {
            throw new SecurityException("您无此操作权限");
        }
    }

    @Override
    public void delete(Long id) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        Qualifier qualifier = qualifierRepository.findOne(id);
        //判断部门要删除的人员对应的部门id与当前登录用户的部门id是否相等
        if (department.getId() == qualifier.getDepartment().getId())
        {
            qualifierRepository.delete(id);
        }
        else{
            throw new SecurityException("很抱歉您没有删除该人员的权限");
        }
        return;
    }

    @Override
    public List<Qualifier> getAllByCurrentLoginUserDepartment() {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        List<Qualifier> qualifiers = qualifierRepository.findAllByDepartment(department);
        return qualifiers;
    }

    @Override
    public Page<Qualifier> getAllByCurrentLoginUser(Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        Page<Qualifier> qualifiers = qualifierRepository.findAllByDepartment(department, pageable);
        return qualifiers;
    }

    @Override
    public Page<Qualifier> pageAllOfCurrentUserBySpecification(Long districtId, String name, String departmentName, Pageable pageable) {
        // 判断用户是否使用高级搜索,如果用户未使用高级搜索，则直接返回当前部门的用户
        if (districtId == 0 && name.equals("") && departmentName.equals("")) {
            this.getAllByCurrentLoginUser(pageable);
        }

        // 用户使用了高级搜索，并按条件进行查询
        districtId = districtId == 0 ? null : districtId;

        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = new ArrayList<>();
        logger.info("判断用户是否查询区域");
        if (null != districtId) {
            logger.info("对区域进行权限判端");
            districts = districtService.getSonsListByDistrictId(districtId);
            Boolean found = false;
            for (int i = 0; i < districts.size() && !found; i++) {
                if (districts.get(i).getId() == districtId) {
                    found = true;
                }
            }
            if (false == found) {
                throw new SecurityException("对不起，您只能查看本区域内的信息");
            }
            districts = districtService.getSonsListByDistrictId(districtId);
        }
        logger.info(districtId + "fdsfsd");
        logger.info("chizuclsjf" + districts.size());
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("districts", districts);
        map.put("departmentName", departmentName);
        Specification specification = QualifierSpecs.base(map);
        Page<Qualifier> qualifiers = qualifierRepository.findAll(specification, pageable);
        return qualifiers;
    }
}
