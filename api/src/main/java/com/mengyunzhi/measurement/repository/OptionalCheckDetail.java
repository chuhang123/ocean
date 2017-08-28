package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by zhangjiahao on 2017/5/9.
 * 模拟--非强检器具检定信息查询
 */
//该类是一个实体，对应数据表optional_check_detail
@Entity
@ApiModel(value = "OptionalCheckDetail 非强检器具检定信息查询")
public class OptionalCheckDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   //主键
    //强检器具综合检查询作为外键，多对一
    @ManyToOne
    private OptionalIntegrated optionalIntegrated;
    //使用ApiModelProperty注解来生成文档
    @ApiModelProperty("证书编号") private String certificateNum;
    @ApiModelProperty("示值偏差") private String indicationDeviation;
    @ApiModelProperty("检定日期") private String checkTime;
    @ApiModelProperty("检定单位") private String checkUnit;
    @ApiModelProperty("检定结果") private String checkResult;
    @ApiModelProperty("有效期至") private String validityTime;
    @ApiModelProperty("检定员")  private String checker;
    @ApiModelProperty("核验员")  private String examiner;

    //构造函数
    public OptionalCheckDetail() {
    }

    public OptionalCheckDetail(OptionalIntegrated optionalIntegrated, String certificateNum, String indicationDeviation, String checkTime, String checkUnit, String checkResult, String validityTime, String checker, String examiner) {
        this.optionalIntegrated = optionalIntegrated;
        this.certificateNum = certificateNum;
        this.indicationDeviation = indicationDeviation;
        this.checkTime = checkTime;
        this.checkUnit = checkUnit;
        this.checkResult = checkResult;
        this.validityTime = validityTime;
        this.checker = checker;
        this.examiner = examiner;
    }

//    get set函数
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OptionalIntegrated getOptionalIntegrated() {
        return optionalIntegrated;
    }

    public void setOptionalIntegrated(OptionalIntegrated optionalIntegrated) {
        this.optionalIntegrated = optionalIntegrated;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getIndicationDeviation() {
        return indicationDeviation;
    }

    public void setIndicationDeviation(String indicationDeviation) {
        this.indicationDeviation = indicationDeviation;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(String validityTime) {
        this.validityTime = validityTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

//    复写toString函数
    @Override
    public String toString() {
        return "OptionalCheckDetail{" +
                "id=" + id +
                ", optionalIntegrated=" + optionalIntegrated +
                ", certificateNum='" + certificateNum + '\'' +
                ", indicationDeviation='" + indicationDeviation + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", checkUnit='" + checkUnit + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", validityTime='" + validityTime + '\'' +
                ", checker='" + checker + '\'' +
                ", examiner='" + examiner + '\'' +
                '}';
    }
}
