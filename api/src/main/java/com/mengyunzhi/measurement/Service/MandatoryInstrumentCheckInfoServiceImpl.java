package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.MandatoryInstrumentCheckInfoSpecs;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chuhang on 17-8-2.
 * 强制器具检定信息的service实现
 */
@Service
public class MandatoryInstrumentCheckInfoServiceImpl extends InstrumentCheckInfoServiceImpl implements MandatoryInstrumentCheckInfoService {
    private Logger logger = Logger.getLogger(DepartmentServiceImpl.class.getName());

    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;    // 器具检定信息
    @Autowired
    private UserService userService;
    @Autowired
    private MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;
    @Autowired
    private DistrictService districtService;        //区域
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;            //强检器具
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;

    @Override
    public void save(InstrumentCheckInfo instrumentCheckInfo) {
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();
        instrumentCheckInfo.setCheckDepartment(department);
        logger.info("保存创建用户");
        instrumentCheckInfo.setCreateUser(userService.getCurrentLoginUser());
        logger.info("保存强制器具检定信息");
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        logger.info("算出强检器具的下次检定时间保存 (本次检定时间加检定周期)");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(instrumentCheckInfo.getMandatoryInstrument().getId());

        if (mandatoryInstrument.getCheckCycle() != 0 && mandatoryInstrument.getCheckCycle() != -1) {
            logger.info("强检器具的周期非未设定和无限期");
            /**
             * sql.Date 如果在当前日期上，加入天数
             * https://stackoverflow.com/questions/15802010/how-to-add-days-to-java-sql-date
             */
            Date date = instrumentCheckInfo.getCheckDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.DATE, mandatoryInstrument.getCheckCycle());
            mandatoryInstrument.setNextCheckDate(new java.sql.Date(calendar.getTimeInMillis()));
            mandatoryInstrumentRepository.save(mandatoryInstrument);
        }

        return;
    }

    @Override
    public void delete(Long id) {
        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoRepository.findOne(id);

        if (null == instrumentCheckInfo) {
            throw new ObjectNotFoundException(404, "未找到相关的强制器具检定信息实体");
        }

        //判断器具检定部门是否为当前用户所在部门
        if (userService.getCurrentLoginUser().getDepartment().getId() != instrumentCheckInfo.getCheckDepartment().getId()) {
            throw new SecurityException("该用户无此权限");
        }

        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        return;
    }

    @Override
    public void update(Long id, InstrumentCheckInfo instrumentCheckInfo) {
        instrumentCheckInfo.setId(id);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        return;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllByMandatoryInstrumentId(Long mandatoryInstrumentId, Pageable pageable) {
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAllByMandatoryInstrumentId(mandatoryInstrumentId, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfManagementUser(User currentUser, Pageable pageable) {
        //获取当前用户的所以子区域
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());

        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAllByDistricts(districts, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfManagementDepartmentBySpecification(
            Integer year, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId,
            Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId,
            Long accuracyDisplayNameId, Long mandatoryInstrumentId, String name, Pageable pageable) {

        logger.info("获取当前用户");
        User user = userService.getCurrentLoginUser();

        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = mandatoryInstrumentService.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        // 根据用户的查询条件获取检定信息
        Map<String, Object> map = new HashMap<>();
        map.put("checkDepartmentId", checkDepartmentId);
        map.put("disciplineId", disciplineId);
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);
        map.put("instrumentTypeId", instrumentTypeId);
        map.put("certificateNum", certificateNum);
        map.put("checkResultId", checkResultId);
        map.put("accuracyDisplayNameId", accuracyDisplayNameId);
        map.put("mandatoryInstrumentId", mandatoryInstrumentId);
        map.put("districts", districts);
        map.put("name", name);
        map.put("departmentId", departmentId);
        map.put("year", year);

        Specification specification = MandatoryInstrumentCheckInfoSpecs.base(map);
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAll(specification, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfMeasureUserBySpecification(Integer year, Long disciplineId, Long instrumentFirstLevelTypeId,
             Long instrumentTypeId, Long checkResultId, Long mandatoryInstrumentId, String name, Pageable pageable) {

        logger.info("获取当前用户");
        User user = userService.getCurrentLoginUser();
        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = districtService.getSonsListByDistrict(user.getDepartment().getDistrict());

        logger.info("格式话数据");
        Map<String, Object> map = new HashMap<>();
        map.put("mandatoryInstrumentId", mandatoryInstrumentId);            // 强检器具id                                       // id
        map.put("districts", districts);                                    // 区域
        map.put("checkResultId", checkResultId);                            // 检定结果
        map.put("name", name);                                              // 器具名称
        map.put("disciplineId", disciplineId);                              // 学科类别
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);  // 一级类别
        map.put("instrumentTypeId", instrumentTypeId);                      // 二级类别
        map.put("year", year);                                              // 年度
        Specification specification = MandatoryInstrumentCheckInfoSpecs.base(map);
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAll(specification, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfTechnicalInstitutionDepartmentBySpecification(Integer year, Long districtId, Long departmentId,
            Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId,
            Long accuracyDisplayNameId, Long mandatoryInstrumentId, String name, Pageable pageable) {
        logger.info("获取当前用户");
        User user = userService.getCurrentLoginUser();

        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = mandatoryInstrumentService.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        // 根据用户的查询条件获取检定信息
        Map<String, Object> map = new HashMap<>();
        map.put("checkDepartmentId", user.getDepartment().getId());
        map.put("disciplineId", disciplineId);
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);
        map.put("instrumentTypeId", instrumentTypeId);
        map.put("certificateNum", certificateNum);
        map.put("checkResultId", checkResultId);
        map.put("accuracyDisplayNameId", accuracyDisplayNameId);
        map.put("mandatoryInstrumentId", mandatoryInstrumentId);
        map.put("districts", districts);
        map.put("name", name);
        map.put("departmentId", departmentId);
        map.put("year", year);

        Specification specification = MandatoryInstrumentCheckInfoSpecs.base(map);
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAll(specification, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfCurrentUser(Pageable pageable) {
        //取出当前用用户
        User currentUser = userService.getCurrentLoginUser();
        Page<InstrumentCheckInfo> instrumentCheckInfos;
        //根据当前登录用户获取相应相应强检器具的检定信息
        switch (currentUser.getDepartment().getDepartmentType().getName()) {
            case "技术机构":
                instrumentCheckInfos = instrumentCheckInfoRepository.getAllByCheckDepartment(currentUser.getDepartment(), pageable);
                break;
            case "器具用户":
                instrumentCheckInfos = instrumentCheckInfoRepository.findAllByDepartment(currentUser.getDepartment(), pageable);
                break;
            case "管理部门":
                instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementUser(currentUser, pageable);
                break;
            default:
                instrumentCheckInfos = null;
                break;
        }

        return instrumentCheckInfos;
    }

}
