package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by panjie on 17/5/5.
 * 标准装置档案查询
 */
@Entity
@ApiModel(value = "StandardFile 标准装置档案查询")
public class StandardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市")  private String region;          // 市
    @ApiModelProperty("区县") private String county;          // 区县
    @ApiModelProperty("代码") private String code;            // 代码
    @ApiModelProperty("计量标准装置名称") private String name;            // 名称
    @ApiModelProperty("考核证书编号") private String certificateNum;  // 考核证书编号
    @ApiModelProperty("考核日期") private String checkDate;       // 考核日期
    @ApiModelProperty("颁发日期") private String issueDate;       // 颁发日期
    @ApiModelProperty("有效期至") private String validityDate;    // 有效期至

    public StandardFile() {
    }

    public StandardFile(String region, String county, String code, String name, String certificateNum, String checkDate, String issueDate, String validityDate) {
        this.region = region;
        this.county = county;
        this.code = code;
        this.name = name;
        this.certificateNum = certificateNum;
        this.checkDate = checkDate;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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
        return "StandardFile{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", validityDate='" + validityDate + '\'' +
                '}';
    }
}
