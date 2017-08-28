package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/14.
 * 学科M层实现
 */
@Service
public class DisciplineServiceImpl implements DisciplineService {
    private static Logger logger = Logger.getLogger(DisciplineServiceImpl.class.getName());
    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public Discipline save(Discipline discipline) {
        disciplineRepository.save(discipline);
        return discipline;
    }

    @Override
    public void delete(Long id) {
        disciplineRepository.delete(id);
        return;
    }

    @Override
    public void update(Long id, Discipline discipline) {
//      对传过来的实体设置id，并且保存
        discipline.setId(id);
        disciplineRepository.save(discipline);
        return;
    }

    @Override
    public List<Discipline> getAll() {
        List<Discipline> list = (List<Discipline>) disciplineRepository.findAll();
        return list;
    }

    /**
     * 获取一个实体
     * @param id 实体id
     * @return Discipline 学行类别
     * @author panjie
     */
    @Override
    public Discipline getById(Long id) throws ObjectNotFoundException {
        Discipline discipline = disciplineRepository.findOne(id);
        if (null == discipline) {
            throw new ObjectNotFoundException(404, "您所查询的实体不存在或已删除");
        }
        return discipline;
    }

    @Override
    public Discipline getOneSavedDiscipline() {
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);
        return discipline;
    }
}
