package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by panjie on 17/7/25.
 * 前台菜单数据初始化
 */
@Component
public class WebAppMenuDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(WebAppMenuDataInit.class.getName());
    @Autowired
    private WebAppMenuRepository webAppMenuRepository; // 前台菜单
    // 前台菜单
    protected Set<WebAppMenu> webAppMenus = new HashSet<>();

    // 权重
    protected int weight = HIGHEST_PRECEDENCE;

    private WebAppMenu mainMenu;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<WebAppMenu> webAppMenusAll = webAppMenuRepository.findAll();
        if (webAppMenusAll.size() == 0) {
            mainMenu = new WebAppMenu();
            mainMenu.setAbstract(true);
            mainMenu.setTemplateUrl("views/main.html");
            mainMenu.setShow(false);
            mainMenu.setName("用于仪表台及大数据平台继承");
            mainMenu.setRouteName("main");
            mainMenu.setWeight(weight++);
            webAppMenus.add(mainMenu);

            logger.info("主菜单");
            this.addWebAppMenuMain();

            logger.info("强检器具");
            this.addWebAppMenuIntegrated();
//
//            logger.info("非强检器具");
//            this.addWebAppMenuOptions();
//
            logger.info("标准装置管理");
            this.addWebAppMenuStandard();

            logger.info("用户（部门）管理");
            this.addDepartment();
//
//            logger.info("器具计量产品");
//            this.addWebAppMenuMeasuringDevice();
//
//            logger.info("重点用能企业");
//            this.addWebAppMenuEnergyEnterprise();
//
//            logger.info("监督抽查");
//            this.addWebAppMenuSupervise();
//
//            logger.info("行政许可");
//            this.addWebAppMenuBusiness();

            logger.info("人员资质");
            this.addWebAppMenusPersonnel();
            logger.info("系统设置");
            this.addWebAppMenuSystem();
            logger.info("个人中心");
            this.addWebAppMenuPersonalCenter();
//
//            logger.info("注册信息(用户管理)");
//            this.addWebAppMenuRegister();
//
//            logger.info("我的工作");
//            this.addWebAppMenuMyWork();
//
//            logger.info("其它功能");
//            this.addWebAppMenuOther();
//
//            logger.info("计量科技园地");
//            this.addWebAppMenuTechnology();
//
//            logger.info("最高行业标准");
//            this.addWebAppMenuHighestStandard();

            webAppMenuRepository.save(webAppMenus);

        }
    }

    // 用户，部门
    private void addDepartment() {
        WebAppMenu departmentMenu = new WebAppMenu();
        departmentMenu.setAbstract(true);
        departmentMenu.setRouteName("department");
        departmentMenu.setName("用户管理");
        departmentMenu.setWeight(weight++);
        webAppMenus.add(departmentMenu);

        WebAppMenu instrumentUserMenu = new WebAppMenu();
        instrumentUserMenu.setRouteName("instrumentUser");
        instrumentUserMenu.setName("器具用户");
        instrumentUserMenu.setDescription("器具用户管理");
        instrumentUserMenu.setParentRouteWebAppMenu(departmentMenu);
        instrumentUserMenu.setParentWebAppMenu(departmentMenu);
        instrumentUserMenu.setWeight(weight++);
        webAppMenus.add(instrumentUserMenu);

        WebAppMenu technicalInstitutionMenu = new WebAppMenu();
        technicalInstitutionMenu.setRouteName("technicalInstitution");
        technicalInstitutionMenu.setName("技术机构");
        technicalInstitutionMenu.setDescription("技术机构管理");
        technicalInstitutionMenu.setParentRouteWebAppMenu(departmentMenu);
        technicalInstitutionMenu.setParentWebAppMenu(departmentMenu);
        technicalInstitutionMenu.setWeight(weight++);
        webAppMenus.add(technicalInstitutionMenu);

        WebAppMenu enterpriseMenu = new WebAppMenu();
        enterpriseMenu.setRouteName("enterprise");
        enterpriseMenu.setName("生产企业");
        enterpriseMenu.setDescription("生产企业管理");
        enterpriseMenu.setParentRouteWebAppMenu(departmentMenu);
        enterpriseMenu.setParentWebAppMenu(departmentMenu);
        enterpriseMenu.setWeight(weight++);
        webAppMenus.add(enterpriseMenu);

        WebAppMenu managementMenu = new WebAppMenu();
        managementMenu.setRouteName("management");
        managementMenu.setName("管理部门");
        managementMenu.setDescription("管理部门");
        managementMenu.setParentRouteWebAppMenu(departmentMenu);
        managementMenu.setParentWebAppMenu(departmentMenu);
        managementMenu.setWeight(weight++);
        webAppMenus.add(managementMenu);
    }
    protected void addWebAppMenuMain() {
        logger.info("----- 添加前台菜单信息 -----");

        WebAppMenu dashboardMenu = new WebAppMenu();
        dashboardMenu.setName("仪表台");
        dashboardMenu.setPinyin("yibiaotai");
        dashboardMenu.setShowBadge(true);
        dashboardMenu.setBadgeStyle("success");
        dashboardMenu.setBadgeContent("V0.1");
        dashboardMenu.setDescription("动态显示系统各项数据指标");
        dashboardMenu.setRouteName("dashboard");
        dashboardMenu.setWeight(weight++);
        dashboardMenu.setParentRouteWebAppMenu(mainMenu);
        webAppMenus.add(dashboardMenu);

        WebAppMenu analyticsMenu = new WebAppMenu();
        analyticsMenu.setName("大数据平台");
        analyticsMenu.setRouteName("analytics");
        analyticsMenu.setShowBadge(true);
        analyticsMenu.setBadgeStyle("warning");
        analyticsMenu.setBadgeContent("NEW");
        analyticsMenu.setParentRouteWebAppMenu(mainMenu);
        analyticsMenu.setWeight(weight++);
        webAppMenus.add(analyticsMenu);

        WebAppMenu mandatoryCalibrateRecordMenu = new WebAppMenu();
        mandatoryCalibrateRecordMenu.setName("强制检定备案");
        mandatoryCalibrateRecordMenu.setRouteName("MandatoryCalibrateRecord");
        mandatoryCalibrateRecordMenu.setShowBadge(true);
        mandatoryCalibrateRecordMenu.setBadgeStyle("warning");
        mandatoryCalibrateRecordMenu.setBadgeContent("NEW");
        mandatoryCalibrateRecordMenu.setParentRouteWebAppMenu(mainMenu);
        mandatoryCalibrateRecordMenu.setWeight(weight++);
        webAppMenus.add(mandatoryCalibrateRecordMenu);
    }

    // 强检菜单
    protected void addWebAppMenuIntegrated() {
        WebAppMenu mandatoryMenu = new WebAppMenu();
        mandatoryMenu.setAbstract(true);
        mandatoryMenu.setRouteName("mandatory");
        mandatoryMenu.setName("强检器具管理");
        mandatoryMenu.setWeight(weight++);
        webAppMenus.add(mandatoryMenu);

        WebAppMenu instrumentMenu = new WebAppMenu();
        instrumentMenu.setRouteName("Instrument");
        instrumentMenu.setParentRouteWebAppMenu(mandatoryMenu);
        instrumentMenu.setParentWebAppMenu(mandatoryMenu);
        instrumentMenu.setTemplateUrl("views/mandatory/instrument/index.html");
        instrumentMenu.setName("综合查询");
        instrumentMenu.setDescription("强制检定管理综合查询");
        instrumentMenu.setWeight(weight++);
        webAppMenus.add(instrumentMenu);

        WebAppMenu integratedMenu = new WebAppMenu();
        integratedMenu.setAbstract(false);
        integratedMenu.setRouteName("Integrated");
        integratedMenu.setTemplateUrl("views/mandatory/integrated/index.html");
        integratedMenu.setName("档案管理");
        integratedMenu.setWeight(weight++);
        integratedMenu.setDescription("强制检定管理计量器具用户强制检定计量器具档案");
        integratedMenu.setParentRouteWebAppMenu(mandatoryMenu);
        integratedMenu.setParentWebAppMenu(mandatoryMenu);
        webAppMenus.add(integratedMenu);

        WebAppMenu integratedAdd = new WebAppMenu();
        integratedAdd.setRouteName("IntegratedAdd");
        integratedAdd.setParentRouteWebAppMenu(mandatoryMenu);
        integratedAdd.setParentWebAppMenu(mandatoryMenu);
        integratedAdd.setName("新增");
        integratedAdd.setDescription("强制检定管理计量器具用户强制检定计量器具档案 -- 新增(申请)");
        integratedAdd.setShow(false);
        webAppMenus.add(integratedAdd);

//        WebAppMenu numberCategoriesMenu = new WebAppMenu();
//        numberCategoriesMenu.setRouteName("NumberCategories");
//        numberCategoriesMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        numberCategoriesMenu.setParentWebAppMenu(mandatoryMenu);
//        numberCategoriesMenu.setName("分类统计");
//        numberCategoriesMenu.setWeight(weight++);
//        numberCategoriesMenu.setDescription("强制检定管理计量器具强制检定计量器具分类统计");
//        webAppMenus.add(numberCategoriesMenu);


//        WebAppMenu userapplication = new WebAppMenu();
//        userapplication.setRouteName("Userapplication");
//        userapplication.setParentRouteWebAppMenu(mandatoryMenu);
//        userapplication.setParentWebAppMenu(mandatoryMenu);
//        userapplication.setName("用户检定申请");
//        userapplication.setWeight(weight++);
//        userapplication.setDescription("强制检定管理计量器具用户检定申请");
//        webAppMenus.add(userapplication);

//        WebAppMenu replyMenu = new WebAppMenu();
//        replyMenu.setRouteName("Reply");
//        replyMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        replyMenu.setParentWebAppMenu(mandatoryMenu);
//        replyMenu.setName("机构申请回复");
//        replyMenu.setDescription("强制检定管理计量器具机构申请回复");
//        webAppMenus.add(replyMenu);

//        WebAppMenu checkDetailMenu = new WebAppMenu();
//        checkDetailMenu.setRouteName("CheckDetail");
//        checkDetailMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        checkDetailMenu.setParentWebAppMenu(mandatoryMenu);
//        checkDetailMenu.setName("检定信息");
//        checkDetailMenu.setDescription("强制检定管理计量器具检定信息");
//        webAppMenus.add(replyMenu);
        WebAppMenu integratedAuditMenu = new WebAppMenu();
        integratedAuditMenu.setAbstract(false);
        integratedAuditMenu.setRouteName("integratedAudit");
        integratedAuditMenu.setName("检定档案管理");
        integratedAuditMenu.setWeight(weight++);
        integratedAuditMenu.setDescription("器具的检定数据管理");
        integratedAuditMenu.setParentRouteWebAppMenu(mandatoryMenu);
        integratedAuditMenu.setParentWebAppMenu(mandatoryMenu);
        webAppMenus.add(integratedAuditMenu);

        WebAppMenu instrumentCheckInfoManageMenu = new WebAppMenu();
        instrumentCheckInfoManageMenu.setRouteName("instrumentCheckInfoManage");
        instrumentCheckInfoManageMenu.setParentRouteWebAppMenu(mandatoryMenu);
        instrumentCheckInfoManageMenu.setParentWebAppMenu(mandatoryMenu);
        instrumentCheckInfoManageMenu.setName("器具检定综合查询");
        instrumentCheckInfoManageMenu.setWeight(weight++);
        instrumentCheckInfoManageMenu.setDescription("强制检定管理计量器具强制检定计量器具年检定量统计");
        webAppMenus.add(instrumentCheckInfoManageMenu);

        WebAppMenu instrumentCheckInfoMenu = new WebAppMenu();
        instrumentCheckInfoMenu.setRouteName("instrumentCheckInfo");
        instrumentCheckInfoMenu.setParentRouteWebAppMenu(mandatoryMenu);
        instrumentCheckInfoMenu.setParentWebAppMenu(mandatoryMenu);
        instrumentCheckInfoMenu.setName("器具检定档案管理");
        instrumentCheckInfoMenu.setWeight(weight++);
        instrumentCheckInfoMenu.setDescription("强制检定管理计量器具强制检定计量器具年检定量统计");
        webAppMenus.add(instrumentCheckInfoMenu);

        WebAppMenu appointCheckInstrumentMenu = new WebAppMenu();
        appointCheckInstrumentMenu.setRouteName("appointCheckInstrument");
        appointCheckInstrumentMenu.setParentRouteWebAppMenu(mandatoryMenu);
        appointCheckInstrumentMenu.setParentWebAppMenu(mandatoryMenu);
        appointCheckInstrumentMenu.setName("指定检定器具管理");
        appointCheckInstrumentMenu.setWeight(weight++);
        appointCheckInstrumentMenu.setDescription("技术机构中被指定的强检器具档案");
        webAppMenus.add(appointCheckInstrumentMenu);

//        WebAppMenu instrumentCheckInfoStatisticsMenu = new WebAppMenu();
//        instrumentCheckInfoStatisticsMenu.setRouteName("instrumentCheckInfoStatistics");
//        instrumentCheckInfoStatisticsMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        instrumentCheckInfoStatisticsMenu.setParentWebAppMenu(mandatoryMenu);
//        instrumentCheckInfoStatisticsMenu.setName("检定统计");
//        instrumentCheckInfoStatisticsMenu.setWeight(weight++);
//        instrumentCheckInfoStatisticsMenu.setDescription("强制检定管理计量器具强制检定计量器具年检定量统计");
//        webAppMenus.add(instrumentCheckInfoStatisticsMenu);
//
//        WebAppMenu passRateMenu = new WebAppMenu();
//        passRateMenu.setRouteName("PassRate");
//        passRateMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        passRateMenu.setParentWebAppMenu(mandatoryMenu);
//        passRateMenu.setTemplateUrl("views/mandatory/passrate/index.html");
//        passRateMenu.setName("合格率统计");
//        passRateMenu.setDescription("强制检定管理计量器具强制检定计量器具合格率统计");
//        webAppMenus.add(passRateMenu);


    }

    // 非强检菜单
    protected void addWebAppMenuOptions() {
        WebAppMenu optionalMenu = new WebAppMenu();
        optionalMenu.setAbstract(true);
        optionalMenu.setRouteName("optional");
        optionalMenu.setWeight(weight++);
        optionalMenu.setName("非强制检定管理");
        webAppMenus.add(optionalMenu);

        WebAppMenu numberSubjectsMenu = new WebAppMenu();
        numberSubjectsMenu.setRouteName("NumberSubjects");
        numberSubjectsMenu.setParentRouteWebAppMenu(optionalMenu);
        numberSubjectsMenu.setParentWebAppMenu(optionalMenu);
        numberSubjectsMenu.setWeight(weight++);
        numberSubjectsMenu.setName("非强制检定工作计量器具档案");
        numberSubjectsMenu.setDescription("非强制检定管理计量器具非强制检定工作计量器具档案");
        webAppMenus.add(numberSubjectsMenu);

        WebAppMenu optionalIntegratedMenu = new WebAppMenu();
        optionalIntegratedMenu.setRouteName("optionalIntegrated");
        optionalIntegratedMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalIntegratedMenu.setParentWebAppMenu(optionalMenu);
        optionalIntegratedMenu.setTemplateUrl("views/optional/integrated/index.html");
        optionalIntegratedMenu.setName("非强制检定工作计量器具查询");
        optionalIntegratedMenu.setDescription("非强制检定管理非强制检定工作计量器具查询");

        WebAppMenu integratedAddMenu = new WebAppMenu();
        integratedAddMenu.setRouteName("optionalIntegratedAdd");
        integratedAddMenu.setParentRouteWebAppMenu(optionalMenu);
        integratedAddMenu.setParentWebAppMenu(optionalMenu);
        integratedAddMenu.setTemplateUrl("views/optional/integrated/add.html");
        integratedAddMenu.setName("添加");
        integratedAddMenu.setShow(false);
        integratedAddMenu.setDescription("非强制检定管理非强制检定工作计量器具查询--添加");
        webAppMenus.add(integratedAddMenu);

        WebAppMenu optionalCheckDetailMenu = new WebAppMenu();
        optionalCheckDetailMenu.setRouteName("CheckDetail");
        optionalCheckDetailMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalCheckDetailMenu.setParentWebAppMenu(optionalMenu);
        optionalCheckDetailMenu.setTemplateUrl("views/optional/checkdetail/index.html");
        optionalCheckDetailMenu.setName("检定档案管理");
        optionalCheckDetailMenu.setDescription("非强制检定管理计量器具检定信息");
        webAppMenus.add(optionalCheckDetailMenu);

        WebAppMenu optionalUserapplicationMenu = new WebAppMenu();
        optionalUserapplicationMenu.setRouteName("Userapplication");
        optionalUserapplicationMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalUserapplicationMenu.setParentWebAppMenu(optionalMenu);
        optionalUserapplicationMenu.setTemplateUrl("views/optional/userapplication/index.html");
        optionalUserapplicationMenu.setName("用户检定申请");
        optionalUserapplicationMenu.setDescription("非强制检定管理计量器具用户检定申请");
        webAppMenus.add(optionalUserapplicationMenu);

//        WebAppMenu optionalReplyMenu = new WebAppMenu();
//        optionalReplyMenu.setRouteName("Reply");
//        optionalReplyMenu.setParentRouteWebAppMenu(optionalMenu);
//        optionalReplyMenu.setParentWebAppMenu(optionalMenu);
//        optionalReplyMenu.setTemplateUrl("views/optional/reply/index.html");
//        optionalReplyMenu.setName("机构申请回复");
//        optionalReplyMenu.setDescription("非强制检定管理计量器具机构申请回复");
//        webAppMenus.add(optionalReplyMenu);
    }

    // 技术机构
    protected void addWebAppMenuStandard() {
        WebAppMenu standardMenu = new WebAppMenu();
        standardMenu.setAbstract(true);
        standardMenu.setRouteName("standard");
        standardMenu.setWeight(weight++);
        standardMenu.setName("标准装置管理");
        webAppMenus.add(standardMenu);

        WebAppMenu standardFileMenu = new WebAppMenu();
        standardFileMenu.setRouteName("File");
        standardFileMenu.setParentRouteWebAppMenu(standardMenu);
        standardFileMenu.setParentWebAppMenu(standardMenu);
        standardFileMenu.setName("综合查询");
        standardFileMenu.setDescription("技术机构管理技术机构计量标准装置综合查询");
        standardFileMenu.setWeight(weight++);
        webAppMenus.add(standardFileMenu);

        WebAppMenu standardFileAddMenu = new WebAppMenu();
        standardFileAddMenu.setRouteName("FileAdd");
        standardFileAddMenu.setParentRouteWebAppMenu(standardMenu);
        standardFileAddMenu.setParentWebAppMenu(standardFileMenu);
        standardFileAddMenu.setName("技术机构计量标准装置一览表--新增");
        standardFileAddMenu.setDescription("技术机构管理技术机构计量标准装置一览表--新增");
        standardFileAddMenu.setShow(false);
        standardFileAddMenu.setWeight(weight++);
        webAppMenus.add(standardFileAddMenu);

        WebAppMenu deviceSetManageMenu = new WebAppMenu();
        deviceSetManageMenu.setRouteName("deviceSetManage");
        deviceSetManageMenu.setParentRouteWebAppMenu(standardMenu);
        deviceSetManageMenu.setParentWebAppMenu(standardMenu);
        deviceSetManageMenu.setName("档案管理");
        deviceSetManageMenu.setDescription("技术机构 -- 标准装置管理");
        deviceSetManageMenu.setWeight(weight++);
        webAppMenus.add(deviceSetManageMenu);

        WebAppMenu standardFixedassetsMenu = new WebAppMenu();
//        standardFixedassetsMenu.setRouteName("Fixedassets");
//        standardFixedassetsMenu.setParentRouteWebAppMenu(standardMenu);
//        standardFixedassetsMenu.setParentWebAppMenu(standardFileMenu);
//        standardFixedassetsMenu.setName("技术机构固定资产一览表");
//        standardFixedassetsMenu.setDescription("技术机构管理技术机构固定资产一览表");
//        webAppMenus.add(standardFixedassetsMenu);

        WebAppMenu standardAuthorizationMenu = new WebAppMenu();
        standardAuthorizationMenu.setRouteName("Authorization");
        standardAuthorizationMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationMenu.setParentWebAppMenu(standardMenu);
        standardAuthorizationMenu.setName("授权检定项目综合查询");
        standardAuthorizationMenu.setWeight(weight++);
        standardAuthorizationMenu.setDescription("技术机构管理技术机构授权检定项目综合查询");
        webAppMenus.add(standardAuthorizationMenu);


        WebAppMenu standardAuthorizationManageMenu = new WebAppMenu();
        standardAuthorizationManageMenu.setRouteName("FileDeviceInstrument");
        standardAuthorizationManageMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationManageMenu.setParentWebAppMenu(standardMenu);
        standardAuthorizationManageMenu.setName("授权检定项目管理");
        standardAuthorizationManageMenu.setWeight(weight++);
        standardAuthorizationManageMenu.setDescription("授权检定项目综合管理");
        webAppMenus.add(standardAuthorizationManageMenu);

        WebAppMenu standardAuthorizationAddMenu = new WebAppMenu();
        standardAuthorizationAddMenu.setRouteName("AuthorizationManageAdd");
        standardAuthorizationAddMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationAddMenu.setParentWebAppMenu(standardAuthorizationManageMenu);
        standardAuthorizationAddMenu.setName("授权检定项目管理--新增");
        standardAuthorizationAddMenu.setDescription("授权检定项目综合管理--新增");
        standardAuthorizationAddMenu.setShow(false);
        standardAuthorizationAddMenu.setWeight(weight++);
        webAppMenus.add(standardAuthorizationAddMenu);

//        WebAppMenu standardAbilityMenu = new WebAppMenu();
//        standardAbilityMenu.setRouteName("Ability");
//        standardAbilityMenu.setParentRouteWebAppMenu(standardMenu);
//        standardAbilityMenu.setParentWebAppMenu(standardMenu);
//        standardAbilityMenu.setWeight(weight++);
//        standardAbilityMenu.setName("技术机构能力建设申请、进度表");
//        standardAbilityMenu.setDescription("技术机构管理技术机构能力建设申请进度表");
//        webAppMenus.add(standardAbilityMenu);

//        WebAppMenu standardScheduleMenu = new WebAppMenu();
//        standardScheduleMenu.setRouteName("Schedule");
//        standardScheduleMenu.setParentRouteWebAppMenu(standardMenu);
//        standardScheduleMenu.setParentWebAppMenu(standardMenu);
//        standardScheduleMenu.setName("检定进度查询");
//        standardScheduleMenu.setDescription("技术机构管理检定进度查询");
//        webAppMenus.add(standardScheduleMenu);

//        WebAppMenu standardTechnologyMenu = new WebAppMenu();
//        standardTechnologyMenu.setRouteName("Technology");
//        standardTechnologyMenu.setParentRouteWebAppMenu(standardMenu);
//        standardTechnologyMenu.setParentWebAppMenu(standardMenu);
//        standardTechnologyMenu.setName("授权检定机构查询");
//        standardTechnologyMenu.setWeight(weight++);
//        standardTechnologyMenu.setDescription("技术机构管理计量器具授权检定机构查询");
//        webAppMenus.add(standardTechnologyMenu);
    }

    // 计量器具产品
    protected void addWebAppMenuMeasuringDevice() {

        WebAppMenu measuringdeviceMenu = new WebAppMenu();
        measuringdeviceMenu.setName("计量器具产品管理");
        measuringdeviceMenu.setAbstract(true);
        measuringdeviceMenu.setRouteName("measuringdevice");
        measuringdeviceMenu.setWeight(weight++);
        webAppMenus.add(measuringdeviceMenu);

        WebAppMenu measuringdeviceIntegratedMenu = new WebAppMenu();
        measuringdeviceIntegratedMenu.setRouteName("Integrated2");
        measuringdeviceIntegratedMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceIntegratedMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceIntegratedMenu.setName("计量器具产品档案");
        measuringdeviceIntegratedMenu.setDescription("计量器具产品管理计量器具产品档案");
        webAppMenus.add(measuringdeviceIntegratedMenu);

        WebAppMenu measuringdeviceApplianceArchivesMenu = new WebAppMenu();
        measuringdeviceApplianceArchivesMenu.setRouteName("ApplianceArchives");
        measuringdeviceApplianceArchivesMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesMenu.setName("计量器具生产企业档案");
        measuringdeviceApplianceArchivesMenu.setDescription("计量器具产品管理计量器具生产企业档案");
        webAppMenus.add(measuringdeviceApplianceArchivesMenu);

        WebAppMenu measuringdeviceApplianceArchivesAddMenu = new WebAppMenu();
        measuringdeviceApplianceArchivesAddMenu.setRouteName("ApplianceArchives/add");
        measuringdeviceApplianceArchivesAddMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesAddMenu.setParentWebAppMenu(measuringdeviceApplianceArchivesMenu);
        measuringdeviceApplianceArchivesAddMenu.setName("计量器具生产企业档案--新增");
        measuringdeviceApplianceArchivesAddMenu.setDescription("计量器具产品管理计量器具生产企业档案--新增");
        measuringdeviceApplianceArchivesAddMenu.setShow(false);
        webAppMenus.add(measuringdeviceApplianceArchivesAddMenu);

        WebAppMenu measuringdeviceEnterpriseFileMenu = new WebAppMenu();
        measuringdeviceEnterpriseFileMenu.setRouteName("EnterpriseFile");
        measuringdeviceEnterpriseFileMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileMenu.setName("获证产品目录");
        measuringdeviceEnterpriseFileMenu.setDescription("获证产品目录");
        webAppMenus.add(measuringdeviceEnterpriseFileMenu);

        WebAppMenu measuringdeviceEnterpriseFileAddMenu = new WebAppMenu();
        measuringdeviceEnterpriseFileAddMenu.setRouteName("EnterpriseFile/add");
        measuringdeviceEnterpriseFileAddMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileAddMenu.setParentWebAppMenu(measuringdeviceEnterpriseFileMenu);
        measuringdeviceEnterpriseFileAddMenu.setName("获证产品目录--新增");
        measuringdeviceEnterpriseFileAddMenu.setDescription("获证产品目录--新增");
        measuringdeviceEnterpriseFileAddMenu.setShow(false);
        webAppMenus.add(measuringdeviceEnterpriseFileAddMenu);
    }

    // 重点用能企业
    protected void addWebAppMenuEnergyEnterprise() {
        WebAppMenu energyEnterpriseMenu = new WebAppMenu();
        energyEnterpriseMenu.setName("重点用能企业计量管理");
        energyEnterpriseMenu.setAbstract(true);
        energyEnterpriseMenu.setWeight(weight++);
        energyEnterpriseMenu.setRouteName("energyenterprise");
        webAppMenus.add(energyEnterpriseMenu);

        WebAppMenu energyEnterpriseFileMenu = new WebAppMenu();
        energyEnterpriseFileMenu.setRouteName("File");
        energyEnterpriseFileMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFileMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFileMenu.setName("能源计量器具档案");
        energyEnterpriseFileMenu.setDescription("重点用能企业计量管理能源计量器具档案");
        webAppMenus.add(energyEnterpriseFileMenu);

        WebAppMenu energyEnterpriseIntegratedMenu = new WebAppMenu();
        energyEnterpriseIntegratedMenu.setRouteName("Integrated");
        energyEnterpriseIntegratedMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseIntegratedMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseIntegratedMenu.setName("能源计量器具一览表");
        energyEnterpriseIntegratedMenu.setDescription("重点用能企业计量管理能源计量器具一览表");
        webAppMenus.add(energyEnterpriseIntegratedMenu);

        WebAppMenu energyEnterpriseMeasuringdeviceMenu = new WebAppMenu();
        energyEnterpriseMeasuringdeviceMenu.setRouteName("Measuringdevice");
        energyEnterpriseMeasuringdeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMeasuringdeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMeasuringdeviceMenu.setName("进出用能单位能源计量器具一览表");
        energyEnterpriseMeasuringdeviceMenu.setDescription("重点用能企业计量管理进出用能单位能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMeasuringdeviceMenu);

        WebAppMenu energyEnterpriseSecondarydeviceMenu = new WebAppMenu();
        energyEnterpriseSecondarydeviceMenu.setRouteName("Secondarydevice");
        energyEnterpriseSecondarydeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSecondarydeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSecondarydeviceMenu.setName("进出主要次级用能单位能源计量器具一览表");
        energyEnterpriseSecondarydeviceMenu.setDescription("重点用能企业计量管理进出主要次级用能单位能源计量器具一览表");
        webAppMenus.add(energyEnterpriseSecondarydeviceMenu);

        WebAppMenu energyEnterpriseMaindeviceMenu = new WebAppMenu();
        energyEnterpriseMaindeviceMenu.setRouteName("Maindevice");
        energyEnterpriseMaindeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMaindeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMaindeviceMenu.setName("主要用能设备能源计量器具一览表");
        energyEnterpriseMaindeviceMenu.setDescription("重点用能企业计量管理主要用能设备能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMaindeviceMenu);

        WebAppMenu energyEnterpriseOtherdeviceMenu = new WebAppMenu();
        energyEnterpriseOtherdeviceMenu.setRouteName("Otherdevice");
        energyEnterpriseOtherdeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseOtherdeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseOtherdeviceMenu.setTemplateUrl("views/energyenterprise/otherdevice/index.html");
        energyEnterpriseOtherdeviceMenu.setName("主要用能设备能源计量器具一览表");
        energyEnterpriseOtherdeviceMenu.setDescription("重点用能企业计量管理其他能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMaindeviceMenu);

        WebAppMenu energyEnterpriseSummaryMenu = new WebAppMenu();
        energyEnterpriseSummaryMenu.setRouteName("Summary");
        energyEnterpriseSummaryMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSummaryMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSummaryMenu.setName("能源计量器具配备情况汇总表");
        energyEnterpriseSummaryMenu.setDescription("重点用能企业计量管理能源计量器具配备情况汇总表");
        webAppMenus.add(energyEnterpriseSummaryMenu);

        WebAppMenu energyEnterpriseStatisticalsummaryMenu = new WebAppMenu();
        energyEnterpriseStatisticalsummaryMenu.setRouteName("Statisticalsummary");
        energyEnterpriseStatisticalsummaryMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticalsummaryMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticalsummaryMenu.setName("能源计量器具配备情况统计汇总表");
        energyEnterpriseStatisticalsummaryMenu.setDescription("重点用能企业计量管理能源计量器具配备情况统计汇总表");
        webAppMenus.add(energyEnterpriseStatisticalsummaryMenu);

        WebAppMenu energyEnterpriseAccuracyMenu = new WebAppMenu();
        energyEnterpriseAccuracyMenu.setRouteName("Accuracy");
        energyEnterpriseAccuracyMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseAccuracyMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseAccuracyMenu.setName("能源计量器具准确度等级统计汇总表");
        energyEnterpriseAccuracyMenu.setDescription("重点用能企业计量管理能源计量器具准确度等级统计汇总表");
        webAppMenus.add(energyEnterpriseAccuracyMenu);

        WebAppMenu energyEnterpriseFlowgraphMenu = new WebAppMenu();
        energyEnterpriseFlowgraphMenu.setRouteName("Flowgraph");
        energyEnterpriseFlowgraphMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFlowgraphMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFlowgraphMenu.setName("重点用能单位能源流向图");
        energyEnterpriseFlowgraphMenu.setDescription("重点用能企业计量管理重点用能单位能源流向图");
        webAppMenus.add(energyEnterpriseFlowgraphMenu);

        WebAppMenu energyEnterpriseMapMenu = new WebAppMenu();
        energyEnterpriseMapMenu.setRouteName("Map");
        energyEnterpriseMapMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMapMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMapMenu.setName("能源采集点网络图");
        energyEnterpriseMapMenu.setDescription("重点用能企业计量管理能源采集点网络图");
        webAppMenus.add(energyEnterpriseMapMenu);

        WebAppMenu energyEnterpriseEquipmentMenu = new WebAppMenu();
        energyEnterpriseEquipmentMenu.setRouteName("Equipment");
        energyEnterpriseEquipmentMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEquipmentMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEquipmentMenu.setName("主要用能设备一览表");
        energyEnterpriseEquipmentMenu.setDescription("重点用能企业计量管理主要用能设备一览表");
        webAppMenus.add(energyEnterpriseEquipmentMenu);

        WebAppMenu energyEnterpriseTrainingMenu = new WebAppMenu();
        energyEnterpriseTrainingMenu.setRouteName("Training");
        energyEnterpriseTrainingMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseTrainingMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseTrainingMenu.setTemplateUrl("views/energyenterprise/training/index.html");
        energyEnterpriseTrainingMenu.setName("能源计量人员培训管理");
        energyEnterpriseTrainingMenu.setDescription("重点用能企业计量管理能源计量人员培训管理");
        webAppMenus.add(energyEnterpriseTrainingMenu);

        WebAppMenu energyEnterpriseStatisticsMenu = new WebAppMenu();
        energyEnterpriseStatisticsMenu.setRouteName("Statistics");
        energyEnterpriseStatisticsMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticsMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticsMenu.setName("重点用能单位能源购进、消费、库存统计表");
        energyEnterpriseStatisticsMenu.setDescription("重点用能企业计量管理重点用能单位能源购进、消费、库存统计表");
        webAppMenus.add(energyEnterpriseStatisticsMenu);

        WebAppMenu energyEnterpriseEnergyStatisticsMenu = new WebAppMenu();
        energyEnterpriseEnergyStatisticsMenu.setRouteName("Energystatistics");
        energyEnterpriseEnergyStatisticsMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEnergyStatisticsMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEnergyStatisticsMenu.setName("能源消费动态统计");
        energyEnterpriseEnergyStatisticsMenu.setDescription("重点用能企业计量管理能源消费动态统计");
        webAppMenus.add(energyEnterpriseEnergyStatisticsMenu);
    }

    // 监督抽查
    protected void addWebAppMenuSupervise() {
        WebAppMenu superviseMenu = new WebAppMenu();
        superviseMenu.setName("监督抽查");
        superviseMenu.setAbstract(true);
        superviseMenu.setRouteName("supervise");
        superviseMenu.setWeight(weight++);
        webAppMenus.add(superviseMenu);

        WebAppMenu superviseOrganizationMenu = new WebAppMenu();
        superviseOrganizationMenu.setRouteName("Organization");
        superviseOrganizationMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseOrganizationMenu.setParentWebAppMenu(superviseMenu);
        superviseOrganizationMenu.setName("授权检定机构监督抽查");
        superviseOrganizationMenu.setDescription("监督抽查授权检定机构监督抽查");
        webAppMenus.add(superviseOrganizationMenu);

        WebAppMenu superviseStandardMenu = new WebAppMenu();
        superviseStandardMenu.setRouteName("Standard");
        superviseStandardMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseStandardMenu.setParentWebAppMenu(superviseMenu);
        superviseStandardMenu.setName("计量标准监督抽查");
        superviseStandardMenu.setDescription("监督抽查计量标准监督抽查");
        webAppMenus.add(superviseStandardMenu);

        WebAppMenu superviseManufacturingMenu = new WebAppMenu();
        superviseManufacturingMenu.setRouteName("Manufacturing");
        superviseManufacturingMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseManufacturingMenu.setParentWebAppMenu(superviseMenu);
        superviseManufacturingMenu.setName("计量器具制造企业监督抽查");
        superviseManufacturingMenu.setDescription("监督抽查计量器具制造企业监督抽查");
        webAppMenus.add(superviseManufacturingMenu);

        WebAppMenu superviseSuttleMenu = new WebAppMenu();
        superviseSuttleMenu.setRouteName("Suttle");
        superviseSuttleMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseSuttleMenu.setParentWebAppMenu(superviseMenu);
        superviseSuttleMenu.setName("定量包装商品净含量监督抽查");
        superviseSuttleMenu.setDescription("监督抽查定量包装商品净含量监督抽查");
        webAppMenus.add(superviseSuttleMenu);

        WebAppMenu superviseInstrumentMenu = new WebAppMenu();
        superviseInstrumentMenu.setRouteName("Instrument");
        superviseInstrumentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseInstrumentMenu.setParentWebAppMenu(superviseMenu);
        superviseInstrumentMenu.setTemplateUrl("views/supervise/instrument/index.html");
        superviseInstrumentMenu.setName("重点计量器具监督抽查");
        superviseInstrumentMenu.setDescription("监督抽查重点计量器具监督抽查");
        webAppMenus.add(superviseInstrumentMenu);

        WebAppMenu superviseEnterpriseInstrumentMenu = new WebAppMenu();
        superviseEnterpriseInstrumentMenu.setRouteName("Enterpriseinstrument");
        superviseEnterpriseInstrumentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseEnterpriseInstrumentMenu.setParentWebAppMenu(superviseMenu);
        superviseEnterpriseInstrumentMenu.setName("重点计量器具监督抽查");
        superviseEnterpriseInstrumentMenu.setDescription("监督抽查重点计量器具监督抽查");
        webAppMenus.add(superviseEnterpriseInstrumentMenu);

        WebAppMenu superviseDepartmentMenu = new WebAppMenu();
        superviseDepartmentMenu.setRouteName("Department");
        superviseDepartmentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseDepartmentMenu.setParentWebAppMenu(superviseMenu);
        superviseDepartmentMenu.setName("能源标识使用单位监督抽查");
        superviseDepartmentMenu.setDescription("监督抽查能源标识使用单位监督抽查");
        webAppMenus.add(superviseDepartmentMenu);

        WebAppMenu superviseMeasureMenu = new WebAppMenu();
        superviseMeasureMenu.setRouteName("Measure");
        superviseMeasureMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseMeasureMenu.setParentWebAppMenu(superviseMenu);
        superviseMeasureMenu.setName("法定计量单位监督抽查");
        superviseMeasureMenu.setDescription("监督抽查法定计量单位监督抽查");
        webAppMenus.add(superviseMeasureMenu);
    }

    // 行政许可
    protected void addWebAppMenuBusiness() {
        WebAppMenu businessMenu = new WebAppMenu();
        businessMenu.setName("行政许可");
        businessMenu.setAbstract(true);
        businessMenu.setRouteName("business");
        businessMenu.setWeight(weight++);
        webAppMenus.add(businessMenu);

        WebAppMenu businessMeasurementMenu = new WebAppMenu();
        businessMeasurementMenu.setRouteName("Measurement");
        businessMeasurementMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasurementMenu.setParentWebAppMenu(businessMenu);
        businessMeasurementMenu.setTemplateUrl("views/business/measurement/index.html");
        businessMeasurementMenu.setName("计量标准建标考核（复查）申请");
        businessMeasurementMenu.setDescription("计量标准建标考核（复查）申请");
        webAppMenus.add(businessMeasurementMenu);

        WebAppMenu businessMeasurementAddMenu = new WebAppMenu();
        businessMeasurementAddMenu.setRouteName("MeasurementAdd");
        businessMeasurementAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasurementAddMenu.setParentWebAppMenu(businessMeasurementMenu);
        businessMeasurementAddMenu.setTemplateUrl("views/business/measurement/add.html");
        businessMeasurementAddMenu.setName("计量标准建标考核（复查）申请--新增");
        businessMeasurementAddMenu.setDescription("计量标准建标考核（复查）申请--新增");
        businessMeasurementAddMenu.setShow(false);
        webAppMenus.add(businessMeasurementAddMenu);

        WebAppMenu businessTechnologyMenu = new WebAppMenu();
        businessTechnologyMenu.setRouteName("Technology");
        businessTechnologyMenu.setParentRouteWebAppMenu(businessMenu);
        businessTechnologyMenu.setParentWebAppMenu(businessMenu);
        businessTechnologyMenu.setTemplateUrl("views/business/technology/index.html");
        businessTechnologyMenu.setName("技术机构考核（复查）申请");
        businessTechnologyMenu.setDescription("技术机构考核（复查）申请");
        webAppMenus.add(businessTechnologyMenu);

        WebAppMenu businessTechnologyAddMenu = new WebAppMenu();
        businessTechnologyAddMenu.setRouteName("TechnologyAdd");
        businessTechnologyAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessTechnologyAddMenu.setParentWebAppMenu(businessTechnologyMenu);
        businessTechnologyAddMenu.setTemplateUrl("views/business/technology/add.html");
        businessTechnologyAddMenu.setName("技术机构考核（复查）申请--新增");
        businessTechnologyAddMenu.setDescription("技术机构考核（复查）申请--新增");
        businessTechnologyAddMenu.setShow(false);
        webAppMenus.add(businessTechnologyAddMenu);

        WebAppMenu businessMeasureToolMenu = new WebAppMenu();
        businessMeasureToolMenu.setRouteName("MeasureTool");
        businessMeasureToolMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasureToolMenu.setParentWebAppMenu(businessMenu);
        businessMeasureToolMenu.setTemplateUrl("views/business/measuretool/index.html");
        businessMeasureToolMenu.setName("计量器具生产许可证(年检)申请");
        businessMeasureToolMenu.setDescription("计量器具生产许可证(年检)申请");
        businessMeasureToolMenu.setShow(true);
        webAppMenus.add(businessMeasureToolMenu);


        WebAppMenu businessMeasureToolAddMenu = new WebAppMenu();
        businessMeasureToolAddMenu.setRouteName("MeasureToolAdd");
        businessMeasureToolAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasureToolAddMenu.setParentWebAppMenu(businessMeasureToolMenu);
        businessMeasureToolAddMenu.setTemplateUrl("views/business/measuretool/add.html");
        businessMeasureToolAddMenu.setName("计量器具生产许可证(年检)申请--新增");
        businessMeasureToolAddMenu.setDescription("计量器具生产许可证(年检)申请--新增");
        businessMeasureToolAddMenu.setShow(false);
        webAppMenus.add(businessMeasureToolAddMenu);
    }

    // 人员资质
    protected void addWebAppMenusPersonnel() {
        WebAppMenu personnelMenu = new WebAppMenu();
        personnelMenu.setRouteName("personnel");
        personnelMenu.setWeight(weight++);
        personnelMenu.setName("人员资质");
        personnelMenu.setAbstract(true);
        webAppMenus.add(personnelMenu);

        WebAppMenu personnelQualificationManageMenu = new WebAppMenu();
        personnelQualificationManageMenu.setRouteName("personnelQualificationManage");
        personnelQualificationManageMenu.setParentRouteWebAppMenu(personnelMenu);
        personnelQualificationManageMenu.setParentWebAppMenu(personnelMenu);
        personnelQualificationManageMenu.setName("综合查询");
        personnelQualificationManageMenu.setWeight(weight++);
        personnelQualificationManageMenu.setDescription("人员资质管理综合查询");
        webAppMenus.add(personnelQualificationManageMenu);

        WebAppMenu personnelQualificationMenu = new WebAppMenu();
        personnelQualificationMenu.setRouteName("PersonnelFile");
        personnelQualificationMenu.setParentRouteWebAppMenu(personnelMenu);
        personnelQualificationMenu.setParentWebAppMenu(personnelMenu);
        personnelQualificationMenu.setName("档案管理");
        personnelQualificationMenu.setWeight(weight++);
        personnelQualificationMenu.setDescription("人员资质管理档案管理");
        webAppMenus.add(personnelQualificationMenu);

//        WebAppMenu personnelPersonnelFileMenu = new WebAppMenu();
//        personnelPersonnelFileMenu.setRouteName("PersonnelFile");
//        personnelPersonnelFileMenu.setParentRouteWebAppMenu(personnelMenu);
//        personnelPersonnelFileMenu.setParentWebAppMenu(personnelMenu);
//        personnelPersonnelFileMenu.setName("法定计量单位监督抽查");
//        personnelPersonnelFileMenu.setDescription("监督抽查法定计量单位监督抽查");
//        webAppMenus.add(personnelPersonnelFileMenu);

    }

    // 个人中心
    protected void addWebAppMenuPersonalCenter() {
        WebAppMenu personalCenterMenu = new WebAppMenu();
        personalCenterMenu.setName("个人中心");
        personalCenterMenu.setWeight(weight++);
        personalCenterMenu.setDescription("个人信息管理");
        personalCenterMenu.setRouteName("PersonalInfoManage");
        personalCenterMenu.setParentRouteWebAppMenu(mainMenu);
        webAppMenus.add(personalCenterMenu);
    }

    // 注册信息
    protected void addWebAppMenuRegister() {

        WebAppMenu registerMenu = new WebAppMenu();
        registerMenu.setName("注册信息");
        registerMenu.setAbstract(true);
        registerMenu.setWeight(weight++);
        registerMenu.setRouteName("register");
        webAppMenus.add(registerMenu);

        WebAppMenu registerMeasureDeviceMenu = new WebAppMenu();
        registerMeasureDeviceMenu.setRouteName("MeasureDevice");
        registerMeasureDeviceMenu.setParentRouteWebAppMenu(registerMenu);
        registerMeasureDeviceMenu.setParentWebAppMenu(registerMenu);
        registerMeasureDeviceMenu.setName("强制检定计量器具用户注册信息");
        registerMeasureDeviceMenu.setDescription("注册信息强制检定计量器具用户注册信息");
        webAppMenus.add(registerMeasureDeviceMenu);

        WebAppMenu registerMeasureDeviceAddMenu = new WebAppMenu();
        registerMeasureDeviceAddMenu.setRouteName("MeasureDeviceAdd");
        registerMeasureDeviceAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerMeasureDeviceAddMenu.setParentWebAppMenu(registerMenu);
        registerMeasureDeviceAddMenu.setName("强制检定计量器具用户注册信息--新增");
        registerMeasureDeviceAddMenu.setDescription("注册信息强制检定计量器具用户注册信息--新增");
        webAppMenus.add(registerMeasureDeviceAddMenu);

        WebAppMenu registerTechnologyMenu = new WebAppMenu();
        registerTechnologyMenu.setRouteName("Technology");
        registerTechnologyMenu.setParentRouteWebAppMenu(registerMenu);
        registerTechnologyMenu.setParentWebAppMenu(registerMenu);
        registerTechnologyMenu.setName("技术机构注册信息");
        registerTechnologyMenu.setDescription("注册信息技术机构注册信息");
        webAppMenus.add(registerTechnologyMenu);

        WebAppMenu registerTechnologyAddMenu = new WebAppMenu();
        registerTechnologyAddMenu.setRouteName("TechnologyAdd");
        registerTechnologyAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerTechnologyAddMenu.setParentWebAppMenu(registerMenu);
        registerTechnologyAddMenu.setName("技术机构注册信息--新增");
        registerTechnologyAddMenu.setDescription("注册信息技术机构注册信息--新增");
        registerMeasureDeviceMenu.setShow(false);
        webAppMenus.add(registerTechnologyAddMenu);

        WebAppMenu registerEnterpriseMenu = new WebAppMenu();
        registerEnterpriseMenu.setRouteName("Enterprise");
        registerEnterpriseMenu.setParentRouteWebAppMenu(registerMenu);
        registerEnterpriseMenu.setParentWebAppMenu(registerMenu);
        registerEnterpriseMenu.setName("定量包装商品生产、销售企业注册信息");
        registerEnterpriseMenu.setDescription("注册信息定量包装商品生产、销售企业注册信息");
        registerEnterpriseMenu.setShow(false);
        webAppMenus.add(registerEnterpriseMenu);

        WebAppMenu registerEnterpriseAddMenu = new WebAppMenu();
        registerEnterpriseAddMenu.setRouteName("EnterpriseAdd");
        registerEnterpriseAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerEnterpriseAddMenu.setParentWebAppMenu(registerMenu);
        registerEnterpriseAddMenu.setName("定量包装商品生产、销售企业注册信息--新增");
        registerEnterpriseAddMenu.setDescription("注册信息定量包装商品生产、销售企业注册信息--新增");
        registerEnterpriseAddMenu.setShow(false);
        webAppMenus.add(registerEnterpriseAddMenu);

        WebAppMenu registerSocietyMenu = new WebAppMenu();
        registerSocietyMenu.setRouteName("Society");
        registerSocietyMenu.setParentRouteWebAppMenu(registerMenu);
        registerSocietyMenu.setParentWebAppMenu(registerMenu);
        registerSocietyMenu.setName("社会公众注册信息");
        registerSocietyMenu.setDescription("注册信息社会公众注册信息");
        webAppMenus.add(registerSocietyMenu);
    }

    // 我的工作
    protected void addWebAppMenuMyWork() {
        WebAppMenu myWorkMenu = new WebAppMenu();
        myWorkMenu.setName("我的工作");
        myWorkMenu.setAbstract(true);
        myWorkMenu.setRouteName("mywork");
        myWorkMenu.setTemplateUrl("views/common/content.html");
        webAppMenus.add(myWorkMenu);

        WebAppMenu myWorkUnhandleMenu = new WebAppMenu();
        myWorkUnhandleMenu.setRouteName("Unhandle");
        myWorkUnhandleMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkUnhandleMenu.setParentWebAppMenu(myWorkMenu);
        myWorkUnhandleMenu.setTemplateUrl("views/mywork/unhandle/index.html");
        myWorkUnhandleMenu.setName("未办");
        myWorkUnhandleMenu.setDescription("我的工作-未办");
        webAppMenus.add(myWorkUnhandleMenu);

        WebAppMenu myWorkHoldOnMenu = new WebAppMenu();
        myWorkHoldOnMenu.setRouteName("HoldOn");
        myWorkHoldOnMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkHoldOnMenu.setParentWebAppMenu(myWorkMenu);
        myWorkHoldOnMenu.setTemplateUrl("views/mywork/holdon/index.html");
        myWorkHoldOnMenu.setName("搁置");
        myWorkHoldOnMenu.setDescription("我的工作-搁置");
        webAppMenus.add(myWorkHoldOnMenu);

        WebAppMenu myWorkHandlingMenu = new WebAppMenu();
        myWorkHandlingMenu.setRouteName("Handling");
        myWorkHandlingMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkHandlingMenu.setParentWebAppMenu(myWorkMenu);
        myWorkHandlingMenu.setTemplateUrl("views/mywork/handling/index.html");
        myWorkHandlingMenu.setName("在办");
        myWorkHandlingMenu.setDescription("我的工作-在办");
        webAppMenus.add(myWorkHandlingMenu);

        WebAppMenu myWorkHandledMenu = new WebAppMenu();
        myWorkHandledMenu.setRouteName("Handled");
        myWorkHandledMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkHandledMenu.setParentWebAppMenu(myWorkMenu);
        myWorkHandledMenu.setTemplateUrl("views/mywork/handled/index.html");
        myWorkHandledMenu.setName("已办");
        myWorkHandledMenu.setDescription("我的工作-已办");
        webAppMenus.add(myWorkHandledMenu);
    }

    // 其它功能
    protected void addWebAppMenuOther() {
        WebAppMenu othersMenu = new WebAppMenu();
        othersMenu.setName("其他功能");
        othersMenu.setAbstract(true);
        othersMenu.setRouteName("others");
        othersMenu.setWeight(weight++);
        webAppMenus.add(othersMenu);

        WebAppMenu othersIntegrityListMenu = new WebAppMenu();
        othersIntegrityListMenu.setRouteName("Integritylist");
        othersIntegrityListMenu.setParentRouteWebAppMenu(othersMenu);
        othersIntegrityListMenu.setParentWebAppMenu(othersMenu);
        othersIntegrityListMenu.setTemplateUrl("views/others/integritylist/index.html");
        othersIntegrityListMenu.setName("诚信体系红黑榜");
        othersIntegrityListMenu.setDescription("其他功能诚信体系红黑榜");
        webAppMenus.add(othersIntegrityListMenu);

        WebAppMenu othersPlatformMenu = new WebAppMenu();
        othersPlatformMenu.setRouteName("Integritylist");
        othersPlatformMenu.setParentRouteWebAppMenu(othersMenu);
        othersPlatformMenu.setParentWebAppMenu(othersMenu);
        othersPlatformMenu.setTemplateUrl("views/others/integritylist/index.html");
        othersPlatformMenu.setName("技术监督质量信息平台链接");
        othersIntegrityListMenu.setDescription("其他功能技术监督质量信息平台链接");
        webAppMenus.add(othersIntegrityListMenu);
    }

    // 计量科技园地
    protected void addWebAppMenuTechnology() {
        WebAppMenu technologyMenu = new WebAppMenu();
        technologyMenu.setName("计量科技园地");
        technologyMenu.setWeight(weight++);
        technologyMenu.setAbstract(true);
        technologyMenu.setRouteName("technology");
        webAppMenus.add(technologyMenu);

        WebAppMenu technologyDynamicMenu = new WebAppMenu();
        technologyDynamicMenu.setName("计量科技园地");
        technologyDynamicMenu.setDescription("新闻动态");
        technologyDynamicMenu.setRouteName("Dynamic");
        technologyDynamicMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyDynamicMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyDynamicMenu);

        WebAppMenu technologyNewRegulationsMenu = new WebAppMenu();
        technologyNewRegulationsMenu.setName("新法规宣讲");
        technologyNewRegulationsMenu.setDescription("新法规宣讲");
        technologyNewRegulationsMenu.setRouteName("Newregulations");
        technologyNewRegulationsMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyNewRegulationsMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyNewRegulationsMenu);

        WebAppMenu technologyLawMenu = new WebAppMenu();
        technologyLawMenu.setName("法律法规");
        technologyLawMenu.setDescription("法律法规");
        technologyLawMenu.setRouteName("Law");
        technologyLawMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyLawMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyLawMenu);

        WebAppMenu technologyInformationMenu = new WebAppMenu();
        technologyInformationMenu.setName("新闻资讯");
        technologyInformationMenu.setDescription("新闻资讯");
        technologyInformationMenu.setRouteName("Information");
        technologyInformationMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyInformationMenu.setParentWebAppMenu(technologyMenu);
        technologyInformationMenu.setTemplateUrl("views/technology/information/index.html");
        webAppMenus.add(technologyInformationMenu);
    }

    // 企业行业最高计量标准管理
    protected void addWebAppMenuHighestStandard() {
        WebAppMenu higheststandardMenu = new WebAppMenu();
        higheststandardMenu.setAbstract(true);
        higheststandardMenu.setWeight(weight++);
        higheststandardMenu.setRouteName("higheststandard");
        higheststandardMenu.setTemplateUrl("views/common/content.html");
        higheststandardMenu.setName("企业行业最高计量标准管理");
        webAppMenus.add(higheststandardMenu);

        WebAppMenu higheststandardFileMenu = new WebAppMenu();
        higheststandardFileMenu.setRouteName("Reply");
        higheststandardFileMenu.setParentRouteWebAppMenu(higheststandardMenu);
        higheststandardFileMenu.setParentWebAppMenu(higheststandardMenu);
        higheststandardFileMenu.setTemplateUrl("views/higheststandard/file/index.html");
        higheststandardFileMenu.setName("企业行业最高计量标准档案");
        higheststandardFileMenu.setDescription("企业行业最高计量标准管理企业行业最高计量标准档案");
        webAppMenus.add(higheststandardFileMenu);
    }

    // 系统设置
    protected void addWebAppMenuSystem() {
        WebAppMenu systemConfigMenu = new WebAppMenu();
        systemConfigMenu.setName("系统设置");
        systemConfigMenu.setAbstract(true);
        systemConfigMenu.setRouteName("system");
        systemConfigMenu.setWeight(weight++);
        webAppMenus.add(systemConfigMenu);

        WebAppMenu userMenu = new WebAppMenu();
        userMenu.setName("用户管理");
        userMenu.setRouteName("Userfile");
        userMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userMenu.setParentWebAppMenu(systemConfigMenu);
        userMenu.setWeight(weight++);
        webAppMenus.add(userMenu);

        WebAppMenu userAddMenu = new WebAppMenu();
        userAddMenu.setName("用户管理 -- 增加");
        userAddMenu.setRouteName("UserfileAdd");
        userAddMenu.setShow(false);
        userAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userAddMenu.setParentWebAppMenu(userMenu);
        webAppMenus.add(userAddMenu);

        WebAppMenu userEditMenu = new WebAppMenu();
        userEditMenu.setName("用户管理 -- 编辑");
        userEditMenu.setRouteName("UserfileEdit");
        userAddMenu.setShow(false);
        userAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userAddMenu.setParentWebAppMenu(userMenu);
        webAppMenus.add(userAddMenu);

        WebAppMenu userDetailMenu = new WebAppMenu();
        userDetailMenu.setName("用户管理 -- 查看详情");
        userDetailMenu.setRouteName("UserfileDetail");
        userDetailMenu.setShow(false);
        userDetailMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userDetailMenu.setParentWebAppMenu(systemConfigMenu);
        webAppMenus.add(userDetailMenu);

        WebAppMenu roleMenu = new WebAppMenu();
        roleMenu.setName("角色管理");
        roleMenu.setRouteName("role");
        roleMenu.setWeight(weight++);
        roleMenu.setParentWebAppMenu(systemConfigMenu);
        roleMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(roleMenu);

        WebAppMenu roleAddMenu = new WebAppMenu();
        roleAddMenu.setName("角色管理 -- 新增");
        roleAddMenu.setRouteName("RolefileAdd");
        roleAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleAddMenu.setParentWebAppMenu(roleMenu);
        roleAddMenu.setController("RoleRolefileAddCtrl");
        roleAddMenu.setShow(false);
        webAppMenus.add(roleAddMenu);

        WebAppMenu roleEditMenu = new WebAppMenu();
        roleEditMenu.setName("角色管理 -- 编辑");
        roleEditMenu.setRouteName("RolefileEdit");
        roleEditMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleEditMenu.setParentWebAppMenu(roleMenu);
        roleEditMenu.setShow(false);
        webAppMenus.add(roleEditMenu);

        WebAppMenu roleDeleteMenu = new WebAppMenu();
        roleDeleteMenu.setName("角色管理 -- 删除");
        roleDeleteMenu.setRouteName("RolefileDelete");
        roleDeleteMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleDeleteMenu.setParentWebAppMenu(roleMenu);
        roleDeleteMenu.setShow(false);
        webAppMenus.add(roleDeleteMenu);

        WebAppMenu roleDetailMenu = new WebAppMenu();
        roleDetailMenu.setName("角色管理 -- 详情");
        roleDetailMenu.setRouteName("RolefileDetail");
        roleDetailMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleDetailMenu.setParentWebAppMenu(roleMenu);
        roleDetailMenu.setShow(false);
        webAppMenus.add(roleDetailMenu);

        WebAppMenu systemMenuMenu = new WebAppMenu();
        systemMenuMenu.setName("菜单管理");
        systemMenuMenu.setDescription("计量系统菜单管理");
        systemMenuMenu.setRouteName("Menu");
        systemMenuMenu.setWeight(weight++);
        systemMenuMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemMenuMenu.setParentWebAppMenu(systemConfigMenu);
        webAppMenus.add(systemMenuMenu);

        WebAppMenu systemMenuAddMenu = new WebAppMenu();
        systemMenuAddMenu.setName("菜单管理-新增菜单");
        systemMenuAddMenu.setDescription("计量系统菜单管理");
        systemMenuAddMenu.setRouteName("MenuAdd");
        systemMenuAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemMenuAddMenu.setParentWebAppMenu(systemMenuMenu);
        systemMenuAddMenu.setShow(false);
        webAppMenus.add(systemMenuAddMenu);

        logger.info("增加强检器具类别路由");
        WebAppMenu instrumentTypeMenu = new WebAppMenu();
        instrumentTypeMenu.setName("强检器具类别管理");
        instrumentTypeMenu.setDescription("强检器具类别管理，每种器具都应该是器具类别的一种");
        instrumentTypeMenu.setRouteName("instrumentType");
        instrumentTypeMenu.setWeight(weight++);
        instrumentTypeMenu.setParentWebAppMenu(systemConfigMenu);
        instrumentTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(instrumentTypeMenu);

        WebAppMenu instrumentTypeAddMenu = new WebAppMenu();
        instrumentTypeAddMenu.setName("强检器具类别管理 -- 新增");
        instrumentTypeAddMenu.setRouteName("instrumentTypeAdd");
        instrumentTypeAddMenu.setShow(false);
        instrumentTypeAddMenu.setWeight(weight++);
        instrumentTypeAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        instrumentTypeAddMenu.setParentWebAppMenu(instrumentTypeMenu);
        webAppMenus.add(instrumentTypeAddMenu);

        WebAppMenu instrumentTypeEditMenu = new WebAppMenu();
        instrumentTypeEditMenu.setName("强检器具类别管理 -- 编辑");
        instrumentTypeEditMenu.setRouteName("instrumentTypeEdit");
        instrumentTypeEditMenu.setShow(false);
        instrumentTypeEditMenu.setParentRouteWebAppMenu(systemConfigMenu);
        instrumentTypeEditMenu.setParentWebAppMenu(instrumentTypeMenu);
        webAppMenus.add(instrumentTypeEditMenu);

        logger.info("增加标准器具类别路由");
        WebAppMenu standardInstrumentTypeMenu = new WebAppMenu();
        standardInstrumentTypeMenu.setName("标准器类别管理");
        standardInstrumentTypeMenu.setDescription("标准器类别管理，每种器具都应该是器具类别的一种");
        standardInstrumentTypeMenu.setRouteName("standardInstrumentType");
        standardInstrumentTypeMenu.setWeight(weight++);
        standardInstrumentTypeMenu.setParentWebAppMenu(systemConfigMenu);
        standardInstrumentTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(standardInstrumentTypeMenu);

        WebAppMenu systemAuthorityMenu = new WebAppMenu();
        systemAuthorityMenu.setName("系统设置-权限设置");
        systemAuthorityMenu.setDescription("计量系统权限设置");
        systemAuthorityMenu.setRouteName("Authority");
        systemAuthorityMenu.setWeight(weight++);
        systemAuthorityMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemAuthorityMenu.setParentWebAppMenu(systemMenuMenu);
        webAppMenus.add(systemAuthorityMenu);

//        WebAppMenu qualifierCertificateTypeMenu = new WebAppMenu();
//        qualifierCertificateTypeMenu.setName("资格证类别管理");
//        qualifierCertificateTypeMenu.setDescription("每个资格证类别对应检定的学科类别");
//        qualifierCertificateTypeMenu.setRouteName("qualifierCertificateType");
//        qualifierCertificateTypeMenu.setWeight(weight++);
//        qualifierCertificateTypeMenu.setParentWebAppMenu(systemConfigMenu);
//        qualifierCertificateTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        webAppMenus.add(qualifierCertificateTypeMenu);

//
//        WebAppMenu workflowtypeWorkFlowTypeManageMenu = new WebAppMenu();
//        workflowtypeWorkFlowTypeManageMenu.setRouteName("WorkFlowTypeManage");
//        workflowtypeWorkFlowTypeManageMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageMenu.setParentWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageMenu.setName("工作流管理");
//        workflowtypeWorkFlowTypeManageMenu.setDescription("计量系统工作流管理");
//        workflowtypeWorkFlowTypeManageMenu.setWeight(weight++);
//        webAppMenus.add(workflowtypeWorkFlowTypeManageMenu);

//        WebAppMenu workflowtypeWorkFlowTypeManageAddMenu = new WebAppMenu();
//        workflowtypeWorkFlowTypeManageAddMenu.setRouteName("WorkFlowTypeManageAdd");
//        workflowtypeWorkFlowTypeManageAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageAddMenu.setParentWebAppMenu(workflowtypeWorkFlowTypeManageMenu);
//        workflowtypeWorkFlowTypeManageAddMenu.setName("工作流管理-新增页面");
//        workflowtypeWorkFlowTypeManageAddMenu.setDescription("计量系统工作流管理");
//        workflowtypeWorkFlowTypeManageAddMenu.setShow(false);
//        webAppMenus.add(workflowtypeWorkFlowTypeManageAddMenu);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
