package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentType;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/26.
 * 强制检定器具类别
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MandatoryInstrumentTypeServiceTest {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentTypeServiceTest.class.getName());
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;      // 学科类别
    @Autowired MandatoryInstrumentTypeService mandatoryInstrumentTypeService;   // 强制检定器具类别
    @Autowired
    MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;        // 强制检定器具类别

    @Test
    public void findAllByFirstLevelDisciplineId() throws Exception {
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        for (int i = 0; i < 10; i++) {
            MandatoryInstrumentType mandatoryInstrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
            mandatoryInstrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
            mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);
        }

        List<MandatoryInstrumentType> mandatoryInstrumentTypes = mandatoryInstrumentTypeService.findAllByInstrumentFirstLevelTypeId(instrumentFirstLevelType.getId());
        assertThat(mandatoryInstrumentTypes.size()).isEqualTo(10);
        mandatoryInstrumentTypeRepository.delete(mandatoryInstrumentTypes);
    }

            // 强制检定器具类别
    @Test
    public void save() throws Exception {
        MandatoryInstrumentType mandatoryInstrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        mandatoryInstrumentTypeService.save(mandatoryInstrumentType);
        assertThat(mandatoryInstrumentType.getId()).isNotNull();
        mandatoryInstrumentTypeRepository.delete(mandatoryInstrumentType);
    }

}