package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by 安强 on 2017/5/24.
 * 器具生产年检信息  实体
 */
@Entity
@ApiModel(value = "器具生产年检信息", description = "器具生产信息对应的多条年检信息")
public class InstrumentProductionAnnual {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("关联的器具生产信息")
    private InstrumentProduction instrumentProduction;

    @ApiModelProperty("年检日期")
    private int checkDate;

    @ApiModelProperty("操作用户")
    private User createUser;

    @ApiModelProperty("创建时间")
    private int createTime;

    @ApiModelProperty("更新时间")
    private int updateTime;

    public InstrumentProductionAnnual() {
    }

    public InstrumentProductionAnnual(InstrumentProduction instrumentProduction, int checkDate, User createUser, int createTime, int updateTime) {
        this.instrumentProduction = instrumentProduction;
        this.checkDate = checkDate;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstrumentProduction getInstrumentProduction() {
        return instrumentProduction;
    }

    public void setInstrumentProduction(InstrumentProduction instrumentProduction) {
        this.instrumentProduction = instrumentProduction;
    }

    public int getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(int checkDate) {
        this.checkDate = checkDate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "InstrumentProductionAnnual{" +
                "id=" + id +
                ", instrumentProduction=" + instrumentProduction +
                ", checkDate=" + checkDate +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
