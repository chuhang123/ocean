package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.InstrumentCheckInfoJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * create by Gaoliming 0225
 * 强制检定器具检定信息的实体
 */
@Entity
@ApiModel(value = "InstrumentCheckInfo (强制检定器具检定信息)", description = "器具检定信息实体")
public class InstrumentCheckInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("证书编号")
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	private String certificateNum;
	@ApiModelProperty("应收费用 单位:分")
	private Long cost;
	@ApiModelProperty("检定日期")
	private Date checkDate;
	@ApiModelProperty("有效期至")
	private Date validityDate;
	@ApiModelProperty("报警日期")
	private Date alertDate;
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	@ManyToOne @ApiModelProperty("关联证书状态实体")
	private CertificateStatus certificateStatus;
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	@ManyToOne @ApiModelProperty("关联证书类型实体")
	private InstrumentCertificateType instrumentCertificateType;
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	@ManyToOne @ApiModelProperty("关联部门实体")
	private Department checkDepartment;
	@ManyToOne @ApiModelProperty("关联检定结果实体")
	private CheckResult checkResult;
	@ManyToOne @ApiModelProperty("关联强检器具使用信息实体")		//绑定数据的时候无法绑定抽象类
	private MandatoryInstrument mandatoryInstrument;
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	@ManyToOne @ApiModelProperty("创建用户")
	private User createUser;
	@JsonView(InstrumentCheckInfoJsonView.getAllOfCurrentUser.class)
	@ManyToOne @ApiModelProperty("关联器具生产信息")
	private InstrumentProduction instrumentProduction;
	@ManyToOne @ApiModelProperty("关联人员资质(批准人)")
	private Qualifier ratifierQualifier;
	@ManyToOne @ApiModelProperty("关联人员资质(核验员)")
	private Qualifier verifierQualifier;
	@ManyToOne @ApiModelProperty("关联人员资质(检定员)")
	private Qualifier inspectorQualifier;

	public InstrumentCheckInfo() {
	}


	@Override
	public String toString() {
		return "InstrumentCheckInfo{" +
				"id=" + id +
				", certificateNum='" + certificateNum + '\'' +
				", cost=" + cost +
				", checkDate=" + checkDate +
				", validityDate=" + validityDate +
				", alertDate=" + alertDate +
				", certificateStatus=" + certificateStatus +
				", instrumentCertificateType=" + instrumentCertificateType +
				", checkDepartment=" + checkDepartment +
				", checkResult=" + checkResult +
				", mandatoryInstrument=" + mandatoryInstrument +
				", createUser=" + createUser +
				", instrumentProduction=" + instrumentProduction +
				", ratifierQualifier=" + ratifierQualifier +
				", verifierQualifier=" + verifierQualifier +
				", inspectorQualifier=" + inspectorQualifier +
				'}';
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

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Date getCheckDate() {

		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public Date getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	public CertificateStatus getCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(CertificateStatus certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	public InstrumentCertificateType getInstrumentCertificateType() {
		return instrumentCertificateType;
	}

	public void setInstrumentCertificateType(InstrumentCertificateType instrumentCertificateType) {
		this.instrumentCertificateType = instrumentCertificateType;
	}

	public Department getCheckDepartment() {
		return checkDepartment;
	}

	public void setCheckDepartment(Department checkDepartment) {
		this.checkDepartment = checkDepartment;
	}

	public CheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public MandatoryInstrument getMandatoryInstrument() {
		return mandatoryInstrument;
	}

	public void setMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
		this.mandatoryInstrument = mandatoryInstrument;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public InstrumentProduction getInstrumentProduction() {
		return instrumentProduction;
	}

	public void setInstrumentProduction(InstrumentProduction instrumentProduction) {
		this.instrumentProduction = instrumentProduction;
	}

	public Qualifier getRatifierQualifier() {
		return ratifierQualifier;
	}

	public void setRatifierQualifier(Qualifier ratifierQualifier) {
		this.ratifierQualifier = ratifierQualifier;
	}

	public Qualifier getVerifierQualifier() {
		return verifierQualifier;
	}

	public void setVerifierQualifier(Qualifier verifierQualifier) {
		this.verifierQualifier = verifierQualifier;
	}

	public Qualifier getInspectorQualifier() {
		return inspectorQualifier;
	}

	public void setInspectorQualifier(Qualifier inspectorQualifier) {
		this.inspectorQualifier = inspectorQualifier;
	}
}
