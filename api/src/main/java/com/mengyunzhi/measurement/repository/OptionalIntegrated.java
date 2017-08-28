package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liming on 17-4-25.
 * 模拟--非强检器具综合查询
 */
//说明该类对应数据表optional_integrated
@Entity
@ApiModel(value = "OptionalIntegrated 非强检器具综合查询")
public class OptionalIntegrated {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //没有声明@Column的时候，默认的长度是255
    //使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市") private String region;
    @ApiModelProperty("区县") private String county;
    @ApiModelProperty("器具用户") private String user;
    @ApiModelProperty("器具名称") private String name;
    @ApiModelProperty("状态") private String status;
    @ApiModelProperty("型号规格") private String type;
    @ApiModelProperty("用途") private String purpose;
    @ApiModelProperty("测量范围") private String measureScale;
    @ApiModelProperty("准确度等级") private String rank;
    @ApiModelProperty("制造单位") private String manufactureUnit;
    @ApiModelProperty("出厂编号") private String factoryNum;
    @ApiModelProperty("安装地点") private String fixSite;
    @ApiModelProperty("许可证号") private String licenseNum;

    //构造函数
    public OptionalIntegrated() {
    }

    public OptionalIntegrated(String region, String county, String user, String name, String status, String type, String purpose, String measureScale, String rank, String manufactureUnit, String factoryNum, String fixSite, String licenseNum) {
        this.region = region;                 //市
        this.county = county;                 //区县
        this.user = user;                     //器具用户
        this.name = name;                     //器具名称
        this.status = status;                 //状态
        this.type = type;                     //型号规格
        this.purpose = purpose;              //用途
        this.measureScale = measureScale;   //测量范围
        this.rank = rank;                    //准确度等级
        this.manufactureUnit = manufactureUnit;  //制造单位
        this.factoryNum = factoryNum;           //出厂编号
        this.fixSite = fixSite;              //安装地点
        this.licenseNum = licenseNum;       //许可证号
    }

    //get set函数
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(String measureScale) {
        this.measureScale = measureScale;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getManufactureUnit() {
        return manufactureUnit;
    }

    public void setManufactureUnit(String manufactureUnit) {
        this.manufactureUnit = manufactureUnit;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getFixSite() {
        return fixSite;
    }

    public void setFixSite(String fixSite) {
        this.fixSite = fixSite;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }


    //重写toString函数
    @Override
    public String toString() {
        return "OptionalIntegrated{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", User='" + user + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", purpose='" + purpose + '\'' +
                ", measureScale='" + measureScale + '\'' +
                ", rank='" + rank + '\'' +
                ", manufactureUnit='" + manufactureUnit + '\'' +
                ", factoryNum='" + factoryNum + '\'' +
                ", fixSite='" + fixSite + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                '}';
    }
}