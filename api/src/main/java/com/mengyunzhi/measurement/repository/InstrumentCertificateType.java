package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "InstrumentCertificateType (器具证书类型)", description = "器具证书类型")
public class InstrumentCertificateType implements Serializable{
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
	@ManyToOne @ApiModelProperty("创建此证书类型的用户")
	private User CreateUser;

	public InstrumentCertificateType() {
	}

	public InstrumentCertificateType(String name, String pinyin, Long createTime, Long updateTime, User createUser) {
		this.name = name;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		CreateUser = createUser;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	@Override
	public String toString() {
		return "InstrumentCertificateType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				'}';
	}
}
