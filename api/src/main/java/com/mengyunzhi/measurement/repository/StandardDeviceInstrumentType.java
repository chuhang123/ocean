package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by liming on 17-7-27.
 */
@Entity
@ApiModel("StandardDeviceInstrumentType 标准器类别")
@DiscriminatorValue("StandardDevice")
public class StandardDeviceInstrumentType extends InstrumentType implements Serializable {
    private static final long serialVersionUID = 1L;
}
