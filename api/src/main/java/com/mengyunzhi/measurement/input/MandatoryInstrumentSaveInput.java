package com.mengyunzhi.measurement.input;

import com.mengyunzhi.measurement.repository.*;

/**
 * Created by panjie on 17/7/13.
 * 强检器具新增
 */
public class MandatoryInstrumentSaveInput {
    private MandatoryInstrument mandatoryInstrument;        // 强检器具信息
    private WorkflowNode currentWorkflowNode;               // 当前工作流结点
    private WorkflowNode nextWorkflowNode;                  // 选择的下一工作流结点
    private Department auditDepartment;                     // 审核部门
    private MandatoryInstrumentApply Apply;                 // 强检申请(包含联系人，联系方式等信息)
    private Work work;                                      // 审核意见(申请时出具的申请信息)
    public MandatoryInstrumentSaveInput() {
    }

    public MandatoryInstrument getMandatoryInstrument() {
        return mandatoryInstrument;
    }


    public void setMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
        this.mandatoryInstrument = mandatoryInstrument;
    }

    public WorkflowNode getCurrentWorkflowNode() {
        return currentWorkflowNode;
    }

    public void setCurrentWorkflowNode(WorkflowNode currentWorkflowNode) {
        this.currentWorkflowNode = currentWorkflowNode;
    }

    public WorkflowNode getNextWorkflowNode() {
        return nextWorkflowNode;
    }

    public void setNextWorkflowNode(WorkflowNode nextWorkflowNode) {
        this.nextWorkflowNode = nextWorkflowNode;
    }

    public Department getAuditDepartment() {
        return auditDepartment;
    }

    public void setAuditDepartment(Department auditDepartment) {
        this.auditDepartment = auditDepartment;
    }

    public MandatoryInstrumentApply getApply() {
        return Apply;
    }

    public void setApply(MandatoryInstrumentApply apply) {
        Apply = apply;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
