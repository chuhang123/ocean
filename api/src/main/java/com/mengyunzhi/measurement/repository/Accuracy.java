package com.mengyunzhi.measurement.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 精度  实体
 */
@Entity
@ApiModel(value = "Accuracy 精度实体", description = "等级准确度示值偏差的值")
public class Accuracy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("精度值")
    private String value;
    @ApiModelProperty("拼音")
    private String pinyin;
    @ManyToOne
    @ApiModelProperty("操作用户")
    private User createUser;

    @ApiModelProperty("学科")
    @ManyToOne
    // 使用JsonProperty替换JsonIgnore, 实现只输入，不输出的目的
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Discipline discipline;

    public Accuracy() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "Accuracy{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", createUser=" + createUser +
                ", discipline=" + discipline +
                '}';
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

}
