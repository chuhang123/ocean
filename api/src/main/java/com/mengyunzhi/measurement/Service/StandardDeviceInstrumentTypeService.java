package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by liming on 17-7-27.
 * 标准器具类别Service
 */
public interface StandardDeviceInstrumentTypeService extends InstrumentTypeService{
    //获取一条数据
    StandardDeviceInstrumentType get(Long id);
    //获取所有的数据
    List<StandardDeviceInstrumentType> getAllStandardDeviceInstrumentTypes();
    //获取测量范围对应的
    StandardDeviceInstrumentType getByMeasureScaleId(Long measureScaleId);
    //获取一级器具类别下的标准器具类别
    List<StandardDeviceInstrumentType> getAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId);

    // 获取某个学科类别下的分页数据
    Page<StandardDeviceInstrumentType> pageByDisciplineId(Long disciplineId, Pageable pageable);

    static StandardDeviceInstrumentType getOneStandardDeviceInstrumentType() {
        StandardDeviceInstrumentType standardDeviceInstrumentType = new StandardDeviceInstrumentType();
        return standardDeviceInstrumentType;
    }
}
