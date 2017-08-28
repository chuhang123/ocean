package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by panjie on 17/7/14.
 * 强检器具送审
 */
@Entity
@DiscriminatorValue("MandatoryInstrument")
@ApiModel(value = "MandatoryInstrumentApply 强检器具送审申请", description = "记录了每们强检器具的申请信息")
public class MandatoryInstrumentApply extends Apply implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("强检器具使用信息")
    @OneToMany(mappedBy = "mandatoryInstrumentApply", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class})
    private Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();

    public MandatoryInstrumentApply() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<MandatoryInstrument> getMandatoryInstruments() {
        return mandatoryInstruments;
    }

    public void setMandatoryInstruments(Set<MandatoryInstrument> mandatoryInstruments) {
        this.mandatoryInstruments = mandatoryInstruments;
    }

    public void addMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
        this.mandatoryInstruments.add(mandatoryInstrument);
    }
}
