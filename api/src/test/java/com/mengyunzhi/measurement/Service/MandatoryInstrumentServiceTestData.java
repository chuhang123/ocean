package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panjie on 17/8/1.
 */
@Component
public class MandatoryInstrumentServiceTestData {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentServiceTestData.class.getName());
    @Autowired
    DeviceSetService deviceSetService;                       // 标准装置
    @Autowired
    DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired
    DeviceInstrumentRepository deviceInstrumentRepository;       // 授权检定范围
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强制检定申请
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;                // 强制检定器具
    @Autowired InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;   // 器具一级类别
    @Autowired InstrumentTypeRepository instrumentTypeRepository;   // 器具二级类别
    @Autowired DisciplineRepository disciplineRepository;   // 学科类别

    @Autowired UserService userService;                     // 用户


    public void computerCheckAbilityByDepartmentIdOfMandatoryInstruments(
            Accuracy accuracy,
            Accuracy accuracy1,
            MeasureScale measureScale,
            Department department,
            JSONArray jsonArray,
            Set<MandatoryInstrument> mandatoryInstruments) {

        logger.info("新建标准装置");
        DeviceSet deviceSet = deviceSetService.getOneDeviceSet();
        deviceSet.setDepartment(department);

        logger.info("新建授权检定项目");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("设置标准装置可以检定的授权项目");
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);
        for (int i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneMandatoryInstrument();
            if (i % 2 == 0) {
                logger.info("当为偶数个时，设置精确度");
                mandatoryInstrument.setAccuracy(accuracy);
            } else {
                logger.info("当为奇数时，设置另一个精确度");
                mandatoryInstrument.setAccuracy(accuracy1);
            }

            mandatoryInstrument.setMeasureScale(measureScale);
            mandatoryInstrumentRepository.save(mandatoryInstrument);
            mandatoryInstruments.add(mandatoryInstrument);
            logger.info("设置jsonArray");
            mandatoryInstrument.setCreateTime(null);
            JSONObject jsonObject = JSONObject.fromObject(mandatoryInstrument);
            jsonArray.add(jsonObject);
        }
    }

    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest(Department department, MandatoryInstrumentApply mandatoryInstrumentApply) {
        logger.info("创建一个用户，申请");
        mandatoryInstrumentApply.setAuditingDepartment(department);
        mandatoryInstrumentApply.setDone(false);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("新建强制检定器具");
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneMandatoryInstrument();
            if (i % 2 == 0) {
                logger.info("2的倍数时，添加当前申请");
                mandatoryInstrument.setMandatoryInstrumentApply(mandatoryInstrumentApply);
            }

            if (i % 3 == 0) {
                logger.info("3的位数时，设置状态为正常");
                mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_NORMAL);
            }
            mandatoryInstruments.add(mandatoryInstrument);
        }
        mandatoryInstrumentApply.setMandatoryInstruments(mandatoryInstruments);
        mandatoryInstrumentRepository.save(mandatoryInstruments);
    }

    public void pageAllOfCurrentUserBySpecification(String name, MandatoryInstrument mandatoryInstrument, InstrumentType instrumentType, InstrumentFirstLevelType instrumentFirstLevelType, Discipline discipline) {

        logger.info("设置区域");
        // 传入器具名称
        mandatoryInstrument.setName(name);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        // 传入器具名称及器具类别
        instrumentTypeRepository.save(instrumentType);
        mandatoryInstrument.setInstrumentType(instrumentType);
        mandatoryInstrumentRepository.save(mandatoryInstrument);


        // 传入器具名称、及一级学科类别 name
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);


        // 只传入学科类别 name
        disciplineRepository.save(discipline);
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
    }

    public MandatoryInstrument setStatusToBackById(User user) {
        logger.info("新建一个申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setDepartment(user.getDepartment());
        mandatoryInstrumentApply.setAuditingDepartment(user.getDepartment());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setMandatoryInstrumentApply(mandatoryInstrumentApply);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        return mandatoryInstrument;
    }
}
