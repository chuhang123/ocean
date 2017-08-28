package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.StandardDeviceJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sun.print.PSPrinterJob;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by liming on 17-5-9.
 */
@Entity
@ApiModel(value = "StandardDevice (标准器)", description = "标准器实体")
public class StandardDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ApiModelProperty("是否主标准器")
    private Boolean isMain;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("代码")
    private String code;
    @ApiModelProperty("出厂编号")
    private String factoryNum;
    @ApiModelProperty("许可证号")
    private String licenseNum;
    @ApiModelProperty("生产企业名称")
    private String manufacturerName;
    @ManyToOne @ApiModelProperty("操作用户")
    private User currentUser;
    @ManyToOne @ApiModelProperty("计量标准装置")
    @JsonView({NoneJsonView.class, StandardDeviceJsonView.baseJsonView.class})
    private DeviceSet deviceSet;
    @ManyToOne @ApiModelProperty("器具类别")
    private StandardDeviceInstrumentType standardDeviceInstrumentType;
    @ManyToOne @ApiModelProperty("型号规格")
    private Specification specification;
    @ManyToOne @ApiModelProperty("测量范围")
    private MeasureScale measureScale;
    @ManyToOne @ApiModelProperty("精度")
    private Accuracy accuracy;
    @ApiModelProperty("创建时间") @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间") @UpdateTimestamp
    private Calendar updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public StandardDevice() {
    }

    public StandardDevice(Boolean isMain, String name, String code, String factoryNum, String licenseNum, String manufacturerName, User currentUser, DeviceSet deviceSet, StandardDeviceInstrumentType standardDeviceInstrumentType, Specification specification, MeasureScale measureScale, Accuracy accuracy, Calendar createTime, Calendar updateTime) {
        this.isMain = isMain;
        this.name = name;
        this.code = code;
        this.factoryNum = factoryNum;
        this.licenseNum = licenseNum;
        this.manufacturerName = manufacturerName;
        this.currentUser = currentUser;
        this.deviceSet = deviceSet;
        this.standardDeviceInstrumentType = standardDeviceInstrumentType;
        this.specification = specification;
        this.measureScale = measureScale;
        this.accuracy = accuracy;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public DeviceSet getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(DeviceSet deviceSet) {
        this.deviceSet = deviceSet;
    }

    public StandardDeviceInstrumentType getStandardDeviceInstrumentType() {
        return standardDeviceInstrumentType;
    }

    public void setStandardDeviceInstrumentType(StandardDeviceInstrumentType standardDeviceInstrumentType) {
        this.standardDeviceInstrumentType = standardDeviceInstrumentType;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public MeasureScale getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(MeasureScale measureScale) {
        this.measureScale = measureScale;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
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
        return "StandardDevice{" +
                "Id=" + Id +
                ", isMain=" + isMain +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", factoryNum='" + factoryNum + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", currentUser=" + currentUser +
                ", deviceSet=" + deviceSet +
                ", standardDeviceInstrumentType=" + standardDeviceInstrumentType +
                ", specification=" + specification +
                ", measureScale=" + measureScale +
                ", accuracy=" + accuracy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
