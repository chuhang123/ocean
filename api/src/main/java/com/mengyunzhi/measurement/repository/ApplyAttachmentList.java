package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(value = "ApplyAttachmentList (申请附件列表)", description = "每种申请要求用户上传的附件列表")
public class ApplyAttachmentList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@ApiModelProperty("申请类型")
	private ApplyType applyType;

	@ManyToOne
	@ApiModelProperty("模板附件")
	private Attachment attachment;

	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty("名称拼音")
	private String pinyin;

	@ApiModelProperty("操作用户")
	@ManyToOne
	private User createUser;

	@ApiModelProperty("创建时间")
	private Long createTime;

	@ApiModelProperty("更新时间")
	private Long updateTime;

	public ApplyAttachmentList() {
	}

	public ApplyAttachmentList(ApplyType applyType, Attachment attachment, String name,
							   String pinyin, User createUser,
							   Long createTime, Long updateTime) {
		this.applyType = applyType;
		this.attachment = attachment;
		this.name = name;
		this.pinyin = pinyin;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	public ApplyType getApplyType() {
		return applyType;
	}

	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "ApplyAttachmentList{" +
				"id=" + id +
				", applyType=" + applyType +
				", attachment=" + attachment +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createUser=" + createUser +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}