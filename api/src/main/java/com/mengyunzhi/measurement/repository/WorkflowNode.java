package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "WorkflowNode (工作流节点)", description = "工作流节点实体")
public class WorkflowNode implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("是否包含子区域字段")
	private boolean isContainSonDistrict = false;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@OneToOne	@ApiModelProperty("上一工作流节点")
	@Lazy()
	@JoinColumn(name = "pre_id")
	private WorkflowNode preWorkflowNode;
	@ManyToOne @ApiModelProperty("创建此工作流节点的用户")
	private User createUser;
	@ManyToOne @ApiModelProperty("区域类型")
	private DistrictType districtType;
	@ManyToOne @ApiModelProperty("工作流类型")
	private WorkflowType workflowType;
	@ManyToOne @ApiModelProperty("部门类型")
	private DepartmentType departmentType;

	public WorkflowNode() {
	}

	public WorkflowNode(String name, boolean isContainSonDistrict, Long createTime, Long updateTime, WorkflowNode preWorkflowNode, User createUser, DistrictType districtType, WorkflowType workflowType, DepartmentType departmentType) {
		this.name = name;
		this.isContainSonDistrict = isContainSonDistrict;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.preWorkflowNode = preWorkflowNode;
		this.createUser = createUser;
		this.districtType = districtType;
		this.workflowType = workflowType;
		this.departmentType = departmentType;
	}

	@Override
	public String toString() {
		return "WorkflowNode{" +
				"id=" + id +
				", name='" + name + '\'' +
				", isContainSonDistrict=" + isContainSonDistrict +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", preWorkflowNode=" + preWorkflowNode +
				", createUser=" + createUser +
				", districtType=" + districtType +
				", workflowType=" + workflowType +
				", departmentType=" + departmentType +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public WorkflowNode getPreWorkflowNode() {
		return preWorkflowNode;
	}

	public void setPreWorkflowNode(WorkflowNode preWorkflowNode) {
		this.preWorkflowNode = preWorkflowNode;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public DistrictType getDistrictType() {
		return districtType;
	}

	public void setDistrictType(DistrictType districtType) {
		this.districtType = districtType;
	}

	public WorkflowType getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(WorkflowType workflowType) {
		this.workflowType = workflowType;
	}

	public DepartmentType getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(DepartmentType departmentType) {
		this.departmentType = departmentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isContainSonDistrict() {
		return isContainSonDistrict;
	}

	public void setContainSonDistrict(boolean containSonDistrict) {
		isContainSonDistrict = containSonDistrict;
	}

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}
}
