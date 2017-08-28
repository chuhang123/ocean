package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.InstrumentType;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/26.
 * 器具类别 M层
 */
public interface InstrumentTypeService {
    //增加方法
    InstrumentType save(InstrumentType instrumentType);

    //删除方法
    void delete(Long id);

    //更新方法
    void update(Long id, InstrumentType instrumentType);

    //查看index界面所有元素
    List<InstrumentType> getAll();

    // 获取某个实体
    InstrumentType get(Long id) throws ObjectNotFoundException;

    // 通过学科id获取对应的器具类别信息
    Page<InstrumentType> getAllByDisciplineId(Long id, Pageable pageable);

    //通过学科类ID获取所有的器具类别
    List<InstrumentType> getAllByDisciplineId(Long id);

    //通过学科实体获取所有的器具类别
    List<InstrumentType> getAllByDiscipline(Discipline discipline);

    //通过测量范围ID获取器具类别
    InstrumentType getByMeasureScaleId(Long measureScaleId);

    static InstrumentType getOneInstrumentType() {
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName(CommonService.getRandomStringByLength(10));
        instrumentType.setPinyin(CommonService.getRandomStringByLength(10));
        return instrumentType;
    }
}
