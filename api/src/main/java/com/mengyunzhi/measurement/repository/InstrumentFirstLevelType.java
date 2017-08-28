package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/7/26.
 * 器具一级类别
 */
@Entity
@ApiModel(value = "InstrumentFirstLevelType 器具一级类级类别", description = "器具一级类级类别")
public class InstrumentFirstLevelType implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("学科类别")
    @ManyToOne
    @JsonView({
            NoneJsonView.class,
            DeviceInstrumentJsonView.ToDeviceSet.class,
            InstrumentTypeJsonView.Base.class,
            StandardDeviceJsonView.baseJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            MandatoryInstrumentJsonView.pageByCheckDepartmentOfCurrentDepartment.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            ApplyJsonView.get.class,
            InstrumentCheckInfoJsonView.getAllOfCurrentUser.class
    })
    private Discipline discipline;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("拼音")
    private String pinyin;

    public InstrumentFirstLevelType() {
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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "InstrumentFirstLevelType{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstrumentFirstLevelType)) return false;

        InstrumentFirstLevelType that = (InstrumentFirstLevelType) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getDiscipline() != null ? getDiscipline().equals(that.getDiscipline()) : that.getDiscipline() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDiscipline() != null ? getDiscipline().hashCode() : 0);
        return result;
    }
}
