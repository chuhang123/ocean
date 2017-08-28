package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentType;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentTypeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/7/27.
 */
@Service("MandatoryInstrumentTypeService")
public class MandatoryInstrumentTypeServiceImpl extends InstrumentTypeServiceImpl implements MandatoryInstrumentTypeService {
    @Autowired
    MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Override
    public List<MandatoryInstrumentType> findAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId) {
        return mandatoryInstrumentTypeRepository.findAllByInstrumentFirstLevelTypeId(instrumentFirstLevelTypeId);
    }

    @Override
    public Page<MandatoryInstrumentType> getAllMandatoryInstrumentTypesByDisciplineId(Long id, Pageable pageable) {
        return mandatoryInstrumentTypeRepository.findAllByDisciplineId(id, pageable);
    }

    @Override
    public List<MandatoryInstrumentType> getAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId) throws ObjectNotFoundException {
        InstrumentFirstLevelType instrumentFirstLevelType = instrumentFirstLevelTypeRepository.findOne(instrumentFirstLevelTypeId);
        if (null == instrumentFirstLevelType) {
            throw new ObjectNotFoundException(404, "传入的实体ID有误");
        }

        return this.getAllByInstrumentFirstLevelType(instrumentFirstLevelType);
    }

    @Override
    public List<MandatoryInstrumentType> getAllByInstrumentFirstLevelType(InstrumentFirstLevelType instrumentFirstLevelType) {
        return mandatoryInstrumentTypeRepository.findAllByInstrumentFirstLevelTypeId(instrumentFirstLevelType.getId());
    }
}
