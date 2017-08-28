package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 器具类别  实体
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Inheritance = 继承
@DiscriminatorColumn(name = "table_type")
@ApiModel(value = "InstrumentType (器具类别)", description = "器具类别实体")
public class InstrumentType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("拼音")
    private String pinyin;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("更新时间")
    private Long updateTime;
    @ManyToOne
    @ApiModelProperty("用户")
    private User CreateUser;
    @ManyToOne
    @ApiModelProperty("器具一级类别")
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
    private InstrumentFirstLevelType instrumentFirstLevelType;
    @ManyToOne
    @ApiModelProperty("等级准确度示值偏差显示名称")
    private AccuracyDisplayName accuracyDisplayName;

    @Lazy       //参照hibernate实战第七章第三节级联状态
    @OneToMany(mappedBy = "instrumentType", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @ApiModelProperty("oneToMany 规格型号")
    private Set<Specification> specifications = new HashSet<>();

    @Lazy
    @OneToMany(mappedBy = "instrumentType", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JsonView({NoneJsonView.class,
            InstrumentTypeJsonView.Base.class})
    @ApiModelProperty("oneToMany 测量范围")
    private Set<MeasureScale> measureScales = new HashSet<>();

    @Lazy
    @ManyToMany
    @ApiModelProperty("用途")
    @JoinTable(
            name = "instrument_type_purpose",
            joinColumns = @JoinColumn(name = "instrument_type_id"),
            inverseJoinColumns = @JoinColumn(name = "purpose_id"))
    private Set<Purpose> purposes = new HashSet<>();

    @ApiModelProperty("manyToMany精度")
    @ManyToMany // 可以在此直接创建相应的精度值
    @JoinTable(
            name = "accuracy_instrument_type",
            joinColumns = @JoinColumn(name = "instrument_type_id"),
            inverseJoinColumns = @JoinColumn(name = "accuracy_id")
    )
    private Set<Accuracy> accuracies = new HashSet<>();

    public InstrumentType() {
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<Purpose> getPurposes() {
        return purposes;
    }

    public void setPurposes(Set<Purpose> purposes) {
        this.purposes = purposes;
    }

    public void addPurpose(Purpose purpose) {
        this.purposes.add(purpose);
    }

    public Set<Accuracy> getAccuracies() {
        return accuracies;
    }

    public void setAccuracies(Set<Accuracy> accuracies) {
        this.accuracies = accuracies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return "InstrumentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", CreateUser=" + CreateUser +
                ", instrumentFirstLevelType=" + instrumentFirstLevelType +
                ", accuracyDisplayName=" + accuracyDisplayName +
                ", specifications=" + specifications +
                ", measureScales=" + measureScales +
                ", purposes=" + purposes +
                ", accuracies=" + accuracies +
                '}';
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

    public User getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(User createUser) {
        CreateUser = createUser;
    }

    public InstrumentFirstLevelType getInstrumentFirstLevelType() {
        return instrumentFirstLevelType;
    }

    public void setInstrumentFirstLevelType(InstrumentFirstLevelType instrumentFirstLevelType) {
        this.instrumentFirstLevelType = instrumentFirstLevelType;
    }

    public AccuracyDisplayName getAccuracyDisplayName() {
        return accuracyDisplayName;
    }

    public void setAccuracyDisplayName(AccuracyDisplayName accuracyDisplayName) {
        this.accuracyDisplayName = accuracyDisplayName;
    }

    public void addMeasureScale(MeasureScale measureScale) {
        this.measureScales.add(measureScale);
    }

    public void addSpcification(Specification specification) {
        this.specifications.add(specification);
    }

    public Set<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Set<Specification> specifications) {
        this.specifications = specifications;
    }

    public Set<MeasureScale> getMeasureScales() {
        return measureScales;
    }

    public void setMeasureScales(Set<MeasureScale> measureScales) {
        this.measureScales = measureScales;
    }

}
