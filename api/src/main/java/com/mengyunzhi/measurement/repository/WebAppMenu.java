package com.mengyunzhi.measurement.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 安强 on 2017/6/1.
 * 前台菜单  实体
 */
@Entity
@ApiModel(value = "WebAppMenu (前台菜单)", description = "前台菜单实体")
public class WebAppMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("拼音")
    private String pinyin;

    @ManyToOne
    @ApiModelProperty("操作用户")
    private User createUser;

    @ApiModelProperty("上级菜单 用以标记左侧菜单树的上下级关系")
    @JoinColumn(name = "p_id")
    @ManyToOne
    @Lazy
    private WebAppMenu parentWebAppMenu;

    @ApiModelProperty("上级路由 用以标记ui-touter中路由的上下级关系")
    @JoinColumn(name = "p_route_id")
    @ManyToOne
    @Lazy
    private WebAppMenu parentRouteWebAppMenu;

    @ApiModelProperty("是否为路由信息 路由信息进行路由配置 非路由信息只用于做权限处理")
    private Boolean isRoute = true;

    @ApiModelProperty("路由")
    @Column(unique = true)
    private String routeName;

    @ApiModelProperty("模块路径")
    private String templateUrl;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("对应控制器")
    private String controller;

    @ApiModelProperty("是否抽象菜单")
    private boolean isAbstract = false;

    @ApiModelProperty("权重 越大越靠前")
    private int weight = 0;

    @ApiModelProperty("状态")
    private Boolean status = true;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否显示徽章")
    private boolean isShowBadge = false;

    @ApiModelProperty("徽章样式")
    private String badgeStyle;

    @ApiModelProperty("徽章内容")
    private String badgeContent;

    @ApiModelProperty("是否显示")
    private Boolean isShow = true;

    public WebAppMenu() {
    }

    public WebAppMenu(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public WebAppMenu getParentWebAppMenu() {
        return parentWebAppMenu;
    }

    public void setParentWebAppMenu(WebAppMenu parentWebAppMenu) {
        this.parentWebAppMenu = parentWebAppMenu;
    }

    public WebAppMenu getParentRouteWebAppMenu() {
        return parentRouteWebAppMenu;
    }

    public void setParentRouteWebAppMenu(WebAppMenu parentRouteWebAppMenu) {
        this.parentRouteWebAppMenu = parentRouteWebAppMenu;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public String getBadgeStyle() {
        return badgeStyle;
    }

    public void setBadgeStyle(String badgeStyle) {
        this.badgeStyle = badgeStyle;
    }

    public String getBadgeContent() {
        return badgeContent;
    }

    public void setBadgeContent(String badgeContent) {
        this.badgeContent = badgeContent;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isShowBadge() {
        return isShowBadge;
    }

    public void setShowBadge(boolean showBadge) {
        isShowBadge = showBadge;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public void setRoute(Boolean route) {
        isRoute = route;
    }

    public Boolean getRoute() {
        return isRoute;
    }

    @Override
    public String toString() {
        return "WebAppMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", createUser=" + createUser +
                ", parentWebAppMenu=" + parentWebAppMenu +
                ", parentRouteWebAppMenu=" + parentRouteWebAppMenu +
                ", isRoute=" + isRoute +
                ", routeName='" + routeName + '\'' +
                ", templateUrl='" + templateUrl + '\'' +
                ", description='" + description + '\'' +
                ", controller='" + controller + '\'' +
                ", isAbstract=" + isAbstract +
                ", weight=" + weight +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", isShowBadge=" + isShowBadge +
                ", badgeStyle='" + badgeStyle + '\'' +
                ", badgeContent='" + badgeContent + '\'' +
                ", isShow=" + isShow +
                '}';
    }
}
