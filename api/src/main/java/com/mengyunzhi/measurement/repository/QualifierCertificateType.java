package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 名称有：检定员证，核验员证，批准人证
 */
@Entity
@ApiModel(value = "QualifierCertificateType (资格证类别)", description = "资格证类别实体")
public class QualifierCertificateType {
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
	@ManyToOne @ApiModelProperty("操作此实体的用户")
	private User CreateUser;
	@ManyToOne @ApiModelProperty("学科类别")
	private Discipline discipline;

	public QualifierCertificateType(String name, String pinyin, Long createTime, Long updateTime, User createUser, Discipline discipline) {
		this.name = name;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		CreateUser = createUser;
		this.discipline = discipline;
	}

	public QualifierCertificateType() {
	}

	@Override
	public String toString() {
		return "QualifierCertificateType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				", discipline=" + discipline +
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

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
}
