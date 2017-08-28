package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ApiModel(value = "Menu (菜单)", description = "菜单实体")
public class Menu {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("url")
	private String url;
	@ApiModelProperty("排序")
	private Long weight;
	@ApiModelProperty("状态")
	private Boolean status;
	@ApiModelProperty("请求方法")
	private String request;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@ManyToOne @ApiModelProperty("操作此菜单的用户")
	private User CreateUser;
	@ManyToOne
	@ApiModelProperty("父亲id")
	@Lazy()
	@JoinColumn(name = "p_id")	//外健名
	private Menu parentMenu;

	public Menu(String name, String pinyin, String url, Long weight, Boolean status, String request, String remark, Long createTime, Long updateTime, User createUser, Menu parentMenu) {
		this.name = name;
		this.pinyin = pinyin;
		this.url = url;
		this.weight = weight;
		this.status = status;
		this.request = request;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		CreateUser = createUser;
		this.parentMenu = parentMenu;
	}

	public Menu() {
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", url='" + url + '\'' +
				", weight=" + weight +
				", status=" + status +
				", request='" + request + '\'' +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				", parentMenu=" + parentMenu +
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
}
