package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.sql.Date;

/**
 * Created by panjie on 17/7/13.
 * 强检器具
 */
@Entity
@DiscriminatorValue("mandatory")
@ApiModel(value = "MandatoryInstrument 强检器具", description = "强检器具使用信息")
public class MandatoryInstrument extends InstrumentEmploymentInfo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("是否通过审核")
    private Boolean audit = false;
    @ApiModelProperty("检定周期(天)")
    private int checkCycle = -1;    // -1 未设置；0无检定期限
    @ApiModelProperty("首次检定日期")
    private Date firstCheckDate;
    @ApiModelProperty("下次检定日期")
    private Date nextCheckDate;
    @ApiModelProperty("强制检定备案申请")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private MandatoryInstrumentApply mandatoryInstrumentApply;
    @ManyToOne
    @ApiModelProperty("指定检定技术机构(指定检定部门)")
    private Department checkDepartment;
    @ApiModelProperty("授权检定项目")
    @Transient
    @JsonView(NoneJsonView.class)
    private DeviceInstrument deviceInstrument = new DeviceInstrument();

    @ApiModelProperty("某个部门是否具有本实体的检定能力")
    @Transient
    @JsonView({
            NoneJsonView.class,
            MandatoryInstrumentJsonView.computerCheckAbilityByDepartmentIdOfMandatoryInstruments.class,
            ApplyJsonView.get.class})
    private Boolean checkAbility = false;

    public MandatoryInstrument() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    public int getCheckCycle() {
        return checkCycle;
    }

    public void setCheckCycle(int checkCycle) {
        this.checkCycle = checkCycle;
    }

    public Date getFirstCheckDate() {
        return firstCheckDate;
    }

    public void setFirstCheckDate(Date firstCheckDate) {
        this.firstCheckDate = firstCheckDate;
    }

    public MandatoryInstrumentApply getMandatoryInstrumentApply() {
        return mandatoryInstrumentApply;
    }

    public void setMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) {
        this.mandatoryInstrumentApply = mandatoryInstrumentApply;
    }

    public Department getCheckDepartment() {
        return checkDepartment;
    }

    public void setCheckDepartment(Department checkDepartment) {
        this.checkDepartment = checkDepartment;
    }

    public DeviceInstrument getDeviceInstrument() {
        if (this.getAccuracy() != null && this.getMeasureScale() != null) {
            deviceInstrument.setAccuracy(this.getAccuracy());
            deviceInstrument.setMeasureScale(this.getMeasureScale());
        }
        return deviceInstrument;
    }

    public void setDeviceInstrument(DeviceInstrument deviceInstrument) {
        this.deviceInstrument = deviceInstrument;
    }

    public Boolean getCheckAbility() {
        return checkAbility;
    }

    public void setCheckAbility(Boolean checkAbility) {
        this.checkAbility = checkAbility;
    }

    public Date getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    @Override
    public String toString() {
        return "MandatoryInstrument{" +
                "audit=" + audit +
                ", checkCycle=" + checkCycle +
                ", firstCheckDate=" + firstCheckDate +
                ", nextCheckDate=" + nextCheckDate +
                ", mandatoryInstrumentApply=" + mandatoryInstrumentApply +
                ", checkDepartment=" + checkDepartment +
                ", deviceInstrument=" + deviceInstrument +
                ", checkAbility=" + checkAbility +
                '}';
    }
}
