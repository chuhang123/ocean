package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Inheritance = 继承
@DiscriminatorColumn(name = "table_type")
@ApiModel(value = "Apply (申请)", description = "申请实体")
public class Apply implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("电话")
	private String contactNumber;
	@ApiModelProperty("邮政编码")
	private String postalCode;
	@ApiModelProperty("申请时间") @CreationTimestamp
	private Calendar applyTime;
	@ApiModelProperty("更新时间") @UpdateTimestamp
	private Calendar updateTime;
	@ApiModelProperty("是否办结")
	private Boolean isDone = false;
	@ApiModelProperty("联系人姓名")
	private String contactName;
	@ManyToOne @ApiModelProperty("创建此申请的用户")
	private User createUser;
	@ApiModelProperty("申请类型")
	@ManyToOne
	private ApplyType applyType;
	@ApiModelProperty("申请部门")
	@ManyToOne
	private Department department;
	@ApiModelProperty("在/待办部门")
	@ManyToOne
	private Department auditingDepartment;

	public Apply() {
	}

	@Override
	public String toString() {
		return "Apply{" +
				"id=" + id +
				", name='" + name + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				", postalCode='" + postalCode + '\'' +
				", applyTime=" + applyTime +
				", updateTime=" + updateTime +
				", isDone=" + isDone +
				", contactName='" + contactName + '\'' +
				", createUser=" + createUser +
				", applyType=" + applyType +
				", department=" + department +
				", auditingDepartment=" + auditingDepartment +
				'}';
	}

	public Department getAuditingDepartment() {
		return auditingDepartment;
	}

	public void setAuditingDepartment(Department auditingDepartment) {
		this.auditingDepartment = auditingDepartment;
	}

	public ApplyType getApplyType() {
		return applyType;
	}

	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Calendar getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Calendar applyTime) {
		this.applyTime = applyTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDone() {
		return isDone;
	}

	public void setDone(Boolean done) {
		isDone = done;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
}
