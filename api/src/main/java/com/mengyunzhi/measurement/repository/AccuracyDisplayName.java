package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(value = "AccuracyDisplayName (等级准确度示值偏差显示名称)", description = "等级准确度示值偏差显示名称实体")
public class AccuracyDisplayName {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@ManyToOne @ApiModelProperty("创建此实体的用户")
	private User CreateUser;

	public AccuracyDisplayName() {
	}

	public AccuracyDisplayName(String name, String pinyin, Long createTime, Long updateTime, User createUser) {
		this.name = name;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		CreateUser = createUser;
	}

	@Override
	public String toString() {
		return "AccuracyDisplayName{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				'}';
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

	public User getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(User createUser) {
		CreateUser = createUser;
	}
}
