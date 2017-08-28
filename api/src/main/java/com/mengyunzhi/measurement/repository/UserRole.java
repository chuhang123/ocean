package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/6/14.
 * 用户 - 角色
 */
@Entity
@Table(name = "user_role")
@ApiModel("用户-角色")
public class UserRole implements Serializable{
    @Embeddable
    public static class Id implements Serializable {
        @ApiModelProperty("用户ID")
        @Column(name = "user_id", insertable = false, updatable = false)
        protected Long userId;
        @ApiModelProperty("角色ID")
        @Column(name = "role_id", insertable = false, updatable = false)
        protected Long roleId;

        public Id() {
        }

        public Id(Long userId, Long roleId) {
            this.userId = userId;
            this.roleId = roleId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;

            Id id = (Id) o;

            if (!getUserId().equals(id.getUserId())) return false;
            return getRoleId().equals(id.getRoleId());
        }

        @Override
        public int hashCode() {
            int result = getUserId().hashCode();
            result = 31 * result + getRoleId().hashCode();
            return result;
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    protected User user;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    protected Role role;

    public UserRole() {
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
