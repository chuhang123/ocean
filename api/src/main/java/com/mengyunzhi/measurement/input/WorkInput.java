package com.mengyunzhi.measurement.input;

import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.WorkflowNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by panjie on 17/7/31.
 * 工作输入
 */
@ApiModel("WorkInput 工作输入")
public class WorkInput {
    @ApiModel("WorkInput.AuditById 用户审核工作时发送的数据")
    static public class AuditByIdInput{
        @ApiModelProperty("审核意见")
        private String opinion;
        @ApiModelProperty("选择的工作流结点")
        protected WorkflowNode nextWorkflowNode;
        @ApiModelProperty("审核类型：办结，done; 送上一申请人，sendToPreDepartment; 送上一审核人， sendToPreWorkflowNodeDepartment; 送下一审核人, sendToNextWorkflowNodeDepartment")
        protected String type;
        @ApiModelProperty("审核部门")
        protected Department department;

        public AuditByIdInput() {
        }

        public WorkflowNode getNextWorkflowNode() {
            return nextWorkflowNode;
        }

        public void setNextWorkflowNode(WorkflowNode nextWorkflowNode) {
            this.nextWorkflowNode = nextWorkflowNode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public String getOpinion() {

            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }
    }
}
