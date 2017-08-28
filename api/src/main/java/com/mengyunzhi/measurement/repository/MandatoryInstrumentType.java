package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by panjie on 17/7/26.
 * 强制检定器具类别
 */
@Entity
@ApiModel("MandatoryInstrumentType 强制检定器具类别")
@DiscriminatorValue("MandatoryInstrument")
public class MandatoryInstrumentType extends InstrumentType implements Serializable {
    private static final long serialVersionUID = 1L;
}
