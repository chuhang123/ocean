package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/26.
 * 器具类别 M层实现
 */
@Service("InstrumentTypeService")
public class InstrumentTypeServiceImpl implements InstrumentTypeService {
    private Logger logger = Logger.getLogger(InstrumentTypeServiceImpl.class.getName());
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;

    @Override
    public InstrumentType save(InstrumentType instrumentType) {
        for(MeasureScale measureScale: instrumentType.getMeasureScales()) {
            measureScale.setInstrumentType(instrumentType);
        }

        for(Specification specification: instrumentType.getSpecifications()) {
            specification.setInstrumentType(instrumentType);
        }
        instrumentTypeRepository.save(instrumentType);
        return instrumentType;
    }

    @Override
    public void delete(Long id) {
        instrumentTypeRepository.delete(id);
        return;
    }

    @Override
    public void update(Long id, InstrumentType instrumentType) throws ObjectNotFoundException{
        logger.info("查找要更新的实体是否存在");
        InstrumentType instrumentType1 = instrumentTypeRepository.findOne(id);
        if (instrumentType1 == null)
        {
            throw new ObjectNotFoundException(404, "要修改的实体不存在");
        }

        instrumentType.setId(id);
        for(MeasureScale measureScale: instrumentType.getMeasureScales()) {
            measureScale.setInstrumentType(instrumentType);
        }

        for(Specification specification: instrumentType.getSpecifications()) {
            specification.setInstrumentType(instrumentType);
        }

        instrumentTypeRepository.save(instrumentType);
        return;

    }

    @Override
    public List<InstrumentType> getAll() {
        List<InstrumentType> list = (List<InstrumentType>) instrumentTypeRepository.findAll();
        return list;

    }

    @Override
    public InstrumentType get(Long id) throws ObjectNotFoundException {
        InstrumentType instrumentType = instrumentTypeRepository.findOne(id);
        return instrumentType;
    }

    @Override
    public Page<InstrumentType> getAllByDisciplineId(Long id, Pageable pageable) {
        Page<InstrumentType> page = instrumentTypeRepository.findAllByDisciplineId(id, pageable);
        return page;
    }

    @Override
    public List<InstrumentType> getAllByDisciplineId(Long disciplineId) {
        //获取所有的器具类别
        List<InstrumentType> instrumentTypes = instrumentTypeRepository.findAllByDisciplineId(disciplineId);
        //返回数据
        return instrumentTypes;
    }

    @Override
    public List<InstrumentType> getAllByDiscipline(Discipline discipline) {
        //获取所有的器具类别
        List<InstrumentType> instrumentTypes = instrumentTypeRepository.findAllByDisciplineId(discipline.getId());
        //返回数据
        return instrumentTypes;
    }

    @Override
    public InstrumentType getByMeasureScaleId(Long measureScaleId) {
        MeasureScale measureScale = measureScaleRepository.findOne(measureScaleId);
        return measureScale.getInstrumentType();
    }
}
