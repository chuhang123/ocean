package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department_type_post")
@ApiModel(value = "DepartmentTypePost (部门类型-岗位)", description = "部门类型-岗位实体")
public class DepartmentTypePost {
    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1L;
        @Column(name = "department_type_id",
                nullable = false, updatable = false
        )
        private Long departmentTypeId;

        @Column(
                name = "post_id",
                nullable = false, updatable = false
        )
        private Long postId;

        public Id() {
        }

        public Id(Long dePartmentTypeId, Long postId) {
            this.departmentTypeId = dePartmentTypeId;
            this.postId = postId;
        }

        public Long getDePartmentTypeId() {
            return departmentTypeId;
        }

        public void setDePartmentTypeId(Long dePartmentTypeId) {
            this.departmentTypeId = dePartmentTypeId;
        }

        public Long getPostId() {
            return postId;
        }

        public void setPostId(Long postId) {
            this.postId = postId;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "dePartmentTypeId=" + departmentTypeId +
                    ", postId=" + postId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (!departmentTypeId.equals(id.departmentTypeId)) return false;
            return postId.equals(id.postId);
        }

        @Override
        public int hashCode() {
            int result = departmentTypeId.hashCode();
            result = 31 * result + postId.hashCode();
            return result;
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @ApiModelProperty("关联部门类型")
    @JoinColumn(
            name = "department_type_id",
            insertable = false,updatable = false
    )
    private DepartmentType departmentType;
    @ManyToOne
    @ApiModelProperty("关联岗位")
    @JoinColumn(
            name = "post_id",
            insertable = false,updatable = false
    )
    private Post post;

    public DepartmentTypePost(DepartmentType departmentType, Post post) {
        this.id = new Id(departmentType.getId(), post.getId());
        this.departmentType = departmentType;
        this.post = post;
    }

    public DepartmentTypePost() {
    }

    @Override
    public String toString() {
        return "DepartmentTypePost{" +
                "id=" + id +
                ", departmentType=" + departmentType +
                ", post=" + post +
                '}';
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.id.setDePartmentTypeId(departmentType.getId());
        this.departmentType = departmentType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.id.setPostId(post.getId());
        this.post = post;
    }
}
