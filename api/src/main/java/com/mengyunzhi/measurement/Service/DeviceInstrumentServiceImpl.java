package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.DeviceInstrumentSpecs;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目Service实现层
 */
@Service
public class DeviceInstrumentServiceImpl implements DeviceInstrumentService {
    static private Logger logger = Logger.getLogger(DeviceInstrumentServiceImpl.class.getName());

    @Autowired
    protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired
    protected DeviceSetService deviceSetService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected AccuracyRepository accuracyRepository;
    @Autowired
    protected MeasureScaleRepository measureScaleRepository;
    @Autowired
    protected DistrictService districtService;
    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;
    @Autowired
    private AccuracyService accuracyService;                // 精确度
    @Autowired
    private MeasureScaleService measureScaleService;        // 测量范围
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(DeviceSet deviceSet) {
        logger.info("得到检定项目列表");
        Iterator<DeviceInstrument> deviceInstrumentIterator = deviceSet.getDeviceInstruments().iterator();
        while (deviceInstrumentIterator.hasNext()) {
            DeviceInstrument deviceInstrument = deviceInstrumentIterator.next();
            //判断是否存在这个元素
            if (null == deviceInstrumentRepository.findTopByAccuracyAndMeasureScale(deviceInstrument.getAccuracy(), deviceInstrument.getMeasureScale())) {
                logger.info("持久化一个实体");
                deviceInstrumentRepository.save(deviceInstrument);
            }
        }

        logger.info("更新标准装置和授权检定项目的关系");
        deviceSetService.updateDeviceInstrumentsById(deviceSet.getId(), deviceSet);

        return;
    }

    @Override
    public Page<?> pageAllByCurrentUserOfDeviceInstrument(Pageable pageable) {
        //获取当前登录用户
        User currentUser = userService.getCurrentLoginUser();
        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
        String realDepartementTypeName = "技术机构";
        if (departmentTypeName.equals(realDepartementTypeName)) {
            //返回分页信息
            return deviceInstrumentRepository.findAllByDepartmentOfDeviceInstrument(currentUser.getDepartment(), pageable);
        } else {
            //如果是管理部门
            return deviceInstrumentService.pageAllOfCurrentUserOfManagement(pageable);
        }
    }

    @Override
    public Page<?> pageAllOfCurrentUserOfManagement(Pageable pageable) {
        //获取当强登录用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前登录用户的所有区域
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());
        //返回分页信息
        Page<?> page = deviceInstrumentRepository.findAllByDistricts(districts, pageable);
        return page;
    }

    @Override
    public Page<DeviceInstrument> pageByDeviceSetOfCurrentUser(DeviceSet deviceSet, Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        Long id = deviceSet.getId();
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAllByDeviceSetDepartmentAndDeviceSetId(department, id, pageable);
        return deviceInstruments;
//        Long id = 1L;
//
//        org.springframework.data.jpa.domain.Specification<DeviceSet> specification = new org.springframework.data.jpa.domain.Specification<DeviceSet>() {
//
//            /*
//                         * @param root: 代表的查询的实体类
//                         * @param query：可以从中得到Root对象，即告知JPA Criteria查询要查询哪一个实体类，
//                         * 还可以来添加查询条件，还可以结合EntityManager对象得到最终查询的TypedQuery 对象
//                         * @Param cb:criteriabuildre对象，用于创建Criteria相关的对象工程，当然可以从中获取到predicate类型
//                         * @return:代表一个查询条件
//                         * 参考地址：http://blog.csdn.net/luckyzhoustar/article/details/49362951
//                         * 官方文档：https://docs.spring.io/spring-data/jpa/docs/1.11.6.RELEASE/api/org/springframework/data/jpa/domain/Specification.html
//                         */
//            @Override
//            public Predicate toPredicate(Root<DeviceSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                logger.info("增加第一个查询条件");
//                Predicate predicate1 = criteriaBuilder.equal(root.get("id").as(Long.class), String.valueOf(id));
//                return predicate1;
//            }
//        };

//        return deviceSetRepository.findAll(specification, pageable);

    }

    @Override
    public Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(Long deviceSetId, Pageable pageable) {
        DeviceSet deviceSet = new DeviceSet();
        if (deviceSetId != 0) {
            deviceSet.setId(deviceSetId);
        }
        return this.pageByDeviceSetOfCurrentUser(deviceSet, pageable);
    }

    /**
     * 多条件查询
     * @param deviceSetId                   //标准器ID
     * @param districtId                    //区域ID
     * @param departmentId                  //技术机构ID
     * @param disciplineId                  //学科ID
     * @param instrumentTypeFirstLevelId    //一级类别
     * @param instrumentTypeId              //二级类别
     * @param name                          //标准装置名称
     * @return
     */
    @Override
    public Page<DeviceInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long deviceSetId, Long districtId, Long departmentId, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable) {
        logger.info("获取当前登录用户");
        User currentUser = userService.getCurrentLoginUser();

        if (null != departmentId) {
            logger.info("传入部门ID,将区域ID设置为当前部门所在ID");
            Department department = departmentRepository.findOne(departmentId);
            if (null == department) {
                throw new ObjectNotFoundException(404, "没有找到部门实体");
            }
            districtId = department.getDistrict().getId();
        }

        logger.info("对区域的权限进行判断");
        List<District> districts = null;
        districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());
        if (null != districtId) {
            Boolean found = false;
            for (int i = 0; i < districts.size() && !found; i++) {
                if (districts.get(i).getId() == districtId) {
                    found = true;
                }
            }
            if (false == found) {
                throw new SecurityException("对不起,您只能查看本区域的信息");
            }
            districts = districtService.getSonsListByDistrictId(districtId);
        }

        //格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("deviceSetId", deviceSetId);                                    //标准装置ID
        map.put("districts", districts);                                        //区域
        map.put("departmentId", departmentId);                                  //所属部门
        map.put("disciplineId", disciplineId);                                  //学科ID
        map.put("instrumentTypeFirstLevelId", instrumentTypeFirstLevelId);      //一级器具类别
        map.put("instrumentTypeId", instrumentTypeId);                          //二级类别
        map.put("name", name);                                                  //标准装名称

        org.springframework.data.jpa.domain.Specification specification = DeviceInstrumentSpecs.base(map);
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAll(specification, pageable);
        return deviceInstruments;
    }

    @Override
    public DeviceInstrument getOneUnSavedDeviceInstrument() {
        Accuracy accuracy = accuracyService.getOneAccuracy();
        MeasureScale measureScale = measureScaleService.getOneMeasureScale();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        return deviceInstrument;
    }
}
