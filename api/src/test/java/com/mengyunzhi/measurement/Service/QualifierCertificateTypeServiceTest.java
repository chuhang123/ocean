package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;
/**
 * Created by zhangjiahao on 2017/6/9.
 * 资格证类别 M层测试
 */
public class QualifierCertificateTypeServiceTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(QualifierCertificateTypeServiceTest.class.getName());

    @Autowired
    private  QualifierCertificateTypeService qualifierCertificateTypeService;

    @Autowired
    private QualifierCertificateTypeRepository qualifierCertificateTypeRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void save() throws Exception {
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        qualifierCertificateTypeService.save(qualifierCertificateType);

        qualifierCertificateType = qualifierCertificateTypeRepository.findOne(qualifierCertificateType.getId());
        assertThat(qualifierCertificateType).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("---- update test ----");
        logger.info("增加一个实体");
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        qualifierCertificateTypeRepository.save(qualifierCertificateType);

        logger.info("修改实体");
        qualifierCertificateType.setName("hello");
        qualifierCertificateTypeService.update(qualifierCertificateType.getId(), qualifierCertificateType);

        logger.info("断言修改成功");
        QualifierCertificateType qualifierCertificateTypeupdate = qualifierCertificateTypeRepository.findOne(qualifierCertificateType.getId());
        assertThat(qualifierCertificateTypeupdate.getName()).isEqualTo("hello");
    }

    @Test
    public void getAll() throws Exception {
        System.out.println(qualifierCertificateTypeService.getAll());
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- 删除实体 ----");
        logger.info("增加一个实体");
        QualifierCertificateType qualifierCertificateType = new QualifierCertificateType();
        qualifierCertificateTypeService.save(qualifierCertificateType);

        logger.info("删除实体");
        qualifierCertificateTypeService.delete(qualifierCertificateType.getId());

        logger.info("查询实体，并断言删除成功");
        QualifierCertificateType qualifierCertificateType2 = qualifierCertificateTypeRepository.findOne(qualifierCertificateType.getId());
        assertThat(qualifierCertificateType2).isNull();
    }

    @Test
    public void getAllByDisciplineId() throws Exception {
        logger.info("新建一个学科实体");
        Discipline discipline = new Discipline();
        discipline.setName("discipline");
        discipline.setPinyin("discipline");
        disciplineRepository.save(discipline);

        logger.info("新建一个资格证类别实体A");
        QualifierCertificateType qualifierCertificateTypeA = new QualifierCertificateType();
        qualifierCertificateTypeA.setDiscipline(discipline);
        qualifierCertificateTypeA.setName("qualifierCertificateTypeA");
        qualifierCertificateTypeRepository.save(qualifierCertificateTypeA);

        logger.info("新建一个资格证类别实体B");
        QualifierCertificateType qualifierCertificateTypeB = new QualifierCertificateType();
        qualifierCertificateTypeB.setDiscipline(discipline);
        qualifierCertificateTypeB.setName("qualifierCertificateTypeB");
        qualifierCertificateTypeRepository.save(qualifierCertificateTypeB);

        PageRequest pageRequest = new PageRequest(1,1);

        Page<QualifierCertificateType> qualifierCertificateTypes = qualifierCertificateTypeService.getAllByDisciplineId(discipline.getId(), pageRequest);

        assertThat(qualifierCertificateTypes.getContent().size()).isEqualTo(1);
        assertThat(qualifierCertificateTypes.getTotalPages()).isEqualTo((int)qualifierCertificateTypes.getTotalElements());

        logger.info("删除用于测试的实体");
        qualifierCertificateTypeRepository.delete(qualifierCertificateTypeA);
        qualifierCertificateTypeRepository.delete(qualifierCertificateTypeB);
        disciplineRepository.delete(discipline);

    }

}