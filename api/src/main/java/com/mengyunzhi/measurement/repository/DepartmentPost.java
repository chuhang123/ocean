package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 安强 on 2017/6/2.
 * 部门岗位  之间的关系
 */
@ApiModel(value = "DepartmentPost (部门岗位)", description = "部门岗位关系")
public class DepartmentPost {
    @ApiModelProperty("关联的部门")
    private Department department;

    @ApiModelProperty("关联的岗位")
    private Post post;

    public DepartmentPost(Department department, Post post) {
        this.department = department;
        this.post = post;
    }

    public DepartmentPost() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "DepartmentPost{" +
                "department=" + department +
                ", post=" + post +
                '}';
    }
}
