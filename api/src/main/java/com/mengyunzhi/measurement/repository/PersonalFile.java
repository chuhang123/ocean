package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by liming on 17-4-28.
 * 人员资质管理综合查询界面
 */
@Entity
@ApiModel(value = "PersonalFile 人员资质管理综合查询")
public class PersonalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //使用ApiModelProperty注解来生成文档
    @ApiModelProperty("市") private String region;
    @ApiModelProperty("区县") private String county;
    @ApiModelProperty("技术机构")  private String technical;
    @ApiModelProperty("建标用户")  private String user;
    @ApiModelProperty("生产企业") private String generative;
    @ApiModelProperty("姓名") private String name;
    @ApiModelProperty("年龄") private String age;
    @ApiModelProperty("学历")  private String edubg;
    @ApiModelProperty("职称")  private String title;
    @ApiModelProperty("从业年限") private String occupation;
    @ApiModelProperty("资格证") private String qualifierCertificate;
    @ApiModelProperty("授权项目") private String authorizItem;
    @ApiModelProperty("发证单位") private String issueUnit;
    @ApiModelProperty("发证日期")  private String issueTime;
    @ApiModelProperty("有效期至") private String validityTime;

    public PersonalFile() {
    }

    public PersonalFile(String region, String county, String technical, String user, String generative, String name, String age, String edubg, String title, String occupation, String qualifierCertificate, String authorizItem, String issueUnit, String issueTime, String validityTime) {
        this.region = region;
        this.county = county;
        this.technical = technical;
        this.user = user;
        this.generative = generative;
        this.name = name;
        this.age = age;
        this.edubg = edubg;
        this.title = title;
        this.occupation = occupation;
        this.qualifierCertificate = qualifierCertificate;
        this.authorizItem = authorizItem;
        this.issueUnit = issueUnit;
        this.issueTime = issueTime;
        this.validityTime = validityTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGenerative() {
        return generative;
    }

    public void setGenerative(String generative) {
        this.generative = generative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEdubg() {
        return edubg;
    }

    public void setEdubg(String edubg) {
        this.edubg = edubg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getQualifierCertificate() {
        return qualifierCertificate;
    }

    public void setQualifierCertificate(String qualifierCertificate) {
        this.qualifierCertificate = qualifierCertificate;
    }

    public String getAuthorizItem() {
        return authorizItem;
    }

    public void setAuthorizItem(String authorizItem) {
        this.authorizItem = authorizItem;
    }

    public String getIssueUnit() {
        return issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(String validityTime) {
        this.validityTime = validityTime;
    }

    @Override
    public String toString() {
        return "PersonalFile{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", technical='" + technical + '\'' +
                ", User='" + user + '\'' +
                ", generative='" + generative + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", edubg='" + edubg + '\'' +
                ", title='" + title + '\'' +
                ", occupation='" + occupation + '\'' +
                ", qualifierCertificate='" + qualifierCertificate + '\'' +
                ", authorizItem='" + authorizItem + '\'' +
                ", issueUnit='" + issueUnit + '\'' +
                ", issueTime='" + issueTime + '\'' +
                ", validityTime='" + validityTime + '\'' +
                '}';
    }
}
