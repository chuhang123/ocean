package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.DeviceInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目与精度，测量范围关联
 */
@Entity
@Immutable
@ApiModel(value = "DeviceInstrument (装置授权检定项目)", description = "装置授权检定项目实体")
public class DeviceInstrument implements Serializable{
    //参考网址http://codego.net/144328/

    @EmbeddedId                 //映射组合建
    private Id id = new Id();

    @ManyToOne
    @ApiModelProperty("对应的测量范围")        //关联测量范围
    @JoinColumn(insertable = false, updatable = false)
    private MeasureScale measureScale;

    @ManyToOne
    @ApiModelProperty("对应的精度")         //关联精度
    @JoinColumn(insertable = false, updatable = false)
    private Accuracy accuracy;

    @ManyToMany
    @ApiModelProperty("标准装置 http://www.mengyunzhi.cn/2017/08/09/study-jpa-ManyToMany-deeply/")
    @JsonView({NoneJsonView.class, DeviceInstrumentJsonView.ToDeviceSet.class})
    @JoinTable(
            name = "device_set_device_instruments",
            joinColumns = {
                    @JoinColumn(name = "accuracy_id", referencedColumnName = "accuracy_id"),
                    @JoinColumn(name = "measure_scale_id", referencedColumnName = "measure_scale_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "device_set_id", referencedColumnName = "id")})
    private Set<DeviceSet> deviceSets;

    public DeviceInstrument() {
    }
    @Embeddable
    public static class Id implements Serializable {        //封装组合健
        @Column(name = "accuracy_id", nullable = false, insertable = false, updatable = false)
        private Long accuracyId;
        @Column(name = "measure_scale_id", nullable = false, insertable = false, updatable = false)
        private Long measureScaleId;

        public Id() {
        }

        public Id(Long accuracyId, Long measureScaleId) {
            this.accuracyId = accuracyId;
            this.measureScaleId = measureScaleId;
        }

        public Long getMeasureScaleId() {
            return measureScaleId;
        }

        public void setMeasureScaleId(Long measureScaleId) {
            this.measureScaleId = measureScaleId;
        }

        public Long getAccuracyId() {
            return accuracyId;
        }

        public void setAccuracyId(Long accuracyId) {
            this.accuracyId = accuracyId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;

            Id id = (Id) o;

            if (!measureScaleId.equals(id.measureScaleId)) return false;
            return accuracyId.equals(id.accuracyId);
        }

        @Override
        public int hashCode() {
            int result = measureScaleId.hashCode();
            result = 31 * result + accuracyId.hashCode();
            return result;
        }
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public MeasureScale getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(MeasureScale measureScale) {
        this.measureScale = measureScale;
        this.id.setMeasureScaleId(measureScale.getId());
    }

    public Set<DeviceSet> getDeviceSets() {
        return deviceSets;
    }

    public void setDeviceSets(Set<DeviceSet> deviceSets) {
        this.deviceSets = deviceSets;
    }

    public void addDeviceSet(DeviceSet deviceSet) {
        this.deviceSets.add(deviceSet);
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
        this.id.setAccuracyId(accuracy.getId());
    }

    @Override
    public String toString() {
        return "DeviceInstrument{" +
                "id=" + id +
                ", measureScale=" + measureScale +
                ", accuracy=" + accuracy +
                '}';
    }
}
