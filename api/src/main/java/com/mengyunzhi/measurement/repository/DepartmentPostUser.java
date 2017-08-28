package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 安强 on 2017/5/31.
 * 部门岗位用户中间表
 */
@Entity
@ApiModel(value = "DepartmentPostUser (部门岗位用户)", description = "部门岗位用户实体")
public class DepartmentPostUser {
    //参考网址http://codego.net/144328/
    //参考书《hibernate 实战》174
    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = -5419345300310440297L;
        @Column(name = "department_id", nullable = false, updatable = false)
        private Long departmentId;
        @Column(name = "post_id", nullable = false, updatable = false)
        private Long postId;
        @Column(name = "user_id", nullable = false, updatable = false)
        private Long userId;

        public Id() {
        }

        public Id(Long departmentId, Long postId, Long userId) {
            this.departmentId = departmentId;
            this.postId = postId;
            this.userId = userId;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
        }

        public Long getPostId() {
            return postId;
        }

        public void setPostId(Long postId) {
            this.postId = postId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Pk{" +
                    "departmentId=" + departmentId +
                    ", postId=" + postId +
                    ", userId=" + userId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (!departmentId.equals(id.departmentId)) return false;
            if (!postId.equals(id.postId)) return false;
            return userId.equals(id.userId);
        }

        @Override
        public int hashCode() {
            int result = departmentId.hashCode();
            result = 31 * result + postId.hashCode();
            result = 31 * result + userId.hashCode();
            return result;
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @ApiModelProperty("关联的部门")
    @JoinColumn(
            name = "department_id",
            insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @ApiModelProperty("关联的岗位")
    @JoinColumn(
            name = "post_id",
            insertable = false, updatable = false
    )
    private Post post;

    @ManyToOne
    @ApiModelProperty("关联的用户")
    @JoinColumn(
            name = "user_id",
            insertable = false, updatable = false
    )
    private User user;

    public DepartmentPostUser() {
    }

    public DepartmentPostUser(Department department, Post post, User user) {
        this.department = department;
        this.post = post;
        this.user = user;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.id.setDepartmentId(department.getId());
        this.department = department;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.id.setPostId(post.getId());
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.id.setUserId(user.getId());
        this.user = user;
    }

    @Override
    public String toString() {
        return "DepartmentPostUser{" +
                "id=" + id +
                ", department=" + department +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}






