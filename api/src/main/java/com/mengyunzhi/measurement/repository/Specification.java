package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "Specification (规格型号)", description = "规格型号")
public class Specification implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("应收费用 单位:分")
	private Long cost;
	@ApiModelProperty("值")
	@Column(nullable = false)
	private String value;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("修改时间")
	private Long updateTime;
	@ApiModelProperty("创建用户")
	@ManyToOne
	private User CreateUser;
	@ApiModelProperty("器具类别")
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private InstrumentType instrumentType;

	public Specification() {
	}

	@Override
	public String toString() {
		return "Specification{" +
				"id=" + id +
				", cost=" + cost +
				", value='" + value + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				", instrumentType=" + instrumentType +
				'}';
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
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

	public InstrumentType getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(InstrumentType instrumentType) {
		this.instrumentType = instrumentType;
	}
}
