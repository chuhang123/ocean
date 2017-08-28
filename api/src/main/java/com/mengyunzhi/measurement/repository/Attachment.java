package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;


@Entity
@ApiModel(value = "Attachment (附件)", description = "附件")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("操作用户")
    private User operator;

    @ApiModelProperty("sha1值")
    private String sha1;

    @ApiModelProperty("md5值")
    private String md5;

    @ApiModelProperty("附件扩展名")
    private String ext;

    @ApiModelProperty("附件大小")
    private String size;

    @ApiModelProperty("附件上传时间")
    private Long createTime;

    @ApiModelProperty("附件更新时间")
    private Long updateTime;

    @ApiModelProperty("附件名称")
    private String name;

    @ApiModelProperty("附件存储路径")
    private String savePath;

    @ApiModelProperty("附件存储名称")
    private String saveName;

    @ApiModelProperty("MIME类型")
    private String MIME;

    public Attachment() {
    }

    public Attachment(User operator, String sha1, String md5, String ext,
                      String size, Long createTime, Long updateTime,
                      String name, String savePath, String saveName, String MIME) {
        this.operator = operator;
        this.sha1 = sha1;
        this.md5 = md5;
        this.ext = ext;
        this.size = size;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.savePath = savePath;
        this.saveName = saveName;
        this.MIME = MIME;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public void setUpdateTime(Long updateTimeP) {
        this.updateTime = updateTimeP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSavepath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getMIME() {
        return MIME;
    }

    public void setMIME(String MIME) {
        this.MIME = MIME;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", operator=" + operator +
                ", sha1='" + sha1 + '\'' +
                ", md5='" + md5 + '\'' +
                ", ext='" + ext + '\'' +
                ", size='" + size + '\'' +
                ", createTime=" + createTime +
                ", updateTimeP=" + updateTime +
                ", name='" + name + '\'' +
                ", savepath='" + savePath + '\'' +
                ", savename='" + saveName + '\'' +
                ", MIME='" + MIME + '\'' +
                '}';
    }
}

