package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 器具使用信息 实体
 * 修改人 高黎明
 */
@Entity
@ApiModel(value = "InstrumentEmploymentInfo (器具使用信息)", description = "器具使用信息实体")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 继承（策略 = 单表继承）
@DiscriminatorColumn(name = "DB_TYPE")  // 鉴别的列名为 DB_TYPE，将在数据表中生成该字段，用与区分强检与非强检
public abstract class InstrumentEmploymentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int STATUS_NEW = -1;     // 新建
    public static final int STATUS_NORMAL = 0;   // 正常
    public static final int STATUS_STOP = 1;     // 停用
    public static final int STATUS_SCRAP = 2;    // 报废
    public static final int STATUS_BACKED = 3;   // 被退回
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("新增时间")
    @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("安装地点")
    private String fixSite;
    @ManyToOne @JsonView({NoneJsonView.class})
    @ApiModelProperty("操作用户")
    private User createUser;
    @ApiModelProperty("精确度")
    private String accuracyName;
    @ApiModelProperty("测量范围")
    private String measureScaleName;
    @ApiModelProperty("出厂编号")
    private String serialNum;

    @ApiModelProperty("许可证号")
    private String licenseNum;
    @ApiModelProperty("器具名称")
    private String name;
    @ApiModelProperty("出厂名称")
    private String outOfFactoryName;
    @ApiModelProperty("规格型号")
    private String specificationName;
    @ManyToOne
    @ApiModelProperty("用途")
    private Purpose purpose;
    @ApiModelProperty("拥有企业")
    @ManyToOne
    private Department department;
    @ApiModelProperty("器具生产信息")
    @ManyToOne  @JsonView({NoneJsonView.class})
    private InstrumentProduction instrumentProduction;
    @ApiModelProperty("适用精确度")
    @ManyToOne @JsonView({NoneJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            ApplyJsonView.get.class})
    private Accuracy accuracy;
    @ApiModelProperty("适用测量范围")
    @ManyToOne @JsonView({NoneJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            ApplyJsonView.get.class})
    private MeasureScale measureScale;
    @ApiModelProperty("适用规格型号")
    @ManyToOne @JsonView({NoneJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            ApplyJsonView.get.class})
    private Specification specification;
    @ApiModelProperty("器具类别")
    @ManyToOne
    private InstrumentType instrumentType;

    @ManyToOne
    @ApiModelProperty("生产企业（生产部门）")
    private Department generativeDepartment;
    @ApiModelProperty("器具状态： -1：未通过审核； 0：正常； 1：停用 2：报废")
    private int status = STATUS_NEW; // 器具状态：

    public InstrumentEmploymentInfo() {
    }

    @Override
    public String toString() {
        return "InstrumentEmploymentInfo{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", fixSite='" + fixSite + '\'' +
                ", createUser=" + createUser +
                ", accuracyName='" + accuracyName + '\'' +
                ", measureScaleName='" + measureScaleName + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", name='" + name + '\'' +
                ", outOfFactoryName='" + outOfFactoryName + '\'' +
                ", specificationName='" + specificationName + '\'' +
                ", purpose=" + purpose +
                ", department=" + department +
                ", instrumentProduction=" + instrumentProduction +
                ", accuracy=" + accuracy +
                ", measureScale=" + measureScale +
                ", specification=" + specification +
                ", instrumentType=" + instrumentType +
                ", generativeDepartment=" + generativeDepartment +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getFixSite() {
        return fixSite;
    }

    public void setFixSite(String fixSite) {
        this.fixSite = fixSite;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Department getGenerativeDepartment() {
        return generativeDepartment;
    }

    public void setGenerativeDepartment(Department generativeDepartment) {
        this.generativeDepartment = generativeDepartment;
    }

    public InstrumentProduction getInstrumentProduction() {
        return instrumentProduction;
    }

    public void setInstrumentProduction(InstrumentProduction instrumentProduction) {
        this.instrumentProduction = instrumentProduction;
    }

    public String getAccuracyName() {
        return accuracyName;
    }

    public void setAccuracyName(String accuracyName) {
        this.accuracyName = accuracyName;
    }

    public String getMeasureScaleName() {
        return measureScaleName;
    }

    public void setMeasureScaleName(String measureScaleName) {
        this.measureScaleName = measureScaleName;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutOfFactoryName() {
        return outOfFactoryName;
    }

    public void setOutOfFactoryName(String outOfFactoryName) {
        this.outOfFactoryName = outOfFactoryName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public MeasureScale getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(MeasureScale measureScale) {
        this.measureScale = measureScale;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
}
