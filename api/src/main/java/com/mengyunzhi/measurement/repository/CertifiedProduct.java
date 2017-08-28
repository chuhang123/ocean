package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liming on 17-4-28.
 * 器具产品获证产品目录的增加实体
 */
@Entity
@ApiModel(value = "CertifiedProduct 器具产品")
public class CertifiedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市") private String region;
    @ApiModelProperty("区县") private String county;
    @ApiModelProperty("器具名称") private String name;
    @ApiModelProperty("准确度等级") private String rank;
    @ApiModelProperty("测量范围") private String measureScale;
    @ApiModelProperty("制造单位") private String manufactureUnit;
    @ApiModelProperty("许可证号") private String licenseNum;
    @ApiModelProperty("发证日期") private String issueDate;
    @ApiModelProperty("有效期至") private String validityDate;

    public CertifiedProduct() {
    }

    public CertifiedProduct(String region, String county, String name, String rank, String measureScale, String manufactureUnit, String licenseNum, String issueDate, String validityDate) {
        this.region = region;
        this.county = county;
        this.name = name;
        this.rank = rank;
        this.measureScale = measureScale;
        this.manufactureUnit = manufactureUnit;
        this.licenseNum = licenseNum;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
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

    public String getManufactureUnit() {
        return manufactureUnit;
    }

    public void setManufactureUnit(String manufactureUnit) {
        this.manufactureUnit = manufactureUnit;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    @Override
    public String toString() {
        return "CertifiedProduct{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", measureScale='" + measureScale + '\'' +
                ", manufactureUnit='" + manufactureUnit + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", validityDate='" + validityDate + '\'' +
                '}';
    }
}
