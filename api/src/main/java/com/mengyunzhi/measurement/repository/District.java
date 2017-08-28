package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.DepartmentJsonView;
import com.mengyunzhi.measurement.jsonView.DistrictJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(value = "District (区域)", description = "区域")
public class District implements Serializable {
    // 实现了Serializable接口，用于序列化
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("区域类型")
    @ManyToOne
    private DistrictType districtType;

    @ApiModelProperty("区域名称")
    private String name;

    @ApiModelProperty("操作用户")
    @ManyToOne
    private User createUser;

    @ApiModelProperty("拼音")
    private String pinyin;

    @ApiModelProperty("上级区域")
    @ManyToOne
    @JsonView({NoneJsonView.class,
            DepartmentJsonView.OnlyPartentDistrict.class,
            DistrictJsonView.WithParentDistrict.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class})
    private District parentDistrict;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @JsonView({NoneJsonView.class,
            JsonViewConfig.WithOutParentDistrict.class,
            DistrictJsonView.WithSonDistricts.class})
    @Transient // 该字段并不存在于数据表中
    private List<District> sonDistricts = new ArrayList<>();

    public District() {
    }

    public District(DistrictType districtType, String name, User createUser,
                    String pinyin, District parentDistrict, Long createTime,
                    Long updateTime) {
        this.districtType = districtType;
        this.name = name;
        this.createUser = createUser;
        this.pinyin = pinyin;
        this.parentDistrict = parentDistrict;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DistrictType getDistrictType() {
        return districtType;
    }

    public void setDistrictType(DistrictType districtType) {
        this.districtType = districtType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public District getParentDistrict() {
        return parentDistrict;
    }

    public void setParentDistrict(District preDistrict) {
        this.parentDistrict = preDistrict;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<District> getSonDistricts() {
        return sonDistricts;
    }

    public void setSonDistricts(List<District> sonDistricts) {
        this.sonDistricts = sonDistricts;
    }

}
