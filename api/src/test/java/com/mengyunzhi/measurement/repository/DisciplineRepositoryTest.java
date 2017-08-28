package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liming on 17-5-18.
 * 学科
 */
public class DisciplineRepositoryTest extends RepositoryTest {
    private Logger logger = Logger.getLogger(DisciplineRepositoryTest.class.getName());

    @Autowired
    private DisciplineRepository disciplineRepository; // 学科
    private Discipline discipline = new Discipline();

    @Before
    public void before() {
        //保存并断言
        discipline.setName("name0");
        discipline.setPinyin("pinyin");
        disciplineRepository.save(discipline);
    }

    @Test
    public void save() {
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);
    }

    @After
    public void after() {
        disciplineRepository.delete(discipline);
    }
}