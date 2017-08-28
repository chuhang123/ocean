package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasureScale;
import com.mengyunzhi.measurement.repository.MeasureScaleRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentType;
import com.mengyunzhi.measurement.repository.StandardDeviceInstrumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-27.
 * 标准器类别Service实现
 */
@Service("StandardDeviceInstrumentTypeService")
public class StandardDeviceInstrumentTypeServiceImpl extends InstrumentTypeServiceImpl implements StandardDeviceInstrumentTypeService {
    @Autowired protected StandardDeviceInstrumentTypeRepository standardDeviceInstrumentTypeRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;

    @Override
    public StandardDeviceInstrumentType get(Long id) {
        return standardDeviceInstrumentTypeRepository.findOne(id);
    }

    @Override
    public List<StandardDeviceInstrumentType> getAllStandardDeviceInstrumentTypes() {
        return (List<StandardDeviceInstrumentType>) standardDeviceInstrumentTypeRepository.findAll();
    }

    @Override
    public StandardDeviceInstrumentType getByMeasureScaleId(Long measureScaleId) {
        MeasureScale measureScale = measureScaleRepository.findOne(measureScaleId);
        return (StandardDeviceInstrumentType) measureScale.getInstrumentType();
    }

    @Override
    public List<StandardDeviceInstrumentType> getAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId) {
        return standardDeviceInstrumentTypeRepository.getAllByInstrumentFirstLevelTypeId(instrumentFirstLevelTypeId);
    }

    @Override
    public Page<StandardDeviceInstrumentType> pageByDisciplineId(Long disciplineId, Pageable pageable) {
        return standardDeviceInstrumentTypeRepository.pageByDisciplineId(disciplineId, pageable);
    }
}
