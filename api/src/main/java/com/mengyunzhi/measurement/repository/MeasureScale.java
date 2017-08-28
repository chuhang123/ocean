package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.DeviceInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 测量范围  实体
 */
@Entity
@ApiModel(value = "MeasureScale (测量范围)", description = "测量范围实体")
public class MeasureScale implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("值")
	private String value;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@ManyToOne @ApiModelProperty("创建用户")
	private User createUser;
	@ManyToOne
	@ApiModelProperty("关联器具类别")
	@JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
	private InstrumentType instrumentType;
	@OneToMany
	@ApiModelProperty("对应的机构授权鉴定项目")
	@JsonView(NoneJsonView.class)
	private Set<DeviceInstrument> deviceInstrumentSet = new HashSet<DeviceInstrument>();

	public MeasureScale() {
	}

	public MeasureScale(String value, String pinyin, Long createTime, Long updateTime, User createUser, InstrumentType instrumentType, Set<DeviceInstrument> deviceInstrumentSet) {
		this.value = value;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
		this.instrumentType = instrumentType;
		this.deviceInstrumentSet = deviceInstrumentSet;
	}

	@Override
	public String toString() {
		return "MeasureScale{" +
				"id=" + id +
				", value='" + value + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUser=" + createUser +
				", instrumentType=" + instrumentType +
				", deviceInstrumentSet=" + deviceInstrumentSet +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public InstrumentType getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(InstrumentType instrumentType) {
		this.instrumentType = instrumentType;
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

	public Set<DeviceInstrument> getDeviceInstrumentSet() {
		return deviceInstrumentSet;
	}

	public void setDeviceInstrumentSet(Set<DeviceInstrument> deviceInstrumentSet) {
		this.deviceInstrumentSet = deviceInstrumentSet;
	}
}
