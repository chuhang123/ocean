package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liming on 17-5-7.
 * 标准装置 —— 机构授权检定项目增加实体
 */
@Entity
@ApiModel(value = "StandardAuthorization 标准装置机构授权检定项目")
public class StandardAuthorization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市")  private String region;
    @ApiModelProperty("区县") private String county;
    @ApiModelProperty("技术机构") private String technical;
    @ApiModelProperty("器具名称") private String name;
    @ApiModelProperty("准确度等级") private String rank;
    @ApiModelProperty("测量范围")  private String measureScale;
    @ApiModelProperty("地址") private String address;
    @ApiModelProperty("联系电话") private String phone;

    public StandardAuthorization() {
    }

    public StandardAuthorization(String region, String county, String technical, String name, String rank, String measureScale, String address, String phone) {
        this.region = region;
        this.county = county;
        this.technical = technical;
        this.name = name;
        this.rank = rank;
        this.measureScale = measureScale;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(String measureScale) {
        this.measureScale = measureScale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "StandardAuthorization{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", technical='" + technical + '\'' +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", measureScale='" + measureScale + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
