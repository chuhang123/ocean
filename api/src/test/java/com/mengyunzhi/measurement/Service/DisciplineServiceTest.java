package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Accuracy;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import com.mengyunzhi.measurement.repository.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by zhangjiahao on 2017/6/15.
 * 学科类别 单元测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DisciplineServiceTest{
    private static Logger logger = Logger.getLogger(DisciplineServiceTest.class.getName());
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Test
    public void save() throws Exception {
        logger.info("---- save test ----");
        logger.info("增加一个实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        disciplineRepository.save(discipline);

        discipline = disciplineRepository.findOne(discipline.getId());
        logger.info("断言新增成功");
        assertThat(discipline).isNotNull();
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- 删除实体 ----");
        logger.info("新增一个实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        disciplineService.save(discipline);

        logger.info("删除实体");
        disciplineService.delete(discipline.getId());

        logger.info("查询实体，并断言删除成功");
        Discipline discipline1 = disciplineRepository.findOne(discipline.getId());
        assertThat(discipline1).isNull();

    }

    @Test
    public void update() throws Exception {
        logger.info("---- save测试 ----");
        logger.info("新增一个实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        disciplineService.save(discipline);

        logger.info("修改实体");
        discipline.setName("hello");
        disciplineService.update(discipline.getId(),discipline);

        logger.info("断言修改成功");
        Discipline discipline1 = disciplineRepository.findOne(discipline.getId());
        assertThat(discipline1.getName()).isEqualTo("hello");

        disciplineRepository.delete(discipline.getId());
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll测试 ----");
        logger.info("新增一个实体");
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        disciplineRepository.save(discipline);
        List<Discipline> disciplines = disciplineService.getAll();
        assertThat(disciplines.size()).isGreaterThan(0);

        disciplineRepository.delete(discipline.getId());
    }

    @Test
    @Transactional
    public void getById() {
        logger.info("新增实体，保存，并查询。断言其ID相同。");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        Discipline discipline = new Discipline();
        discipline.setName("name");
        discipline.setPinyin("pinyin");
        discipline.addAccruacy(accuracy);
        disciplineRepository.save(discipline);
        Discipline discipline1 = disciplineRepository.findOne(discipline.getId());
        assertThat(discipline1.getAccuracies().size()).isEqualTo(1);

        disciplineRepository.delete(discipline);
        assertThat(accuracyRepository.findOne(accuracy.getId())).isNull();
    }
}