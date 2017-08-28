package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 * 部门实体
 */
@Entity
@ApiModel(value = "Department (部门)", description = "部门实体")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("机构代码")
    private String code;
    @ApiModelProperty("邮政编码")
    private String postalCode;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("法人姓名")
    private String legalName;
    @ApiModelProperty("法人电话")
    private String legalPhone;
    @ApiModelProperty("注册人姓名")
    private String registrantName;
    @ApiModelProperty("注册人手机")
    private String registrantPhone;
    @ApiModelProperty("注册人座机")
    private String registrantTel;
    @ApiModelProperty("注册人邮箱")
    private String registrantMail;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("拼音")
    private String pinyin;
    @ApiModelProperty("注册日期")
    private Date registerDate;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间")
    @UpdateTimestamp
    private Calendar updateTime;
    @ApiModelProperty("状态")
    private Boolean status;
    @ApiModelProperty("是否超限报警")
    private Boolean isOutOfRange;
    @ApiModelProperty("是否是建标用户")
    private Boolean isStandard;
    @ManyToOne
    @ApiModelProperty("创建用户")
    private User createUser;
    @ManyToOne
    @ApiModelProperty("关联部门类型")
    private DepartmentType departmentType;
    @ManyToOne
    @ApiModelProperty("关联区域")
    private District district;

    @ApiModelProperty("(对某授权项目的)检定能力")
    @Transient
    private boolean checkAbility = false;

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", legalName='" + legalName + '\'' +
                ", legalPhone='" + legalPhone + '\'' +
                ", registrantName='" + registrantName + '\'' +
                ", registrantPhone='" + registrantPhone + '\'' +
                ", registrantTel='" + registrantTel + '\'' +
                ", registrantMail='" + registrantMail + '\'' +
                ", phone='" + phone + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", registerDate=" + registerDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", isOutOfRange=" + isOutOfRange +
                ", isStandard=" + isStandard +
                ", createUser=" + createUser +
                ", departmentType=" + departmentType +
                ", district=" + district +
                ", checkAbility=" + checkAbility +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getRegistrantPhone() {
        return registrantPhone;
    }

    public void setRegistrantPhone(String registrantPhone) {
        this.registrantPhone = registrantPhone;
    }

    public String getRegistrantTel() {
        return registrantTel;
    }

    public void setRegistrantTel(String registrantTel) {
        this.registrantTel = registrantTel;
    }

    public String getRegistrantMail() {
        return registrantMail;
    }

    public void setRegistrantMail(String registrantMail) {
        this.registrantMail = registrantMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getOutOfRange() {
        return isOutOfRange;
    }

    public void setOutOfRange(Boolean outOfRange) {
        isOutOfRange = outOfRange;
    }

    public Boolean getStandard() {
        return isStandard;
    }

    public void setStandard(Boolean standard) {
        isStandard = standard;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public boolean isCheckAbility() {
        return checkAbility;
    }

    public void setCheckAbility(boolean checkAbility) {
        this.checkAbility = checkAbility;
    }
}
