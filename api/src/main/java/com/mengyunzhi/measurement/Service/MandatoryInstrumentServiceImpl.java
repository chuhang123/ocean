package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.input.MandatoryInstrumentSaveInput;
import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.MandatoryInstrumentSpecs;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by liming on 17-7-6.
 * 实现器具使用信息的InstrumentEmploymentInfo
 */
@Service
public class MandatoryInstrumentServiceImpl implements MandatoryInstrumentService {
    private Logger logger = Logger.getLogger(MandatoryInstrumentServiceImpl.class.getName());
    @Autowired
    private InstrumentProductionRepository instrumentProductionRepository;
    @Autowired          //用户Service
    private UserService userService;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具
    @Autowired          //部门实体仓库
    private DepartmentRepository departmentRepository;
    @Autowired
    private ApplyTypeRepository applyTypeRepository;     // 申请类型
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;  // 强检审核申请信息
    @Autowired
    private WorkRepository workRepository;       // 工作
    @Autowired
    private DeviceSetDeviceInstrumentService deviceSetDeviceInstrumentService; // 授权检定项目
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    private DistrictService districtService;        //区域
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;  // 部门类型

    @Override
    public MandatoryInstrument saveWithInstrumentProduction(MandatoryInstrument mandatoryInstrument) {
        if (null == mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment().getId()) {
            logger.info("未传入部门, 则按名称进行查询");
            Department department = departmentRepository.findTopOneByName(mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment().getName());
            if (department == null) {
                logger.info("未找到相关的部门，则新建一个");
                departmentRepository.save(mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment());
            } else {
                logger.info("找到器具生产信息的相关部门信息");
                mandatoryInstrument.getInstrumentProduction().setManufacturerDepartment(department);
            }
        }

        logger.info("查询是否存在该器具生产信息");
        InstrumentProduction realInstrumentProduction = instrumentProductionRepository.findByInstrumentTypeAndAccuracyAndMeasureScaleAndSpecificationAndLicenseNumAndManufacturerDepartment(
                mandatoryInstrument.getInstrumentProduction().getInstrumentType(),
                mandatoryInstrument.getInstrumentProduction().getAccuracy(),
                mandatoryInstrument.getInstrumentProduction().getMeasureScale(),
                mandatoryInstrument.getInstrumentProduction().getSpecification(),
                mandatoryInstrument.getInstrumentProduction().getLicenseNum(),
                mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment()
        );

        if (null == realInstrumentProduction) {
            logger.info("不存在器具生产信息，则持久化一个");
            instrumentProductionRepository.save(mandatoryInstrument.getInstrumentProduction());
        } else {
            logger.info("找到相关的生产企业器具的种类");
            mandatoryInstrument.setInstrumentProduction(realInstrumentProduction);
        }

        //器具当前操作用户并作为外建
        User currentUser = userService.getCurrentLoginUser();
        mandatoryInstrument.setCreateUser(currentUser);
        //获取当前操作用户对应的部门，做为器具使用信息的部门的外键
        Department generativeDepartment = currentUser.getDepartment();
        mandatoryInstrument.setGenerativeDepartment(generativeDepartment);
        //保存相应的器具使用信息
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        //返回相应的实体
        return mandatoryInstrument;
    }

    @Override
    public MandatoryInstrument save(MandatoryInstrument mandatoryInstrument) {
        logger.info("添加创建用户以及器具所属部门");
        User user = userService.getCurrentLoginUser();
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());

        logger.info("添加器具制造单位");
        this.addGenerativeDepartmentIfNotFound(mandatoryInstrument);

        mandatoryInstrumentRepository.save(mandatoryInstrument);
        return mandatoryInstrument;
    }

    /**
     * 如果生产企业的实体不存在，那么就添加一个
     *
     * @param mandatoryInstrument
     */
    protected void addGenerativeDepartmentIfNotFound(MandatoryInstrument mandatoryInstrument) {
        if (null != mandatoryInstrument.getGenerativeDepartment()) {
            if (null == mandatoryInstrument.getGenerativeDepartment().getId()) {
                logger.info("未传入部门, 则按名称进行查询");
                String name = mandatoryInstrument.getGenerativeDepartment().getName();
                DepartmentType departmentType = departmentTypeRepository.findOneByPinyin("shengchanqiye");
                Department department = departmentRepository.findTopOneByNameAndDepartmentType(name, departmentType);
                if (department == null) {
                    logger.info("未找到相关的部门，则新建一个");
                    mandatoryInstrument.getGenerativeDepartment().setDepartmentType(departmentType);
                    departmentRepository.save(mandatoryInstrument.getGenerativeDepartment());
                } else {
                    logger.info("找到器具生产信息的相关部门信息");
                    mandatoryInstrument.setGenerativeDepartment(department);
                }
            }
        }
    }

    @Override
    @Transactional
    public MandatoryInstrumentSaveInput apply(MandatoryInstrumentSaveInput mandatoryInstrumentSaveInput) {
        logger.info("取当前登录用户");
        User user = userService.getCurrentLoginUser();

        logger.info("存强检器具信息");
        this.saveWithInstrumentProduction(mandatoryInstrumentSaveInput.getMandatoryInstrument());

        logger.info("取申请类型信息");
        ApplyType applyType = applyTypeRepository.findOneByName("区\\县器具用户新增器具");

        logger.info("存申请信息");
        mandatoryInstrumentSaveInput.getApply().setApplyType(applyType);
        mandatoryInstrumentSaveInput.getApply().setCreateUser(user);
        mandatoryInstrumentSaveInput.getApply().setDepartment(user.getDepartment());
        mandatoryInstrumentSaveInput.getApply().addMandatoryInstrument(mandatoryInstrumentSaveInput.getMandatoryInstrument());
        mandatoryInstrumentSaveInput.getApply().setAuditingDepartment(mandatoryInstrumentSaveInput.getAuditDepartment());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentSaveInput.getApply());

        logger.info("存已办工作信息");
        mandatoryInstrumentSaveInput.getWork().setWorkflowNode(mandatoryInstrumentSaveInput.getCurrentWorkflowNode());
        mandatoryInstrumentSaveInput.getWork().setDone(true);
        mandatoryInstrumentSaveInput.getWork().setTodo(false);
        mandatoryInstrumentSaveInput.getWork().setAuditUser(user);
        mandatoryInstrumentSaveInput.getWork().setAuditDepartment(user.getDepartment());
        mandatoryInstrumentSaveInput.getWork().setApply(mandatoryInstrumentSaveInput.getApply());
        workRepository.save(mandatoryInstrumentSaveInput.getWork());

        logger.info("存待办工作信息");
        Work work = new Work();
        work.setWorkflowNode(mandatoryInstrumentSaveInput.getNextWorkflowNode());
        work.setAuditDepartment(mandatoryInstrumentSaveInput.getAuditDepartment());
        work.setApply(mandatoryInstrumentSaveInput.getApply());
        work.setPreWork(mandatoryInstrumentSaveInput.getWork());
        workRepository.save(work);

        return mandatoryInstrumentSaveInput;
    }

    @Override
    public MandatoryInstrument getOneMandatoryInstrument() {
        return mandatoryInstrumentRepository.save(this.getOneUnsavedMandatoryInstrument());
    }

    @Override
    public MandatoryInstrument getOneUnsavedMandatoryInstrument() {
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setName("测试器具名称" + CommonService.getRandomStringByLength(10));
        mandatoryInstrument.setSerialNum("出厂编号");
        mandatoryInstrument.setFixSite("安装地点");
        return mandatoryInstrument;
    }

    @Override
    public Set<MandatoryInstrument> computerCheckAbilityByDepartmentIdOfMandatoryInstruments(Long departmentId, Set<MandatoryInstrument> mandatoryInstruments) {
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            logger.info("获取可以检定的标准装置个数");
            int count = deviceSetDeviceInstrumentService.countByAccuracyIdAndMeasureScaleIdAndDepartmentId(
                    mandatoryInstrument.getAccuracy().getId(),
                    mandatoryInstrument.getMeasureScale().getId(),
                    departmentId);

            if (count > 0) {
                logger.info("个数大于0，有检定能力");
                mandatoryInstrument.setCheckAbility(true);
            } else {
                logger.info("个数等于0,无检定能力");
                mandatoryInstrument.setCheckAbility(false);
            }
        }

        return mandatoryInstruments;
    }

    @Override
    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentId(Set<MandatoryInstrument> mandatoryInstruments, Long departmentId) {
        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到相关的部门实体");
        }
        User user = userService.getCurrentLoginUser();
        Set<MandatoryInstrument> tempMandatoryInstruments = new HashSet<>();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(mandatoryInstrument.getId());
            if (mandatoryInstrument1.getStatus() == InstrumentEmploymentInfo.STATUS_NEW &&
                    mandatoryInstrument1.getMandatoryInstrumentApply() != null &&
                    !mandatoryInstrument1.getMandatoryInstrumentApply().getDone() &&
                    mandatoryInstrument1.getMandatoryInstrumentApply().getAuditingDepartment() != null &&
                    mandatoryInstrument1.getMandatoryInstrumentApply().getAuditingDepartment().getId() == user.getDepartment().getId()) {
                logger.info("器具状态为新建，而且对应的申请的审核部门是当前部门，而且当前申请未办结");
                mandatoryInstrument1.setCheckDepartment(department);
                mandatoryInstrument1.setAudit(true);
                mandatoryInstrument1.setStatus(InstrumentEmploymentInfo.STATUS_NORMAL);
                tempMandatoryInstruments.add(mandatoryInstrument1);
            }
        }
        logger.info("统一更新符合要求的强检器具");
        mandatoryInstrumentRepository.save(tempMandatoryInstruments);
    }

    @Override
    public void updateCheckCycleAndFirstCheckDate(Long id, MandatoryInstrument mandatoryInstrument) {
        logger.info("取出实体");
        MandatoryInstrument updateMandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (updateMandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }
        if (userService.getCurrentLoginUser().getDepartment().getId() != updateMandatoryInstrument.getCheckDepartment().getId()) {
            logger.info("被指定的技术机构才有更新的权限");
            throw new SecurityException("没有权限");
        }

        logger.info("更新检定周期和首次检定日期");
        updateMandatoryInstrument.setCheckCycle(mandatoryInstrument.getCheckCycle());
        updateMandatoryInstrument.setFirstCheckDate(mandatoryInstrument.getFirstCheckDate());
        mandatoryInstrumentRepository.save(updateMandatoryInstrument);
        return;
    }

    @Override
    public void delete(Long id) throws ObjectNotFoundException, SecurityException {
        logger.info("取出实体");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (mandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }

        if (!this.canBeDeleteOrUpdate(mandatoryInstrument)) {
            throw new SecurityException("您无权删除该器具");
        }

        mandatoryInstrumentRepository.delete(mandatoryInstrument);
        return;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentUser(Pageable pageable) {
        User currentUser = userService.getCurrentLoginUser();
        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
//        String applianceUser = "器具用户";
//        String technicalInstitution = "技术机构";
//        String management = "管理部门";
        Page<MandatoryInstrument> mandatoryInstruments;

        switch (departmentTypeName) {
            case "技术机构":
                mandatoryInstruments = mandatoryInstrumentRepository.findAllByCheckDepartment(currentUser.getDepartment(), pageable);
                break;
            case "器具用户":
                mandatoryInstruments = mandatoryInstrumentRepository.findAllByDepartment(currentUser.getDepartment(), pageable);
                break;
            case "管理部门":
                mandatoryInstruments = mandatoryInstrumentService.pageAllOfManagementUser(currentUser, pageable);
                break;
            default:
                mandatoryInstruments = null;
                break;
        }

        return mandatoryInstruments;
    }

    /**
     * 多条件查询
     *
     * @param disciplineId               学科ID
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId           二级类别
     * @param audit                      是否通过审核
     * @param name                       器具名称
     * @param pageable                   分页信息
     * @return
     */
    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Pageable pageable) {
        Long departmentId = userService.getCurrentLoginUser().getDepartment().getId();
        return this.pageAllOfCurrentManageDepartmentBySpecification(id, null, departmentId, null, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, audit, name, pageable);
    }

    /**
     * 更新
     *
     * @param id
     * @param mandatoryInstrument 强检器具
     * @author panjie
     */
    @Override
    public void update(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException {
        MandatoryInstrument oldMandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (oldMandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }

        if (!this.canBeDeleteOrUpdate(oldMandatoryInstrument)) {
            throw new SecurityException("器具状态非正在审核，或该器具并不属于您");
        }

        User user = userService.getCurrentLoginUser();
        logger.info("设置添加用户及所属部门");
        mandatoryInstrument.setId(id);
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        return;
    }

    /**
     * 检测是否可以被删除或是被全部更新
     * @param mandatoryInstrument 强检器具
     * @return
     * @author panjie
     */
    public boolean canBeDeleteOrUpdate(MandatoryInstrument mandatoryInstrument) {
        logger.info("判断是否为退回");
        if (mandatoryInstrument.getStatus() != InstrumentEmploymentInfo.STATUS_BACKED
                && mandatoryInstrument.getStatus() != InstrumentEmploymentInfo.STATUS_NEW) {
            return false;
        }

        logger.info("判断强检器具的所在部门是否为用户所在的部门");
        if (userService.getCurrentLoginUser().getDepartment().getId() != mandatoryInstrument.getDepartment().getId()) {
            return false;
        }
        return true;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfManagementUser(User currentUser, Pageable pageable) {
        logger.info("获取当前登录用户的所有区域");
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());

        logger.info("获取所有的指定强检器具");
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByDistricts(districts, pageable);
        return mandatoryInstruments;
    }

    @Override
    public List<MandatoryInstrument> getAllOfCurrentUser() {
        logger.info("取出当前登录用户");
        User currentUser = userService.getCurrentLoginUser();
        logger.info("获取所有的数据");
        List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByCheckDepartment(currentUser.getDepartment());
        return mandatoryInstruments;
    }

    @Override
    public Page<MandatoryInstrument> pageByCheckDepartmentOfCurrentDepartment(Pageable pageable) {
        Department checkDepartment = userService.getCurrentLoginUser().getDepartment();
        return mandatoryInstrumentRepository.findAllByCheckDepartment(checkDepartment, pageable);
    }

    @Override
    public void updateFixSiteAndName(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException {
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(id);
        if (mandatoryInstrument1 == null) {
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }
        mandatoryInstrument1.setFixSite(mandatoryInstrument.getFixSite());
        mandatoryInstrument1.setName(mandatoryInstrument.getName());
        mandatoryInstrumentRepository.save(mandatoryInstrument1);
        return;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Pageable pageable) throws SecurityException{
        User user = userService.getCurrentLoginUser();

        if (null != departmentId) {
            logger.info("传入部门ID，则将区域ID设置为当前部门所在ID");
            Department department = departmentRepository.findOne(departmentId);
            if (null == department) {
                throw new ObjectNotFoundException(404, "部门实体未找到");
            }
            districtId = department.getDistrict().getId();
        }

        // 获取所在的区域列表
        List<District> districts = this.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        // 格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);                                                  // id
        map.put("districts", districts);                                    // 区域
        map.put("departmentId", departmentId);                              // 所属部门
        map.put("name", name);                                              // 器具名称
        map.put("disciplineId", disciplineId);                              // 学科类别
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);  // 一级类别
        map.put("instrumentTypeId", instrumentTypeId);                      // 二级类别
        map.put("audit", audit);                                            // 是否通过审核
        map.put("checkDepartmentId", checkDepartmentId);                    // 检定机构

        org.springframework.data.jpa.domain.Specification specification = MandatoryInstrumentSpecs.base(map);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAll(specification, pageable);
        return mandatoryInstruments;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentTechnicalInstitutionBySpecification(Long id, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String name, Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Department checkDepartment = user.getDepartment();

        // 获取所在的区域列表
        List<District> districts = this.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        // 格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);                                                  // id
        map.put("districts", districts);                                    // 区域
        map.put("departmentId", departmentId);                              // 所属部门
        map.put("name", name);                                              // 器具名称
        map.put("disciplineId", disciplineId);                              // 学科类别
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);  // 一级类别
        map.put("instrumentTypeId", instrumentTypeId);                      // 二级类别
        map.put("checkDepartmentId", checkDepartment.getId());              // 审核部门

        org.springframework.data.jpa.domain.Specification specification = MandatoryInstrumentSpecs.base(map);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAll(specification, pageable);
        return mandatoryInstruments;
    }

    /**
     * 获取当前部门拥有权限查看的区域列表
     * @param rootDistrict 根区域
     * @param sonDistrictId 传入的子区域id
     * @return
     */
    @Override
    public List<District> getRightDistrictsByRootDistrictAndSonDistrictId(District rootDistrict, Long sonDistrictId) {
        logger.info("对区域进行权限判定");
        List<District> districts = null;
        districts = districtService.getSonsListByDistrict(rootDistrict);
        if (null != sonDistrictId) {
            Boolean found = false;
            for (int i = 0; i < districts.size() && !found; i++) {
                if (districts.get(i).getId() == sonDistrictId) {
                    found = true;
                }
            }
            logger.info("传入的区域ID在父区域中，则取子区域包含的所有区域");
            if (true == found) {
                districts = districtService.getSonsListByDistrictId(sonDistrictId);
            }
        }

        return districts;
    }

    @Override
    public void setStatusToBackById(Long id) throws ObjectNotFoundException, SecurityException{
        logger.info("查看当前器具状态");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (null == mandatoryInstrument) {
            throw new ObjectNotFoundException(404, "传入的强检器具实体不存在");
        }

        if (mandatoryInstrument.getStatus() != InstrumentEmploymentInfo.STATUS_NEW) {
            throw new SecurityException("该器具当前状态不允许退回");
        }
        logger.info("取当前部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断在办部门是否为当前部门");
        if (currentDepartment.getId() != mandatoryInstrument.getMandatoryInstrumentApply().getAuditingDepartment().getId()) {
            throw new SecurityException("当前用户所有部门并不具有审核权限");
        }

        logger.info("设置状态");
        mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_BACKED);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        return;
    }
}
