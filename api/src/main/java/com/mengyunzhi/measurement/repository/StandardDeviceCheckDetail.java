package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

/**
 * Created by liming on 17-5-13.
 */
@Entity
@ApiModel(value = "StandardDeviceCheckDetail (标准器检定信息)", description = "标准器检定信息实体")
public class StandardDeviceCheckDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("证书编号")
    private String certificateNum;
    @ApiModelProperty("修正值")
    private String correctValue;
    @ApiModelProperty("检定日期")
    private Date checkDate;
    @ApiModelProperty("有效期至")
    private Date validityDate;
    @ApiModelProperty("报警日期")
    private Date alertDate;
    @ApiModelProperty("创建时间") @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间") @UpdateTimestamp
    private Calendar updateTime;
    @ApiModelProperty("检定员")
    private String inspectorQualifierCertificate;
    @ApiModelProperty("核验员")
    private String verifierQualifierCertificate;
    @ManyToOne @ApiModelProperty("操作用户")
    private User createUser;
    @ApiModelProperty("检定部门")
    private String calibrationDepartment;
    @ManyToOne @ApiModelProperty("标准器")
    private StandardDevice standardDevice;
    @ManyToOne @ApiModelProperty("检定结果")
    private CheckResult checkResult;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public StandardDeviceCheckDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(String correctValue) {
        this.correctValue = correctValue;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public String getInspectorQualifierCertificate() {
        return inspectorQualifierCertificate;
    }

    public void setInspectorQualifierCertificate(String inspectorQualifierCertificate) {
        this.inspectorQualifierCertificate = inspectorQualifierCertificate;
    }

    public String getVerifierQualifierCertificate() {
        return verifierQualifierCertificate;
    }

    public void setVerifierQualifierCertificate(String verifierQualifierCertificate) {
        this.verifierQualifierCertificate = verifierQualifierCertificate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getCalibrationDepartment() {
        return calibrationDepartment;
    }

    public void setCalibrationDepartment(String calibrationDepartment) {
        this.calibrationDepartment = calibrationDepartment;
    }

    public StandardDevice getStandardDevice() {
        return standardDevice;
    }

    public void setStandardDevice(StandardDevice standardDevice) {
        this.standardDevice = standardDevice;
    }

    public void setCheckResult(CheckResult checkResult) {
        this.checkResult = checkResult;
    }

    public CheckResult getCheckResult() {

        return checkResult;
    }

    @Override
    public String toString() {
        return "StandardDeviceCheckDetail{" +
                "id=" + id +
                ", certificateNum='" + certificateNum + '\'' +
                ", correctValue='" + correctValue + '\'' +
                ", checkDate=" + checkDate +
                ", validityDate=" + validityDate +
                ", alertDate=" + alertDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", inspectorQualifierCertificate=" + inspectorQualifierCertificate +
                ", verifierQualifierCertificate=" + verifierQualifierCertificate +
                ", createUser=" + createUser +
                ", calibrationDepartment=" + calibrationDepartment +
                ", standardDevice=" + standardDevice +
                ", checkResult=" + checkResult +
                '}';
    }
}
