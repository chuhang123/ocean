package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(value = "QualifierCertificate(人员资质)" , description = "人员资质实体")
public class QualifierCertificate {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("发证日期") private int issueDate;
    @ApiModelProperty("有效期至") private int validityDate;
    @ApiModelProperty("报警日期") private int alertDate;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;
    @ManyToOne@ApiModelProperty("操作该实体的用户") private User createUser;
    @ManyToOne@ApiModelProperty("对应的人员") private Qualifier qualifier;
    @ManyToOne@ApiModelProperty("对应的资格证类别") private QualifierCertificateType qualifierCertificateType;



    public QualifierCertificate() {
    }

    public QualifierCertificate(int issueDate, int validityDate, int alertDate, Long createTime, Long updateTime, User createUser, Qualifier qualifier, QualifierCertificateType qualifierCertificateType) {
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.alertDate = alertDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.qualifier = qualifier;
        this.qualifierCertificateType = qualifierCertificateType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(int issueDate) {
        this.issueDate = issueDate;
    }

    public int getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(int validityDate) {
        this.validityDate = validityDate;
    }

    public int getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(int alertDate) {
        this.alertDate = alertDate;
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

    public Qualifier getQualifier() {
        return qualifier;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public QualifierCertificateType getQualifierCertificateType() {
        return qualifierCertificateType;
    }

    public void setQualifierCertificateType(QualifierCertificateType qualifierCertificateType) {
        this.qualifierCertificateType = qualifierCertificateType;
    }

    @Override
    public String toString() {
        return "QualifierCertificate{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", validityDate=" + validityDate +
                ", alertDate=" + alertDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", qualifier=" + qualifier +
                ", qualifierCertificateType=" + qualifierCertificateType +
                '}';
    }
}
