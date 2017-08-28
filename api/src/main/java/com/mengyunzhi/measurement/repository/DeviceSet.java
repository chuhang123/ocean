package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.DeviceSetJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
/**
 * 计量标准装置  实体
 */
@Entity @ApiModel(value = "DeviceSet (计量标准装置)", description = "计量标准装置实体")
public class DeviceSet implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	@ApiModelProperty("对应的装置授权检定项目 http://www.mengyunzhi.cn/2017/08/09/study-jpa-ManyToMany-deeply/")
	@JsonView({NoneJsonView.class, DeviceSetJsonView.ToDeviceInstrument.class})
	@JoinTable(
			name = "device_set_device_instruments",
			joinColumns = {@JoinColumn(name = "device_set_id", referencedColumnName = "id")},
			inverseJoinColumns = {
					@JoinColumn(name = "accuracy_id", referencedColumnName = "accuracy_id"),
					@JoinColumn(name = "measure_scale_id", referencedColumnName = "measure_scale_id")
			}
	)
	private Set<DeviceInstrument> deviceInstruments = new HashSet<DeviceInstrument>();

	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("代码")
	private String code;
	@ApiModelProperty("考核证书编号")
	private String certificateNum;
	@ApiModelProperty("考核日期")
	private Date checkDate;
	@ApiModelProperty("颁发日期")
	private Date issueDate;
	@ApiModelProperty("有效日期")
	private Date validityDate;
	@ApiModelProperty("报警日期")
	private Date alertDate;
	@ApiModelProperty("拼音")
	private String pinyin;

	/**
	 * 自动添加创建时间，更新时间
	 * 以下文章中，给出四种实现方法
	 * 引用：https://stackoverflow.com/questions/40969919/hibernate-5-auto-updated-timestamp-field-for-last-modified
	 * 本文采用第四种进行实现
	 */
	@ApiModelProperty("创建时间")
	@CreationTimestamp
	private Calendar createTime;
	@ApiModelProperty("更新时间")
	@UpdateTimestamp
	private Calendar updateTime;
	@ManyToOne @ApiModelProperty("关联的用户实体")
	private User createUser;


	@ManyToOne
	@ApiModelProperty("对应的部门")
	private Department department;

	public DeviceSet() {
	}

	public DeviceSet(String name, String code, String certificateNum, Date checkDate, Date issueDate, Date validityDate, Date alertDate, String pinyin, Calendar createTime, Calendar updateTime, User createUser, Set<DeviceInstrument> deviceInstruments, Department department) {
		this.name = name;
		this.code = code;
		this.certificateNum = certificateNum;
		this.checkDate = checkDate;
		this.issueDate = issueDate;
		this.validityDate = validityDate;
		this.alertDate = alertDate;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
		this.deviceInstruments = deviceInstruments;
		this.department = department;
	}

	@Override
	public String toString() {
		return "DeviceSet{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", certificateNum='" + certificateNum + '\'' +
				", checkDate=" + checkDate +
				", issueDate=" + issueDate +
				", validityDate=" + validityDate +
				", alertDate=" + alertDate +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUser=" + createUser +
				", deviceInstruments=" + deviceInstruments +
				", department=" + department +
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public Date getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Set<DeviceInstrument> getDeviceInstruments() {
		return deviceInstruments;
	}
	public void addDeviceInstrument(DeviceInstrument deviceInstrument) {
	    this.deviceInstruments.add(deviceInstrument);
    }

	public void setDeviceInstruments(Set<DeviceInstrument> deviceInstruments) {
		this.deviceInstruments = deviceInstruments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
