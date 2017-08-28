package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel(value = "Discipline (学科)", description = "学科实体")
public class Discipline implements Serializable{
	private static final long serialVersionUID = 1L;
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
	@ApiModelProperty("创建学科的用户")
	@ManyToOne
	private User CreateUser;

	@ApiModelProperty("精度")
	@OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
	private Set<Accuracy> accuracies = new HashSet<Accuracy>();

	public Discipline() {
	}

	@Override
	public String toString() {
		return "Discipline{" +
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

	public Set<Accuracy> getAccuracies() {
		return accuracies;
	}

	public void setAccuracies(Set<Accuracy> accuracies) {
		this.accuracies = accuracies;
	}

	public void addAccruacy(Accuracy accuracy) {
		this.accuracies.add(accuracy);
	}

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}
}
