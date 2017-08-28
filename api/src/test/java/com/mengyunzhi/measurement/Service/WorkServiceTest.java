package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-15.
 * 工作
 */
public class WorkServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(WorkServiceTest.class.getName());
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected WorkRepository workRepository;
    @Autowired @Qualifier("WorkService") protected WorkService workService;
    @Autowired private ApplyRepository applyRepository; // 申请
    @Autowired @Qualifier("ApplyService") private ApplyService applyService;       // 申请
    @Autowired private UserRepository userRepository;       // 用户
    @Autowired private UserService  userService;            // 用户
    @Autowired private WorkflowNodeRepository workflowNodeRepository;       // 工作流结点
    private User user;
    @Before
    public void dataInit() {
        logger.info("登录用户");
        user = userService.loginWithOneUser();
    }

    @After
    public void deleteData () {
        userRepository.delete(user);
    }
    @Test
    public void pageDistinctApplyByDepartmentOfMandatoryInstrument() throws Exception {
        logger.info("新建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("新建一个强检申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("新建一个工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        work.setAuditDepartment(department);
        workRepository.save(work);

        logger.info("测试数据");
        final PageRequest pageRequest = new PageRequest(1,1);
        Page<Apply> page = workService.pageDistinctApplyByDepartmentOfMandatoryInstrument(department, "MandatoryInstrument", pageRequest);
        logger.info("断言");
        assertThat(page.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        workRepository.delete(work);
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
        departmentRepository.delete(department);
    }

    @Test
    public void pageByDepartmentOfMandatoryInstrumentGroupByApply() {
        PageRequest pageRequest = new PageRequest(1, 1);
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);
        Page<Work> works = workService.pageByDepartmentOfMandatoryInstrumentGroupByApply(department, pageRequest);
        assertThat(works.getTotalElements()).isEqualTo(0);
    }

    @Test
    public void updateToDoingByWorkId() {
        logger.info("新建一个工作，先保存，然后设置为在办");
        Work work = WorkService.getOneWork();
        work.setTodo(true);
        workRepository.save(work);
        workService.updateToDoingByWork(work);
        assertThat(work.getDoing()).isTrue();
        assertThat(work.getTodo()).isFalse();

        workService.updateToDoingByWorkId(work.getId());
        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getDoing()).isTrue();
        assertThat(work1.getTodo()).isFalse();

        logger.info("查找不存在的，断言发生异常");
        Long id = -1L;
        Boolean getException = false;
        try {
            workService.updateToDoingByWorkId(id);
        } catch (ObjectNotFoundException e) {
            getException = true;
        }

        assertThat(getException).isTrue();
        workRepository.delete(work);
    }

    @Test
    public void updateToDoneByWorkId() {
        logger.info("新建一个工作，并设置为完成，然后断言");
        Work work = WorkService.getOneWork();
        workRepository.save(work);
        workService.updateToDoneByWorkId(work.getId());
        assertThat(work.getDone()).isTrue();
        workRepository.delete(work);
    }

    @Test
    public void doneByWork() {

        logger.info("新建工作及其对应的申请");
        Apply apply = applyService.getOneApply();
        applyRepository.save(apply);
        Work work = WorkService.getOneWork();
        work.setApply(apply);
        workRepository.save(work);

        logger.info("设置为办结");
        workService.doneByWork(work);

        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getDone()).isTrue();
        assertThat(work1.getApply().getDone()).isTrue();

        logger.info("删除测试数据");
        applyRepository.delete(apply);
        workRepository.delete(work);
    }

    /**
     * 退回提交人
     */
    @Test
    public void backToPreDepartment() {
        logger.info("新建部门");
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("新建申请");
        Apply apply = applyService.getOneApply();
        applyRepository.save(apply);

        logger.info("新建两个工作");
        Work work = WorkService.getOneWork();
        work.setApply(apply);
        work.setAuditDepartment(department);
        workRepository.save(work);
        Work work1 = WorkService.getOneWork();
        work1.setApply(apply);
        work1.setPreWork(work);
        workRepository.save(work1);

        logger.info("新建用户，并设置为当前用户");
        User user = UserService.getOneUser();
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("调用退回上一审核部门方法");
        workService.backToPreDepartment(work1);

        logger.info("获取并断言数据");
        Work work2 = workRepository.findOneByPreWork(work1);
        assertThat(work2.getTodo()).isTrue();
        assertThat(work2.getAliasWork().getId()).isEqualTo(work.getId());
        assertThat(work2.getAuditDepartment().getId()).isEqualTo(department.getId());

        logger.info("删除测试数据");
        workRepository.delete(work2);
        workRepository.delete(work1);
        workRepository.delete(work);
        applyRepository.delete(apply);
        departmentRepository.delete(department);
        userRepository.delete(user);
    }

    // 送下一审核部门
    @Test
    public void sendToNextDepartment() {
        logger.info("新建一一个申请");
        Apply apply = applyService.getOneApply();
        applyRepository.save(apply);
        logger.info("新建3个部门");
        Department department = DepartmentService.getOneDepartment();
        departmentRepository.save(department);
        Department department1 = DepartmentService.getOneDepartment();
        departmentRepository.save(department1);
        Department department2 = DepartmentService.getOneDepartment();
        departmentRepository.save(department2);

        logger.info("新建一个工作流结点");
        WorkflowNode workflowNode = WorkflowNodeService.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);

        Work work = WorkService.getOneWork();
        work.setAuditDepartment(department);
        work.setApply(apply);
        workRepository.save(work);

        logger.info("登录用户");
        User user = userService.loginWithOneUser();

        logger.info("审核当前工作，并送下一部门");
        workService.sendToNextDepartment(work, department1, workflowNode);
        Work work1 = workRepository.findOneByPreWork(work);
        assertThat(work1.getAuditDepartment().getId()).isEqualTo(department1.getId());
        assertThat(work1.getPreWork().getId()).isEqualTo(work.getId());
        assertThat(work1.getWorkflowNode().getId()).isEqualTo(workflowNode.getId());

        logger.info("再送下一部门");
        workService.sendToNextDepartment(work1, department2, workflowNode);
        Work work2 = workRepository.findOneByPreWork(work1);
        assertThat(work2.getAuditDepartment().getId()).isEqualTo(department2.getId());

        logger.info("退回给上一部门");
        workService.backToPreDepartment(work2);
        Work work3 = workRepository.findOneByPreWork(work2);
        assertThat(work3.getAuditDepartment().getId()).isEqualTo(department1.getId());

        logger.info("退回上一申请部门");
        workService.backToApplyDepartment(work3);
        Work work4 = workRepository.findOneByPreWork(work3);
        assertThat(work4.getAuditDepartment().getId()).isEqualTo(department.getId());


        workRepository.delete(work4);
        workRepository.delete(work3);
        workRepository.delete(work2);
        workRepository.delete(work1);
        workRepository.delete(work);
        userRepository.delete(user);
        workflowNodeRepository.delete(workflowNode);
        departmentRepository.delete(department);

    }

    @Test
    public void saveNewWork() {
        Work work = WorkService.getOneWork();
        User user = userService.loginWithOneUser();
        workService.saveNewWork(work);

        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getAuditDepartment().getId()).isEqualTo(user.getDepartment().getId());
        assertThat(work1.getDoing()).isEqualTo(true);

    }


}