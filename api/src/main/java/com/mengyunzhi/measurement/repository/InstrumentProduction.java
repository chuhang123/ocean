package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 器具生产器具种类实体
 */
@Entity
@ApiModel(value = "InstrumentProduction(生产企业器具的种类)", description = "生产企业器具的种类")
public class InstrumentProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @ApiModelProperty("器具类别")
    private InstrumentType instrumentType;
    @ManyToOne
    @ApiModelProperty("测量范围")
    private MeasureScale measureScale;
    @ManyToOne
    @ApiModelProperty("型号规格")
    private Specification specification;
    @ManyToOne
    @ApiModelProperty("精度")     //原来叫等级准确度示值偏差，太长，已在ER图中改为  精度
    private Accuracy accuracy;
    @ManyToOne
    @ApiModelProperty("生产企业")
    private Department manufacturerDepartment;
    @ManyToOne
    @ApiModelProperty("操作用户")
    private User createUser;

    @ApiModelProperty("许可证号")
    private String licenseNum;
    @ApiModelProperty("发证日期")
    private int certificateDate;
    @ApiModelProperty("有效期至")
    private int validityDate;
    @ApiModelProperty("报警日期")
    private int alertDate;
    @ApiModelProperty("创建时间") @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间") @UpdateTimestamp
    private Calendar updateTime;

    public InstrumentProduction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public MeasureScale getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(MeasureScale measureScale) {
        this.measureScale = measureScale;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public Department getManufacturerDepartment() {
        return manufacturerDepartment;
    }

    public void setManufacturerDepartment(Department manufacturerDepartment) {
        this.manufacturerDepartment = manufacturerDepartment;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }


    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public int getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(int certificateDate) {
        this.certificateDate = certificateDate;
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

    @Override
    public String toString() {
        return "InstrumentProduction{" +
                "id=" + id +
                ", instrumentType=" + instrumentType +
                ", measureScale=" + measureScale +
                ", specification=" + specification +
                ", accuracy=" + accuracy +
                ", manufacturerDepartment=" + manufacturerDepartment +
                ", createUser=" + createUser +
                ", licenseNum='" + licenseNum + '\'' +
                ", certificateDate=" + certificateDate +
                ", validityDate=" + validityDate +
                ", alertDate=" + alertDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
