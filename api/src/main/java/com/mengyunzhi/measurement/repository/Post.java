package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 岗位实体
 */
@Entity
@ApiModel(value = "post (岗位)", description = "工作岗位")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    @Column(unique = true)
    private String name;
    @ApiModelProperty("描述")
    private String discription;
    @ApiModelProperty("拼音")
    private String pinyin;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("更新时间")
    private Long updateTime;
    @ManyToOne
    @ApiModelProperty("创建岗位的用户")
    private User createUser;

    @ApiModelProperty("部门类型列表")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "department_type_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "department_type_id"))
    @Lazy
    protected Set<DepartmentType> departmentTypes = new HashSet<DepartmentType>();

    public Post() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<DepartmentType> getDepartmentTypes() {
        return departmentTypes;
    }

    public void addDepartmentType(DepartmentType departmentType) {
        this.departmentTypes.add(departmentType);
    }

    public void setDepartmentTypes(Set<DepartmentType> departmentTypes) {
        this.departmentTypes = departmentTypes;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                '}';
    }
}
