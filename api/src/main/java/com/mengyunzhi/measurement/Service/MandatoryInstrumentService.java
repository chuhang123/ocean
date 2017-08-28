package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.input.MandatoryInstrumentSaveInput;
import com.mengyunzhi.measurement.repository.District;
import com.mengyunzhi.measurement.repository.MandatoryInstrument;
import com.mengyunzhi.measurement.repository.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by liming on 17-7-6.
 * 强检器具
 */
public interface MandatoryInstrumentService {
    // 保存的同时，保存器具生产信息
    MandatoryInstrument saveWithInstrumentProduction(MandatoryInstrument mandatoryInstrument);

    MandatoryInstrument save(MandatoryInstrument mandatoryInstrument);

    // 提出检定申请
    MandatoryInstrumentSaveInput apply(MandatoryInstrumentSaveInput mandatoryInstrumentSaveInput);

    // 获取一个强检器具
    MandatoryInstrument getOneMandatoryInstrument();
    MandatoryInstrument getOneUnsavedMandatoryInstrument();
    // 计算某个部门对于强制检定数组中的强制检定实体是否具有检定能力
    Set<MandatoryInstrument> computerCheckAbilityByDepartmentIdOfMandatoryInstruments(Long departmentId, Set<MandatoryInstrument> mandatoryInstruments);

    void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentId(Set<MandatoryInstrument> mandatoryInstruments, Long departmentId);
    //更新强检器具的检定周期和首次检定日期
    void updateCheckCycleAndFirstCheckDate(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;
    //删除
    void delete(Long id) throws ObjectNotFoundException, SecurityException;

    //获取当前登录用户下的所有指定强检器具信息
    Page<MandatoryInstrument> pageAllOfCurrentUser(Pageable pageable);
    // 组合条件查询 (器具ID， 学科类别ID， 一级类别ID， 二级类别ID， 器具审核状态， 器具名称,
    Page<MandatoryInstrument> pageAllOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Pageable pageable);

    void update(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;
    // 是否可以删除或是更新基本信息
    boolean canBeDeleteOrUpdate(MandatoryInstrument mandatoryInstrument);

    // 获取当前管理部门下的所有指定强检器具
    Page<MandatoryInstrument> pageAllOfManagementUser(User currentUser, Pageable pageable);

    //获取当前技术机构的所有强检器具使用信息
    List<MandatoryInstrument> getAllOfCurrentUser();

    // 获取当前部门中被管理部门指定检定的器具信息
    Page<MandatoryInstrument> pageByCheckDepartmentOfCurrentDepartment(Pageable pageable);

    void updateFixSiteAndName(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;

    /**
     * 综合查询当前登录的管理部门所辖区域内的强检器具信息
     * @param id    器具ID
     * @param districtId 区域ID
     * @param departmentId 器具用户（部门）ID
     * @param checkDepartmentId 检定机构id
     * @param disciplineId  学科类别id
     * @param instrumentFirstLevelTypeId 一级类别ID
     * @param instrumentTypeId 二级类别ID
     * @param audit 是否审核ID
     * @param name 器具名称
     * @param pageable 分页信息
     * @return
     * @Author panjie@yunzhiclub.com
     */
    Page<MandatoryInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Pageable pageable) throws SecurityException;

    /**
     * 综合查询当前登录的技术机构被指定的强检器具信息
     * @param id 器具ID
     * @param districtId 区域ID
     * @param departmentId 部门ID （器具用户）
     * @param disciplineId  学科类虽
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId 二级类别
     * @param name  器具名称
     * @param pageable
     * @return
     */
    Page<MandatoryInstrument> pageAllOfCurrentTechnicalInstitutionBySpecification(Long id, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String name, Pageable pageable);

    List<District> getRightDistrictsByRootDistrictAndSonDistrictId(District rootDistrict, Long sonDistrictId);

    /**
     * 设置状态为 被退回
     * @param id 强检器具ID
     */
    void setStatusToBackById(Long id);
}