package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.DeviceSetSpecs;
import net.sf.json.JSONObject;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
@Service
public class DeviceSetServiceImpl implements DeviceSetService {
    static private Logger logger = Logger.getLogger(DeviceSetServiceImpl.class.getName());
    @Autowired
    protected DeviceSetRepository deviceSetRepository;        // 计量标准装置
    @Autowired
    protected UserService userService;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected DistrictService districtService;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    protected DeviceSetService deviceSetService;

    @Override
    public DeviceSet save(DeviceSet deviceSet) {
        //获取当前登录用户
        User user = userService.getCurrentLoginUser();
        //获取当前登录用户的部门
        Department department = user.getDepartment();
        //为计量标准装置设置技术机构部门
        deviceSet.setDepartment(department);
        return deviceSetRepository.save(deviceSet);
    }

    @Override
    public void delete(DeviceSet deviceSet) {
        deviceSetRepository.delete(deviceSet);
        return;
    }

    @Override
    public DeviceSet updateDeviceInstrumentsById(Long id, DeviceSet deviceSet) {
        deviceSet.setId(id);
        return deviceSetRepository.save(deviceSet);
    }

    @Override
    public Page<DeviceSet> getAll(Pageable pageable) {
        Page<DeviceSet> page = deviceSetRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<DeviceSet> getAllDeviceSetByTechnicalInstitutionId(Long technicallnstitutionId) {
        List<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartmentId(technicallnstitutionId);
        return deviceSets;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentUser(Pageable pageable) {
        //获取当前登陆用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前登陆用的部门
        Department department = currentUser.getDepartment();

        Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartment(department, pageable);
//        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
//
//        if (departmentTypeName == "技术机构") {
//            logger.info("当前的登录用户为技术机构，获取次技术机构的计量标准装置");
//            Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartment(department, pageable);
//            return deviceSets;
//        } else {
//            logger.info("当前登录用户为管理部门，获取管理部门内的所有技术机构的计量标准装置");
//            return deviceSetService.pageAllOfCurrentManagementDepartmentUser(currentUser, pageable);
//        }

        return deviceSets;

    }

    @Override
    public DeviceSet getOneDeviceSet() {
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("测试计量标准装置" + CommonService.getRandomStringByLength(10));
        return deviceSetRepository.save(deviceSet);
    }

    @Override
    public DeviceSet get(Long id) {
        DeviceSet deviceSet = deviceSetRepository.findOne(id);
        return deviceSet;
    }

    @Override
    public void delete(Long id) {
        //获取当前登录用户
        User user = userService.getCurrentLoginUser();
        DeviceSet deviceSet = deviceSetRepository.findOne(id);
        //判断当前登录用户是否和此标准器的用户相等
        if (user.getDepartment().getId() == deviceSet.getDepartment().getId()) {
            logger.info("有权限删除");
            deviceSetRepository.delete(deviceSet);
        } else {
            logger.info("没有权限删除");
            throw new ObjectNotFoundException(401, "您没有权限删除");
        }

        return;
    }

    @Override
    public void update(Long id, DeviceSet deviceSet) {
        DeviceSet deviceSet1 = deviceSetRepository.findOne(id);
        if (deviceSet1 == null) {
            throw new ObjectNotFoundException(404, "未找到ID为" + id.toString() + "的计量标准装置实体");
        } else {
            deviceSet.setId(id);
            deviceSetRepository.save(deviceSet);
        }
        return;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentManagementDepartmentUser(User currentUser, Pageable pageable) {
        logger.info("获取当前登录用户所属于的区域");
        District district = currentUser.getDepartment().getDistrict();
        logger.info("获取这个区域及所有子区域");
        List<District> districts = districtService.getSonsListByDistrict(district);
        logger.info("获取所有的计量标准装置");
        Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDisctricts(districts, pageable);
        return deviceSets;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentUserBySpecification(Long districtId, Long departmentId, String name, String code, Pageable pageable) {
        //获取当前登录用户的department部门ID，name和代码
//        Department department = userService.getCurrentLoginUser().getDepartment();

        Page<DeviceSet> deviceSets = null;

        if (districtId == 0 && departmentId == 0 && name == "" && code == "") {
            //没有任何查询条件，默认获取当前登录用户的所有标准装置
            deviceSets = deviceSetService.pageAllOfCurrentUser(pageable);
        } else {

            //整理查询条件
            districtId = districtId == 0 ? null : districtId;
            departmentId = departmentId == 0 ? null : departmentId;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", code);
            jsonObject.put("districtId", districtId);
            jsonObject.put("departmentId", departmentId);
            jsonObject.put("name", name);

            org.springframework.data.jpa.domain.Specification specification = DeviceSetSpecs.base(jsonObject);
            deviceSets = deviceSetRepository.findAll(specification, pageable);
        }


        return deviceSets;
    }
}
