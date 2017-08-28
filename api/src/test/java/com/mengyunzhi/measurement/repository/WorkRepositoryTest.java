package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.DepartmentService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentApplyService;
import com.mengyunzhi.measurement.Service.WorkService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-22.
 */
public class WorkRepositoryTest extends RepositoryTest{
    @Autowired
    private WorkRepository workRepository;
    static private Logger logger = Logger.getLogger(WorkRepositoryTest.class.getName());
    @Autowired private DepartmentRepository departmentRepository;       // 部门
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强检器具申请
    @Autowired protected ApplyRepository applyRepository;   // 申请

    @Test
    public void save() {
        logger.info("新建一个工作");
        Work work = new Work();
        logger.info("保存");
        workRepository.save(work);
        logger.info("断言");
        assertThat(work.getId()).isNotNull();
    }

    @Test
    public void findAllByDepartmentIdAndApplyTableName() {
        PageRequest pageRequest = new PageRequest(1,1);
        Department department = departmentRepository.findOneByPinyin("安次区管理部门");
        workRepository.findDistinctApplyByDepartmentAndApplyTableName(department,  "MandatoryInstrument", pageRequest);
        return;
    }

    @Test
    public void pageByDepartmentAndApplyClassNameOrderByIdDescGroupByApply() {
        logger.info("建立一个部门");
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("新建一个强检器具申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = MandatoryInstrumentApplyService.getOneMandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("根据强检申请，取出一个申请");
        Apply apply = applyRepository.findOne(mandatoryInstrumentApply.getId());

        logger.info("在这个申请上建立两个工作");
        Work work = WorkService.getOneWork();
        work.setApply(apply);
        Work work1 = WorkService.getOneWork();
        work1.setApply(apply);
        logger.info("把其中的一个工作给这个部门");
        work1.setAuditDepartment(department);
        workRepository.save(work);
        workRepository.save(work1);


        logger.info("再建立一个强检器具申请");
        MandatoryInstrumentApply mandatoryInstrumentApply1 = MandatoryInstrumentApplyService.getOneMandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply1);

        logger.info("再据强检申请，取出一个申请");
        Apply apply1 = applyRepository.findOne(mandatoryInstrumentApply1.getId());
        logger.info("再在这个申请上建立两个工作");
        Work work2 = WorkService.getOneWork();
        work2.setApply(apply1);
        Work work3 = WorkService.getOneWork();
        work3.setApply(apply1);
        logger.info("再把其中一个工作给这个部门");
        work2.setAuditDepartment(department);
        workRepository.save(work2);
        workRepository.save(work3);

        logger.info("新建一个 分页");
        PageRequest pageRequest = new PageRequest(1,1);

        logger.info("调用方法");
        Page<Work> works = workRepository.pageByDepartmentAndApplyClassNameOrderByIdDescGroupByApply(department, "MandatoryInstrument", pageRequest);

        logger.info("断言取出两条数据");
        assertThat(works.getTotalPages()).isEqualTo(2);
        assertThat(works.getTotalElements()).isEqualTo(2);
    }
}