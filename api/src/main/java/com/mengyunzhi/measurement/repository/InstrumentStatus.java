package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 器具状态 实体
 */
@Entity
@ApiModel(value = "器具状态", description = "描述每个器具的状态(在用、停用、报废)")
public class InstrumentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("拼音")
    private String pinyin;
    @ApiModelProperty("创建时间")
    private int createTime;
    @ApiModelProperty("添加器具状态的用户")
    @ManyToOne
    private User createUser;
    @ApiModelProperty("备注")
    private String remark;
    @ManyToOne
    @ApiModelProperty("对应的器具使用信息")
    private InstrumentEmploymentInfo instrumentEmploymentInfo;


    public InstrumentStatus() {
    }

    public InstrumentStatus(String type, String pinyin, int createTime, User createUser, String remark, InstrumentEmploymentInfo instrumentEmploymentInfo) {
        this.type = type;
        this.pinyin = pinyin;
        this.createTime = createTime;
        this.createUser = createUser;
        this.remark = remark;
        this.instrumentEmploymentInfo = instrumentEmploymentInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InstrumentEmploymentInfo getInstrumentEmploymentInfo() {
        return instrumentEmploymentInfo;
    }

    public void setInstrumentEmploymentInfo(InstrumentEmploymentInfo instrumentEmploymentInfo) {
        this.instrumentEmploymentInfo = instrumentEmploymentInfo;
    }

    @Override
    public String toString() {
        return "InstrumentStatus{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", remark='" + remark + '\'' +
                ", instrumentEmploymentInfo=" + instrumentEmploymentInfo +
                '}';
    }
}
