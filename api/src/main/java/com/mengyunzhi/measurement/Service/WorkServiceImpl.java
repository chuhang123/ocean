package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by liming on 17-7-14.
 * 工作
 */
@Service("WorkService")
public class WorkServiceImpl implements WorkService {
    private Logger logger = Logger.getLogger(WorkServiceImpl.class.getName());
    @Autowired
    protected WorkRepository workRepository;
    @Autowired @org.springframework.beans.factory.annotation.Qualifier("ApplyService")
    private ApplyService applyService;       // 申请
    @Autowired
    private UserService userService;         // 用户

    @Override
    public Page<Apply> pageDistinctApplyByDepartmentOfMandatoryInstrument(Department department, String applyClassName, Pageable pageable) {
        Page<Apply> page = workRepository.findDistinctApplyByDepartmentAndApplyTableName(department, applyClassName, pageable);
        return page;
    }

    @Override
    public Page<Work> pageByDepartmentOfMandatoryInstrumentGroupByApply(Department department, Pageable pageable) {
        String className = "MandatoryInstrument";
        Page<Work> works = workRepository.pageByDepartmentAndApplyClassNameOrderByIdDescGroupByApply(department, className, pageable);
        return works;
    }

    @Override
    public void updateToDoingByWork(Work work) {
        if (!work.getDone()) {
            logger.info("更新实体状态为在办,设置审核人");
            work.setTodo(false);
            work.setDoing(true);
            work.setAuditUser(userService.getCurrentLoginUser());
            workRepository.save(work);
        } else {
            logger.info("实体状态为办结，未能更新为在办");
        }
    }

    @Override
    public void updateToDoingByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw new ObjectNotFoundException(404, "id为" + id.toString() + "的Work实体未找到");
        }
        this.updateToDoingByWork(work);
    }

    @Override
    public void updateToDoneByWork(Work work) {
        work.setTodo(false);
        work.setDoing(false);
        work.setDone(true);
        User user = userService.getCurrentLoginUser();
        work.setAuditUser(user);
        workRepository.save(work);
    }

    @Override
    public void updateToDoneByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw new ObjectNotFoundException(404, "id为" + id.toString() + "的Work实体未找到");
        }
        this.updateToDoneByWork(work);
    }

    @Override
    public void doneByWork(Work work) {
        logger.info("办结本工作");
        this.updateToDoneByWork(work);
        logger.info("办结工作对应的申请");
        applyService.updateToDoneByApply(work.getApply());
    }

    /**
     * 退回申请人，谁当年申请给我的，我退回给谁
     * @param work
     * @author panjie
     */
    @Override
    public void backToApplyDepartment(Work work) {
        logger.info("办结本工作");
        this.updateToDoneByWork(work);
        if (work.getAliasWork() == null) {
            logger.info("上一申请人即是上一提交人，直接调用退回提交人的方法");
            this.backToPreDepartment(work);
        } else {
            logger.info("获取源工作节点");
            Work originWork = this.getOriginWorkByWork(work);
            Work nextWork = new Work();
            this.cloneWork(nextWork, originWork.getPreWork());
            nextWork.setPreWork(work);
            workRepository.save(nextWork);
            logger.info("更新待/在办部门");
            applyService.updateAuditingDepartment(work.getApply(), nextWork.getAuditDepartment());
        }
    }

    /**
     * 获取起始工作流
     *
     * @param work
     * @return work
     * @author panjie
     */
    public Work getOriginWorkByWork(Work work) {
        if (work.getAliasWork() == null) {
            return work;
        } else {
            return this.getOriginWorkByWork(work.getAliasWork());
        }
    }

    @Override
    public Work getOriginWorkByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw  new ObjectNotFoundException(404, "未找到ID为" + id.toString() +"的工作(Work)实体");
        }

        return this.getOriginWorkByWork(work);
    }

    /**
     * 退回提交人
     *
     * @param work
     */
    @Override
    public void backToPreDepartment(Work work) {
        logger.info("办结本工作");
        this.updateToDoneByWork(work);
        logger.info("新建工作");
        Work nextWork = new Work();
        this.cloneWork(nextWork, work.getPreWork());
        nextWork.setPreWork(work);
        workRepository.save(nextWork);
        logger.info("更新待/在办部门");
        applyService.updateAuditingDepartment(work.getApply(), nextWork.getAuditDepartment());
    }

    /**
     * 送下一审核部门
     * @param work
     * @param department 审核部门
     * @author panjie
     */
    @Override
    public void sendToNextDepartment(Work work, Department department, WorkflowNode workflowNode) {
        logger.info("办结本工作");
        this.updateToDoneByWork(work);
        logger.info("更新待/在办部门");
        applyService.updateAuditingDepartment(work.getApply(), department);
        logger.info("新建下一工作");
        Work nextWork = new Work();
        nextWork.setPreWork(work);
        nextWork.setWorkflowNode(workflowNode);
        nextWork.setAuditDepartment(department);
        nextWork.setApply(work.getApply());
        workRepository.save(nextWork);
    }

    /**
     * 审核工作
     * @param type 类型：送申请人 送提交人 办结 送下一审核人
     * @param work 工作
     * @param department 审核部门
     * @param workflowNode 对接的工作流
     */
    @Override
    public void auditByTypeAndWorkAndDepartmentAndWorkflowNode(String type, Work work, Department department, WorkflowNode workflowNode) {
        switch (type) {
            case WorkService.SEND_TO_NEXT_DEPARTMENT:
                this.sendToNextDepartment(work, department, workflowNode);
                break;
            case WorkService.BACK_TO_APPLY_DEPARTMENT:
                this.backToApplyDepartment(work);
                break;
            case WorkService.BACK_TO_PRE_DEPARTMENT:
                this.backToPreDepartment(work);
                break;
            default:
                this.doneByWork(work);
        }
    }


    @Override
    public List<Work> getAllByApplyId(Long applyId) {
        return workRepository.findAllByApplyId(applyId);
    }

    @Override
    public Work saveNewWork(Work work) {
        work.setDoing(true);
        User user = userService.getCurrentLoginUser();
        work.setAuditDepartment(user.getDepartment());
        workRepository.save(work);
        return work;
    }

    @Override
    public void auditByWorkIdAndOpinionAndAuditDepartmentAndAuditTypeAndWorkflowNode(Long workId, String opinion, Department department, String auditType, WorkflowNode workflowNode) {
        Work work = workRepository.findOne(workId);
        if (null == work) {
            throw new ObjectNotFoundException(404, "查找的work工作实体不存在: " + workId.toString());
        }

        if (work.getDone()) {
            throw new SecurityException("该工作已办结，无法更改状态");
        }

        User user = userService.getCurrentLoginUser();
        if (user.getDepartment().getId() != work.getAuditDepartment().getId()) {
            throw new SecurityException("您并不属于该工作的当前待/在办部门，无此操作权限");
        }

        logger.info("设置审核意见");
        work.setOpinion(opinion);

        this.auditByTypeAndWorkAndDepartmentAndWorkflowNode(auditType, work, department, workflowNode);
    }

    protected void cloneWork(Work targetWork, Work originWork) {
        logger.info("设置别名，设置上一工作，设置申请部门，设置申请类型，设置审核部门, 设置待办, 设置工作流结点");
        targetWork.setAliasWork(originWork);
        targetWork.setAuditDepartment(originWork.getAuditDepartment());
        targetWork.setApply(originWork.getApply());
        targetWork.setTodo(true);
        targetWork.setWorkflowNode(originWork.getWorkflowNode());
    }
}
