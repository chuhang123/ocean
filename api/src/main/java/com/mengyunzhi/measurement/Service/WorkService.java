package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Apply;
import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.Work;
import com.mengyunzhi.measurement.repository.WorkflowNode;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by panjie on 17/7/13.
 * 工作
 */
public interface WorkService {
    String SEND_TO_NEXT_DEPARTMENT = "sendToNextDepartment";        // 送下一审核部门
    String BACK_TO_APPLY_DEPARTMENT = "backToApplyDepartment";      // 送申请部门
    String BACK_TO_PRE_DEPARTMENT = "backToPreDepartment";          // 送上一审核部门
    String DONE = "done";

    static Work getOneWork() {
        Work work = new Work();
        work.setOpinion("审核意见");
        return work;
    }

    Page<Apply> pageDistinctApplyByDepartmentOfMandatoryInstrument(Department department, String applyClassName, Pageable pageable);

    /**
     * 获取某个部门的强制检定器具申请工作列表
     *
     * @param department
     * @param pageable
     * @return
     * @author panjie
     */
    Page<Work> pageByDepartmentOfMandatoryInstrumentGroupByApply(Department department, Pageable pageable);

    /**
     * 更新工作状态到 在办
     *
     * @param work
     * @author panjie
     */
    void updateToDoingByWork(Work work);

    void updateToDoingByWorkId(Long id) throws ObjectNotFoundException;

    /**
     * 将某项工作设置为办结
     *
     * @param work
     * @author panjie
     */
    void updateToDoneByWork(Work work);

    void updateToDoneByWorkId(Long id) throws ObjectNotFoundException;

    /**
     * 办结某项工作(同时办结相关的申请)
     *
     * @param work 工作
     * @author panjie
     */
    void doneByWork(Work work);

    /**
     * 退回申请人(即上一结点)
     *
     * @param work
     * @author panjie
     */
    void backToApplyDepartment(Work work);

    /**
     * 退回提交人 谁点给我的，我就回点给他
     *
     * @param work
     * @author
     */
    void backToPreDepartment(Work work);

    /**
     * 获取起始工作
     *
     * @param work
     * @return work
     * @author panjie
     */
    Work getOriginWorkByWork(Work work);

    Work getOriginWorkByWorkId(Long id) throws ObjectNotFoundException;

    /**
     * 分送下一审核部门
     *
     * @param work
     * @param department   审核部门
     * @param workflowNode 工作流结点
     * @author panjie
     */
    void sendToNextDepartment(Work work, Department department, WorkflowNode workflowNode);

    /**
     * 审核工作
     *
     * @param type         类型：送申请人 送提交人 办结 送下一审核人
     * @param work         工作
     * @param department   审核部门
     * @param workflowNode 对接的工作流
     */
    void auditByTypeAndWorkAndDepartmentAndWorkflowNode(String type, Work work, Department department, WorkflowNode workflowNode);

    List<Work> getAllByApplyId(Long applyId);

    // 保存一个新工作。只保存审核部门，但不保存审核用户。工作的状态设置为在办。
    Work saveNewWork(Work work);

    void auditByWorkIdAndOpinionAndAuditDepartmentAndAuditTypeAndWorkflowNode(Long workId,
                                                                              String opinion,
                                                                              Department department,
                                                                              String auditType,
                                                                              WorkflowNode workflowNode)
            throws ObjectNotFoundException, SecurityException;
}