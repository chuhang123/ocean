package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Discipline;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/14.
 * 学科M层接口
 */
public interface DisciplineService {
//    保存方法
    Discipline save(Discipline discipline);
//    删除方法
    void delete(Long id);
//    更新方法
    void update(Long id, Discipline discipline);
//    获得index界面的数据
    List<Discipline> getAll();

    // 获取一个实体
    Discipline getById(Long id) throws ObjectNotFoundException;

    // 获取一个未持久化的新实体
    static Discipline getOneDiscipline(){
        Discipline discipline = new Discipline();
        discipline.setName(CommonService.getRandomStringByLength(10));
        discipline.setPinyin(CommonService.getRandomStringByLength(10));
        return discipline;
    }

    // 获取一个持久化过的学科类别
    Discipline getOneSavedDiscipline();
}
