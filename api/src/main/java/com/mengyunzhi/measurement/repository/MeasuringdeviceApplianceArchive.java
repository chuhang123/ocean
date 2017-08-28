package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liming on 17-4-28.
 * 模拟——器具产品器具生产企业档案实体
 */
@Entity
@ApiModel(value = "MeasuringdeviceApplianceArchive 器具产品器具生产企业档案")
public class MeasuringdeviceApplianceArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column,默认长度是255
    // 使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市") private String region;
    @ApiModelProperty("区县") private String county;
    @ApiModelProperty("企业名称") private String name;
    @ApiModelProperty("企业代码") private String code;
    @ApiModelProperty("学科类别") private String discipline;
    @ApiModelProperty("器具名称") private String dissipativeName;
    @ApiModelProperty("地址") private String address;
    @ApiModelProperty("法人姓名") private String legalName;
    @ApiModelProperty("电话") private String legalPhone;
    @ApiModelProperty("联系人") private String registrantName;

    public MeasuringdeviceApplianceArchive() {
    }

    public MeasuringdeviceApplianceArchive(String region, String county, String name, String code, String discipline, String dissipativeName, String address, String legalName, String legalPhone, String registrantName) {
        this.region = region;
        this.county = county;
        this.name = name;
        this.code = code;
        this.discipline = discipline;
        this.dissipativeName = dissipativeName;
        this.address = address;
        this.legalName = legalName;
        this.legalPhone = legalPhone;
        this.registrantName = registrantName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDissipativeName() {
        return dissipativeName;
    }

    public void setDissipativeName(String dissipativeName) {
        this.dissipativeName = dissipativeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    public String getRegistrantName() {
        return registrantName;
    }

    public void setRegistrantName(String registrantName) {
        this.registrantName = registrantName;
    }

    @Override
    public String toString() {
        return "MeasuringdeviceApplianceArchive{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", discipline='" + discipline + '\'' +
                ", dissipativeName='" + dissipativeName + '\'' +
                ", address='" + address + '\'' +
                ", legalName='" + legalName + '\'' +
                ", legalPhone='" + legalPhone + '\'' +
                ", registrantName='" + registrantName + '\'' +
                '}';
    }
}
