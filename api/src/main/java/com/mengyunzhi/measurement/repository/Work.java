package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.controller.BaseJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@ApiModel(value = "Work (工作)", description = "工作实体")
public class Work implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @ApiModelProperty("上一工作")
    @JoinColumn(name = "pre_id")
    @Lazy
    private Work preWork;
    @OneToOne
    @ApiModelProperty("工作的别名")
    @JoinColumn(name = "alias")
    @Lazy
    private Work aliasWork;
    @ApiModelProperty("审核意见")
    private String opinion;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间")
    @UpdateTimestamp
    private Calendar updateTime;
    @ApiModelProperty("点击时间")
    private Calendar clickTime;
    @ApiModelProperty("待办")
    private Boolean todo = true;
    @ApiModelProperty("在办")
    private Boolean doing = false;
    @ApiModelProperty("已办")
    private Boolean done = false;
    @ManyToOne
    @ApiModelProperty("关联申请实体")
    @JsonView({NoneJsonView.class, MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class})
    private Apply apply;

    @ManyToOne
    @ApiModelProperty("关联工作流节点实体")
    private WorkflowNode workflowNode;
    @ManyToOne
    @ApiModelProperty("需要审核的部门")
    private Department auditDepartment;

    @ManyToOne
    @ApiModelProperty("审核人")
    private User auditUser;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Department getAuditDepartment() {
        return auditDepartment;
    }

    public void setAuditDepartment(Department auditDepartment) {
        this.auditDepartment = auditDepartment;
    }

    public User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(User auditUser) {
        this.auditUser = auditUser;
    }

    public Work() {
    }

    public Work(Work preWork, Work aliasWork, String opinion, Calendar createTime, Calendar updateTime, Calendar clickTime, Boolean todo, Boolean doing, Boolean done, Apply apply, WorkflowNode workflowNode, Department auditDepartment, User auditUser) {
        this.preWork = preWork;
        this.aliasWork = aliasWork;
        this.opinion = opinion;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.clickTime = clickTime;
        this.todo = todo;
        this.doing = doing;
        this.done = done;
        this.apply = apply;
        this.workflowNode = workflowNode;
        this.auditDepartment = auditDepartment;
        this.auditUser = auditUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Work getPreWork() {
        return preWork;
    }

    public void setPreWork(Work preWork) {
        this.preWork = preWork;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public Calendar getClickTime() {
        return clickTime;
    }

    public void setClickTime(Calendar clickTime) {
        this.clickTime = clickTime;
    }

    public Boolean getTodo() {
        return todo;
    }

    public void setTodo(Boolean todo) {
        this.todo = todo;
        if (todo) {
            this.setDoing(false);
            this.setDone(false);
        }
    }

    public Boolean getDoing() {
        return doing;
    }

    public void setDoing(Boolean doing) {
        this.doing = doing;
        if (doing) {
            this.setTodo(false);
            this.setDone(false);
        }
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
        if (done) {
            this.setTodo(false);
            this.setDoing(false);
        }
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public WorkflowNode getWorkflowNode() {
        return workflowNode;
    }

    public void setWorkflowNode(WorkflowNode workflowNode) {
        this.workflowNode = workflowNode;
    }

    public Work getAliasWork() {
        return aliasWork;
    }

    public void setAliasWork(Work aliasWork) {
        this.aliasWork = aliasWork;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", preWork=" + preWork +
                ", aliasWork=" + aliasWork +
                ", opinion='" + opinion + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", clickTime=" + clickTime +
                ", todo=" + todo +
                ", doing=" + doing +
                ", done=" + done +
                ", apply=" + apply +
                ", workflowNode=" + workflowNode +
                ", auditDepartment=" + auditDepartment +
                ", auditUser=" + auditUser +
                '}';
    }
}
