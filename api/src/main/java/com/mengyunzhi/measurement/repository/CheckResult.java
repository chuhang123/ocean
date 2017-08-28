package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.json.JsonConfig;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@ApiModel(value = "CheckResult (检定结果)", description = "检定结果实体")
public class CheckResult {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("创建时间") @CreationTimestamp
	private Calendar createTime;
	@ApiModelProperty("更新时间")	 @UpdateTimestamp
	private Calendar updateTime;
	@ManyToOne @ApiModelProperty("上级检定结果") @JsonIgnore
	private CheckResult parentCheckResult;
	@ManyToOne @ApiModelProperty("创建检定结果的用户")
	private User CreateUser;

	@JsonView(JsonViewConfig.WithOutParentCheckResult.class)
	@Transient		//该字段并不存在数据表中
	private List<CheckResult> sonCheckResults = new ArrayList<>();

	public CheckResult() {
	}

	public CheckResult(String pinyin, String name, Calendar createTime, Calendar updateTime, CheckResult parentCheckResult, User createUser) {
		this.pinyin = pinyin;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.parentCheckResult = parentCheckResult;
		CreateUser = createUser;
	}

	@Override
	public String toString() {
		return "CheckResult{" +
				"id=" + id +
				", pinyin='" + pinyin + '\'' +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", parentCheckResult=" + parentCheckResult +
				", CreateUser=" + CreateUser +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public CheckResult getParentCheckResult() {
		return parentCheckResult;
	}

	public void setParentCheckResult(CheckResult parentCheckResult) {
		this.parentCheckResult = parentCheckResult;
	}

	public List<CheckResult> getSonCheckResults() {
		return sonCheckResults;
	}

	public void setSonCheckResults(List<CheckResult> sonCheckResults) {
		this.sonCheckResults = sonCheckResults;
	}

	public User getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(User createUser) {
		CreateUser = createUser;
	}
}
