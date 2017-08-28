package com.mengyunzhi.measurement.repository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by panjie on 17/7/13.
 * 非强检器具
 */
@Entity
@DiscriminatorValue("unMandatory")
public class UnMandatoryInstrument extends InstrumentEmploymentInfo {
}
