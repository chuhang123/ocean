package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/6/14.
 * 由于在Role的第54行，进行多对多的关联时，手动的指定了中间表名称。
 * 所以在此，必须使用@Table来指定表名，否则将提示重复定义web_app_menu_role的冲突。
 * 原因是这样：由于我们在Role中，定义的是多对多的关系，所以需要有一张中间表来做支持。
 * 指定中间表的名称后，hibernate会把数据存在该表中。并认为，该表的作用就是起Role和WebAppMenu的中间表的作用。
 * 但如果此时，进行另外一个实体映射的时候，系统发现自动的映射策略生成的表名竟然与前面的多对多关联表名字相同。
 * 就会提示我们说：这个表的名字，我们在前面已经用过了。你是不是考虑起一个别的名字。
 * 这时候，我们需要使用@Table来告诉Hibernate：是的没错，本实体对应的就是那个前面我们声明过的多对多的中间表。
 */
@Entity
@Table(name = "web_app_menu_role")
@ApiModel("前台菜单-角色")
public class WebAppMenuRole implements Serializable{
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "role_id", updatable = false, insertable = false)
        private Long roleId;

        // 中间表只接受查询，不能进行增加与更新
        @Column(name = "web_app_menu_id", updatable = false, insertable = false)
        private Long webAppMenuId;

        public Id()
        {}

        public Id(Long roleId, Long webAppMenuId) {
            this.roleId = roleId;
            this.webAppMenuId = webAppMenuId;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public Long getWebAppMenuId() {
            return webAppMenuId;
        }

        public void setWebAppMenuId(Long webAppMenuId) {
            this.webAppMenuId = webAppMenuId;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.roleId.equals(this.roleId) && this.webAppMenuId.equals(that.webAppMenuId);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return roleId.hashCode() + webAppMenuId.hashCode();
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    // 只能通过Role来进行中间表的CUD操作
    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    protected Role role;

    // 只能通过Role来进行中间表的CUD操作
    @ManyToOne
    @JoinColumn(name = "web_app_menu_id", insertable = false, updatable = false)
    protected WebAppMenu webAppMenu;

    public WebAppMenuRole() {
    }

    public WebAppMenuRole(Role role, WebAppMenu webAppMenu) {
        this.id = new Id(role.getId(), webAppMenu.getId());
        this.role = role;
        this.webAppMenu = webAppMenu;
    }

    public WebAppMenuRole(Id id, Role role, WebAppMenu webAppMenu) {
        this.id = id;
        this.role = role;
        this.webAppMenu = webAppMenu;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        this.id.setRoleId(role.getId());
    }

    public WebAppMenu getWebAppMenu() {
        return webAppMenu;
    }

    public void setWebAppMenu(WebAppMenu webAppMenu) {
        this.webAppMenu = webAppMenu;
        this.id.setWebAppMenuId(webAppMenu.getId());
    }
}
