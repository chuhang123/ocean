package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
@Service
public class InstrumentFirstLevelTypeServiceImpl implements InstrumentFirstLevelTypeService {
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;  // 一级器具类别

    @Override
    public List<InstrumentFirstLevelType> getAllByDisciplineId(Long disciplineId) {
        return instrumentFirstLevelTypeRepository.findAllByDisciplineId(disciplineId);
    }

    @Override
    public InstrumentFirstLevelType save(InstrumentFirstLevelType instrumentFirstLevelType) {
        return instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
    }
}
