package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
public interface InstrumentFirstLevelTypeService {
    static InstrumentFirstLevelType getOneInstrumentFirstLevelType() {
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setName(CommonService.getRandomStringByLength(10));
        instrumentFirstLevelType.setPinyin(CommonService.getRandomStringByLength(10));
        return instrumentFirstLevelType;
    }
    List<InstrumentFirstLevelType> getAllByDisciplineId(Long disciplineId);
    InstrumentFirstLevelType save(InstrumentFirstLevelType instrumentFirstLevelType);
}
