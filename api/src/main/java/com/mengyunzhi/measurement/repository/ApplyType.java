package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "ApplyType (申请类型)", description = "申请类型")
public class ApplyType implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@ApiModelProperty("名称")
	@Column(unique = true)
	private String name;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@ManyToOne @ApiModelProperty("创建申请类型的实体")
	private User createUser;
	@ManyToOne
	@ApiModelProperty("工作流类型")
	private WorkflowType workflowType;

	@ApiModelProperty("前台菜单") @OneToOne
	@JoinColumn(name = "web_app_menu_id", updatable = false)
	@JsonIgnore
	@Lazy
	protected WebAppMenu webAppMenu;

	public ApplyType() {
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
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}


	public WebAppMenu getWebAppMenu() {
		return webAppMenu;
	}

	public WorkflowType getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(WorkflowType workflowType) {
		this.workflowType = workflowType;
	}

	public void setWebAppMenu(WebAppMenu webAppMenu) {
		this.webAppMenu = webAppMenu;
	}
}
