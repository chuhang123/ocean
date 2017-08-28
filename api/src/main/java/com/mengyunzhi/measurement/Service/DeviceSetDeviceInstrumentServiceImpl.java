package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.controller.DisciplineController;
import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/19.
 * 计量标准装置：授权检定项目
 */
@Service
public class DeviceSetDeviceInstrumentServiceImpl implements DeviceSetDeviceInstrumentService {
    private Logger logger = Logger.getLogger(DisciplineController.class.getName());

    @Autowired private AccuracyRepository accuracyRepository;   // 精度
    @Autowired private MeasureScaleRepository measureScaleRepository;   // 测量范围
    @Autowired private DepartmentRepository departmentRepository;       // 部门
    @Autowired private DeviceSetRepository deviceSetRepository;
    @Autowired private DeviceInstrumentRepository deviceInstrumentRepository;   //授权检定项目
    @Override
    public int countByAccuracyIdAndMeasureScaleIdAndDepartmentId(Long accuracyId, Long measureScaleId, Long departmentId) throws ObjectNotFoundException {
        Accuracy accuracy = accuracyRepository.findOne(accuracyId);
        if (null == accuracy) {
            throw new ObjectNotFoundException(404, "未找到ID为" + accuracyId.toString() + "的Accuracy精度实体");
        }

        MeasureScale measureScale = measureScaleRepository.findOne(measureScaleId);
        if (null == measureScale) {
            throw new ObjectNotFoundException(404, "未找到ID为" + measureScaleId.toString() + "的MeasureScale测量范围实体");
        }

        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到ID为" + departmentId.toString() + "的Department部门实体");
        }

        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMeasureScale(measureScale);
        return deviceSetRepository.countByDepartmentAndDeviceInstrument(department, deviceInstrument);
    }

    @Override
    public void delete(Long accuracyId, Long measureScaleId, Long deviceSetId) {
        logger.info("获取deviceSet");
        DeviceSet deviceSet = deviceSetRepository.findOne(deviceSetId);
        // 1.如果要删除中间表的数据，首先获取与deviceSet关联的DeviceInstrument数据,并进行迭代
        Set<DeviceInstrument>  deviceInstruments = deviceSet.getDeviceInstruments();
        Iterator<DeviceInstrument> deviceInstrumentIterator = deviceInstruments.iterator();
        // 2.根据精度id和测量范围id获取DeviceInstrument对象
        MeasureScale measureScale = measureScaleRepository.findOne(measureScaleId);
        Accuracy accuracy = accuracyRepository.findOne(accuracyId);
        DeviceInstrument deviceInstrument = deviceInstrumentRepository.findTopByAccuracyAndMeasureScale(accuracy, measureScale);
        // 3.第1步得到的数组中，如果有与2相同的元素，则去除这个元素
        while (deviceInstrumentIterator.hasNext()) {
            DeviceInstrument deviceInstrumentNode = deviceInstrumentIterator.next();
            logger.info("判断两个对象是否相同");
            if (deviceInstrumentNode.getMeasureScale().getId() == deviceInstrument.getMeasureScale().getId()
                    && deviceInstrumentNode.getAccuracy().getId() == deviceInstrument.getAccuracy().getId()) {
                deviceInstrumentIterator.remove();
            }
        }
        // 4.将第3步得到的数组，set与DeviceSet关联的DeviceInstrument数据
        deviceSet.setDeviceInstruments(deviceInstruments);
        // 保存
        deviceSetRepository.save(deviceSet);
        return;

    }
}
