package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjiahao on 2017/7/6.
 * 部门
 */
@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
    private Logger logger = Logger.getLogger(DepartmentServiceImpl.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private UserService userService;
    @Autowired private WorkflowNodeRepository workflowNodeRepository;   // 工作流结点
    @Autowired private MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具
    @Autowired private DeviceSetRepository deviceSetRepository;             // 检定装置
    @Autowired private DepartmentTypeRepository departmentTypeRepository;                       // 部门类型

    @Override
    public List<Department> getTop10ByDepartmentTypeIdAndNameContaining(Long DepartmentTyTypeId, String name) {
        List<Department> departments = departmentRepository.findTop10ByDepartmentTypeIdAndNameContaining(DepartmentTyTypeId, name);
        return departments;
    }
    @Override
    public List<Department> getAllByDistrictAndDepartmentTypeIncludeSons(District district, DepartmentType departmentType) {
        // 获取区域及子区域信息
        List<District> districts = districtService.getSonsListByDistrict(district);
        //获取部门类型
        List<Department> departments = new ArrayList<>();
        for (District newDistrict: districts) {
            List<Department> newDepartments = departmentRepository.findAllByDistrictAndDepartmentType(newDistrict, departmentType);
            departments.addAll(newDepartments);
        }
        return departments;
    }
    @Override
    public List<Department> getAllByDistrictAndDepartmentType(District district, DepartmentType departmentType) {
        List<Department> departments = departmentRepository.findAllByDistrictAndDepartmentType(district, departmentType);
        return departments;
    }

    @Override
    public Department getOneCompleteDepartment() {
        Department department = DepartmentService.getOneDepartment();
        department.setName("测试部门" + CommonService.getRandomStringByLength(10));
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getAllByWorkflowNodeIdOfCurrentLoginUser(Long workflowNodeId) throws ObjectNotFoundException{
        WorkflowNode workflowNode = workflowNodeRepository.findOne(workflowNodeId);
        if (null == workflowNode) {
            throw new ObjectNotFoundException("404", "WorkflowNode");
        }
        return this.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);
    }

    @Override
    public List<Department> getAllByWorkflowNodeAndUser(WorkflowNode workflowNode, User user) {
        logger.info("查看当前用户的区域是否与结点区域相同");
        District district = this.getDistrictByWorkflowNodeAndDistrict(workflowNode, user.getDepartment().getDistrict());
        if (null == district) {
            logger.info("当前用户所在区域是工作流节点上设置的父区域，则以当前用户所有区域为准");
            district = user.getDepartment().getDistrict();
        }
        List<Department> departments = new ArrayList<>();
        if (workflowNode.isContainSonDistrict()) {
            logger.info("包含子区域，则取出自已以及所有子区域中符合要求的部门");
            departments = this.getAllByDistrictAndDepartmentTypeIncludeSons(district, workflowNode.getDepartmentType());
        } else {
            logger.info("不包含子区域，则只取出本区域符合要求的部门");
            departments = this.getAllByDistrictAndDepartmentType(district, workflowNode.getDepartmentType());
        }
        return departments;
    }

    public District getDistrictByWorkflowNodeAndDistrict(WorkflowNode workflowNode, District district) {
        logger.info("判断当前用户区域类型是否符合要求");
        if (null == district) {
            return district;
        } else if (district.getDistrictType().getId() == workflowNode.getDistrictType().getId()) {
            return district;
        } else {
            return this.getDistrictByWorkflowNodeAndDistrict(workflowNode, district.getParentDistrict());
        }
    }
    @Override
    public List<Department> getAllByWorkflowNodeOfCurrentLoginUser(WorkflowNode workflowNode) {
        User user = userService.getCurrentLoginUser();
        return this.getAllByWorkflowNodeAndUser(workflowNode,user);
    }

    @Override
    public List<Department> getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(Long workflowNodeId, Long mandatoryInstrumentId) throws ObjectNotFoundException {
        WorkflowNode workflowNode = workflowNodeRepository.findOne(workflowNodeId);
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(mandatoryInstrumentId);
        if (workflowNode == null) {
            throw new ObjectNotFoundException(404, "id为" + workflowNodeId.toString() + "的工作流实体未找到");
        } else if (mandatoryInstrument == null) {
            throw new ObjectNotFoundException(404, "id为" + mandatoryInstrumentId.toString() + "的实体未找到");
        }
        return this.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(workflowNode, mandatoryInstrument);
    }

    @Override
    public List<Department> getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(WorkflowNode workflowNode, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException {
        logger.info("获取所有的符合申请流程的列表");
        List<Department> departments = this.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);

        logger.info("构造一个授权检定项目");
        DeviceInstrument deviceInstrument = mandatoryInstrument.getDeviceInstrument();

        for (Department department: departments) {
            int count = deviceSetRepository.countByDepartmentAndDeviceInstrument(department, deviceInstrument);
            if (count > 0) {
                department.setCheckAbility(true);
            }
        }
        return departments;
    }

    @Override
    public List<Department> getAllTechnicalInstitutionsByDistrictId(Long districtId) {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        return departmentRepository.findAllByDistrictIdAndDepartmentType(districtId, departmentType);
    }

    @Override
    public Department getOneTechnicalInstitutionDepartment() {
        Department department = this.getOneCompleteDepartment();
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        department.setDepartmentType(departmentType);
        return departmentRepository.save(department);
    }

    @Override
    public Page<Department> pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(String departmentTypePinyin, Pageable pageable) throws SecurityException{
        logger.info("获取当前用户，并检定是否是管理部门");
        User user = userService.getCurrentLoginUser();
        DepartmentType departmentType1 = departmentTypeRepository.findOneByName("管理部门");
        if (user.getDepartment().getDepartmentType().getId() != departmentType1.getId()) {
            throw new SecurityException("您不是管理部门，没有此项权限");
        }

        logger.info("按拼音取出部门类型");
        DepartmentType departmentType = departmentTypeRepository.findOneByPinyin(departmentTypePinyin);
        if (departmentType == null) {
            throw new ObjectNotFoundException(404, "传入的部门类型拼间未找到对应的实体");
        }
        logger.info("取出当前用户所在部门的所辖区域");
        List<District> districts = districtService.getSonsListByDistrict(user.getDepartment().getDistrict());

        logger.info("调用实体仓库方法，返回值");
        Page<Department> departments = departmentRepository.findAllByDistrictsAndDepartmentType(districts, departmentType, pageable);
        return departments;
    }

    @Override
    public void delete(Long id) {
        logger.info("删除实体");
        departmentRepository.delete(id);
        return;
    }

    @Override
    public List<Department> getAllInstrumentUserByDistrictId(Long districtId) {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("器具用户");
        return departmentRepository.findAllByDistrictIdAndDepartmentType(districtId, departmentType);
    }

}
