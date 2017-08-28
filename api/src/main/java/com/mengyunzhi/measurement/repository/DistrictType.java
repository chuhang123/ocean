package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "DistrictType (区域类型)", description = "每个区域对应的区域类型")
public class DistrictType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//使用ApiModelProperty注解来生成文档
	@ApiModelProperty("区域类型名") private String name;
	@ApiModelProperty("拼音")  private String pinyin;
	@ApiModelProperty("创建区域的用户")
	@ManyToOne
	@JsonView(NoneJsonView.class)
	private User createUser;

	public DistrictType(String name, String pinyin, User createUser) {
		this.name = name;
		this.pinyin = pinyin;
		this.createUser = createUser;
	}

	public DistrictType() {
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "DistrictType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createUser=" + createUser +
				'}';
	}
}
