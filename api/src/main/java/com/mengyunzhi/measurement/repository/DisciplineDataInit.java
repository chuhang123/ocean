package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/27.
 * 学科类别 数据初始化
 */
@Component
public class DisciplineDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered{
    private static Logger logger = Logger.getLogger(DisciplineDataInit.class.getName());
    @Autowired private DisciplineRepository disciplineRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        List<Discipline> disciplinesAll = (List<Discipline>) disciplineRepository.findAll();
        if (disciplinesAll.size() == 0) {
            Set<Discipline> disciplines = new HashSet<>();
            Discipline geometricDiscipline = new Discipline();
            geometricDiscipline.setName("几何量");
            geometricDiscipline.setPinyin("jiheliang");
            disciplines.add(geometricDiscipline);

            Discipline chemistryDiscipline = new Discipline();
            chemistryDiscipline.setName("化学");
            chemistryDiscipline.setPinyin("huaxue");
            disciplines.add(chemistryDiscipline);

            Discipline temperatureDiscipline = new Discipline();
            temperatureDiscipline.setName("温度");
            temperatureDiscipline.setPinyin("wendu");
            disciplines.add(temperatureDiscipline);

            Discipline mechanicsDiscipline = new Discipline();
            mechanicsDiscipline.setName("力学");
            mechanicsDiscipline.setPinyin("lixue");
            disciplines.add(mechanicsDiscipline);

            Discipline electromagnetismDiscipline = new Discipline();
            electromagnetismDiscipline.setName("电磁");
            electromagnetismDiscipline.setPinyin("dianci");
            disciplines.add(electromagnetismDiscipline);

            Discipline electronicsDiscipline = new Discipline();
            electronicsDiscipline.setName("电子");
            electronicsDiscipline.setPinyin("dianzi");
            disciplines.add(electronicsDiscipline);

            Discipline frequencyDiscipline = new Discipline();
            frequencyDiscipline.setName("时间频率");
            frequencyDiscipline.setPinyin("shijianpinlv");
            disciplines.add(frequencyDiscipline);

            Discipline opticsDiscipline = new Discipline();
            opticsDiscipline.setName("光学");
            opticsDiscipline.setPinyin("guanxue");
            disciplines.add(opticsDiscipline);

            Discipline acousticsDiscipline = new Discipline();
            acousticsDiscipline.setName("声学");
            acousticsDiscipline.setPinyin("shengxue");
            disciplines.add(acousticsDiscipline);

            disciplineRepository.save(disciplines);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
