package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/6/26.
 * 器具类别 M层测试
 */
public class InstrumentTypeServiceTest extends ServiceTest{

    private static Logger logger = Logger.getLogger(InstrumentTypeServiceTest.class.getName());
    @Autowired
    private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;
    @Autowired
    private MeasureScaleRepository measureScaleRepository;  // 测量范围
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("InstrumentTypeService")
    private InstrumentTypeService instrumentTypeService;
    @Autowired
    private DisciplineService disciplineService;

    @Autowired private DisciplineRepository disciplineRepository; // 学科类别
    @Autowired private PurposeService purposeService; // 用途
    @Autowired private InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;   // 一级类具类别
    @Test
    public void save() throws Exception {
        logger.info("---- save方法测试 ----");

        logger.info("增加一个实体并保存");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");

        logger.info("新增用途");
        Purpose purpose = purposeService.getOnePurpose();
        instrumentType.addPurpose(purpose);

        logger.info("新增一个测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setValue("测量范围");
        measureScale.setInstrumentType(instrumentType);
        instrumentType.addMeasureScale(measureScale);
        mandatoryInstrumentTypeRepository.save(instrumentType);

        instrumentType = mandatoryInstrumentTypeRepository.findOne(instrumentType.getId());
        logger.info("断言保存成功");
        assertThat(instrumentType).isNotNull();
        assertThat(instrumentType.getMeasureScales().size()).isEqualTo(1);

        logger.info("断言外键存成功");
        MeasureScale measureScale1 = measureScaleRepository.findOne(measureScale.getId());
        assertThat(measureScale1.getInstrumentType().getId()).isEqualTo(instrumentType.getId());
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- delete方法测试 -----");
        logger.info("新建一个实体");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        mandatoryInstrumentTypeRepository.save(instrumentType);

        logger.info("删除该实体");
        instrumentTypeService.delete(instrumentType.getId());

        logger.info("查询实体，并断言删除成功");
        InstrumentType instrumentType1 = mandatoryInstrumentTypeRepository.findOne(instrumentType.getId());
        assertThat(instrumentType1).isNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("---- 更新测试 ----");
        logger.info("新增一个实体");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        mandatoryInstrumentTypeRepository.save(instrumentType);

        logger.info("修改该实体");
        instrumentType.setName("hello");

        instrumentTypeService.update(instrumentType.getId(), instrumentType);

        logger.info("断言修改成功");
        assertThat(instrumentType.getName()).isEqualTo("hello");
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll测试 ----");
        logger.info("新增一个实体");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        mandatoryInstrumentTypeRepository.save(instrumentType);

        List<InstrumentType> instrumentTypes;
        logger.info("取出所有的数据");
        instrumentTypes = instrumentTypeService.getAll();
        logger.info("断言取出的对象大小不为空");
        assertThat(instrumentTypes.size()).isNotEqualTo(0);
    }

    @Test
    public void get() {
        logger.info("先保存一个实体，然后查询");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();

        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        mandatoryInstrumentTypeRepository.save(instrumentType);

        InstrumentType instrumentType1 = instrumentTypeService.get(instrumentType.getId());
        assertThat(instrumentType.getId()).isEqualTo(instrumentType1.getId());
    }

    @Test
    public void page() {
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();

        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        mandatoryInstrumentTypeRepository.save(instrumentType);

        MandatoryInstrumentType instrumentType1 = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();

        instrumentType1.setName("namesdfd");
        instrumentType1.setPinyin("pinyinsdfsdf");
        instrumentType1.setInstrumentFirstLevelType(instrumentFirstLevelType);
        mandatoryInstrumentTypeRepository.save(instrumentType1);

        final PageRequest pageRequest = new PageRequest(
                1, 1
        );

        Page<MandatoryInstrumentType> page = mandatoryInstrumentTypeRepository.findAllByDisciplineId(discipline.getId(), pageRequest);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent().size()).isEqualTo(1);
        mandatoryInstrumentTypeRepository.delete(instrumentType1);
        mandatoryInstrumentTypeRepository.delete(instrumentType);
        disciplineRepository.delete(discipline);
        return;

    }

    @Test
    public void getAllByDisciplineId() {
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        mandatoryInstrumentTypeRepository.save(instrumentType);

        MandatoryInstrumentType instrumentType1 = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType1.setName("namesdfd");
        instrumentType1.setPinyin("pinyinsdfsdf");
        instrumentType1.setInstrumentFirstLevelType(instrumentFirstLevelType);
        mandatoryInstrumentTypeRepository.save(instrumentType1);

        //获取所有的数据
        List<InstrumentType> instrumentTypes = instrumentTypeService.getAllByDisciplineId(discipline.getId());

        logger.info("断言");
        assertThat(instrumentTypes.size()).isEqualTo(2);
        mandatoryInstrumentTypeRepository.delete(instrumentType1);
        mandatoryInstrumentTypeRepository.delete(instrumentType);
        disciplineRepository.delete(discipline);
        return;
    }

    @Test
    public void getByMeasureScaleId() {
        logger.info("新建器具类别");
        MandatoryInstrumentType instrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentType.setName("qijuleibie");
        instrumentType.setPinyin("pinyin");
        mandatoryInstrumentTypeRepository.save(instrumentType);
        logger.info("新建测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(instrumentType);
        measureScaleRepository.save(measureScale);
        logger.info("测试");
        InstrumentType instrumentType1 = instrumentTypeService.getByMeasureScaleId(measureScale.getId());
        logger.info("断言");
        assertThat(instrumentType1.getId()).isEqualTo(instrumentType.getId());
        logger.info("删除数据");
        mandatoryInstrumentTypeRepository.delete(instrumentType.getId());
        measureScaleRepository.delete(measureScale.getId());
    }
}