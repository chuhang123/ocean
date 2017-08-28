package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentType;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by panjie on 17/7/26.
 * 强制检定器具类别
 */
public interface MandatoryInstrumentTypeService extends InstrumentTypeService{
    // 获取某个学科类别下的列表
    List<MandatoryInstrumentType> findAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId);
    static MandatoryInstrumentType getOneMandatoryInstrumentType() {
        MandatoryInstrumentType mandatoryInstrumentType = new MandatoryInstrumentType();
        mandatoryInstrumentType.setName(CommonService.getRandomStringByLength(10));
        mandatoryInstrumentType.setPinyin(CommonService.getRandomStringByLength(10));
        return mandatoryInstrumentType;
    }
    Page<MandatoryInstrumentType> getAllMandatoryInstrumentTypesByDisciplineId(Long id, Pageable pageable);
    List<MandatoryInstrumentType> getAllByInstrumentFirstLevelTypeId(Long instrumentFirstLevelTypeId) throws ObjectNotFoundException;
    List<MandatoryInstrumentType> getAllByInstrumentFirstLevelType(InstrumentFirstLevelType instrumentFirstLevelType);
}
