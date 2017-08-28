package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


/**
 * Created by zhangjiahao on 2017/7/27.
 */
@Component
public class MenuDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered{
    private static Logger logger = Logger.getLogger(MenuDataInit.class.getName());
    @Autowired private MenuRepository menuRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        List<Menu> menusAll = (List<Menu>) menuRepository.findAll();
        if (menusAll.size() == 0) {
            logger.info("添加后台菜单信息");
            Set<Menu> menus = new HashSet<Menu>();

            Menu certifiedProductGetAll = new Menu();
            certifiedProductGetAll.setName("器具产品 -- 获取所有产品");
            certifiedProductGetAll.setRequest("GET");
            certifiedProductGetAll.setUrl("/CertifiedProduct/getAll");
            menus.add(certifiedProductGetAll);

            Menu certifiedProductSave = new Menu();
            certifiedProductSave.setName("器具产品 -- 保存");
            certifiedProductSave.setRequest("GET");
            certifiedProductSave.setUrl("/CertifiedProduct/getAll");
            menus.add(certifiedProductSave);

            Menu districtGet = new Menu();
            districtGet.setName("区域 -- 获取一个区域");
            districtGet.setRequest("GET");
            districtGet.setUrl("/District/get/{id}");
            menus.add(districtGet);

            Menu districtGetAll = new Menu();
            districtGetAll.setName("区域 -- 获取所有区域");
            districtGetAll.setRequest("GET");
            districtGetAll.setUrl("/District/getAll");
            menus.add(districtGetAll);


            Menu districtSave = new Menu();
            districtSave.setName("区域 -- 保存区域");
            districtSave.setRequest("POST");
            districtSave.setUrl("/District/save");
            menus.add(districtSave);

            Menu districtTypeGetAll = new Menu();
            districtTypeGetAll.setName("区域类型 -- 获取所有区域类型");
            districtTypeGetAll.setRequest("GET");
            districtTypeGetAll.setUrl("/DistrictType/getAll");
            menus.add(districtTypeGetAll);

            Menu districtTypeSave = new Menu();
            districtTypeSave.setName("区域类型 -- 保存区域类型");
            districtTypeSave.setRequest("POST");
            districtTypeSave.setUrl("/DistrictType/save");
            menus.add(districtTypeSave);

            Menu mandatoryCheckDetailGetAllByMantoryIntegrated = new Menu();
            mandatoryCheckDetailGetAllByMantoryIntegrated.setName("强检器具检定信息 -- 获取该器具所有检定信息");
            mandatoryCheckDetailGetAllByMantoryIntegrated.setRequest("GET");
            mandatoryCheckDetailGetAllByMantoryIntegrated.setUrl("/MandatoryCheckDetail/getAllByMantoryIntegrated");
            menus.add(mandatoryCheckDetailGetAllByMantoryIntegrated);

            Menu mandatoryCheckDetailSave = new Menu();
            mandatoryCheckDetailSave.setName("强检器具检定信息 -- 保存");
            mandatoryCheckDetailSave.setRequest("POST");
            mandatoryCheckDetailSave.setUrl("/MandatoryCheckDetail/save");
            menus.add(mandatoryCheckDetailSave);

            Menu mandatoryIntegratedDelete = new Menu();
            mandatoryIntegratedDelete.setName("强检器具综合信息 -- 删除");
            mandatoryIntegratedDelete.setRequest("DELETE");
            mandatoryIntegratedDelete.setUrl("/MandatoryIntegrated/delete/{id}");
            menus.add(mandatoryIntegratedDelete);

            Menu mandatoryIntegratedGetAll = new Menu();
            mandatoryIntegratedGetAll.setName("强检器具综合信息 -- 获取所有强检器具");
            mandatoryIntegratedGetAll.setRequest("GET");
            mandatoryIntegratedGetAll.setUrl("/MandatoryIntegrated/getAll");
            menus.add(mandatoryIntegratedGetAll);

            Menu mandatoryIntegratedSave = new Menu();
            mandatoryIntegratedSave.setName("强检器具综合信息 -- 保存强检器具");
            mandatoryIntegratedSave.setRequest("POST");
            mandatoryIntegratedSave.setUrl("/MandatoryIntegrated/save");
            menus.add(mandatoryIntegratedSave);

            Menu mandatoryIntegratedUpdate = new Menu();
            mandatoryIntegratedUpdate.setName("强检器具综合信息 -- 更新强检器具");
            mandatoryIntegratedUpdate.setRequest("PUT");
            mandatoryIntegratedUpdate.setUrl("/MandatoryIntegrated/update/{id}");
            menus.add(mandatoryIntegratedUpdate);

            Menu measuringdeviceApplianceArchiveGetAll = new Menu();
            measuringdeviceApplianceArchiveGetAll.setName("器具产品 -- 获取所有器具产品");
            measuringdeviceApplianceArchiveGetAll.setRequest("GET");
            measuringdeviceApplianceArchiveGetAll.setUrl("/MeasuringdeviceApplianceArchive/getAll");
            menus.add(measuringdeviceApplianceArchiveGetAll);

            Menu measuringdeviceApplianceArchiveSave = new Menu();
            measuringdeviceApplianceArchiveSave.setName("器具产品 -- 保存器具产品");
            measuringdeviceApplianceArchiveSave.setRequest("POST");
            measuringdeviceApplianceArchiveSave.setUrl("/MeasuringdeviceApplianceArchive/save");
            menus.add(measuringdeviceApplianceArchiveSave);


            Menu optionalCheckDetailGetAll = new Menu();
            optionalCheckDetailGetAll.setName("非强检器具检定信息 -- 获取所有非强检器具检定信息");
            optionalCheckDetailGetAll.setRequest("GET");
            optionalCheckDetailGetAll.setUrl("/OptionalCheckDetail/getAll");
            menus.add(optionalCheckDetailGetAll);

            Menu optionalCheckDetailGetAllByOptionalIntergrated = new Menu();
            optionalCheckDetailGetAllByOptionalIntergrated.setName("非强检器具检定信息 -- 获取检定信息");
            optionalCheckDetailGetAllByOptionalIntergrated.setRequest("GET");
            optionalCheckDetailGetAllByOptionalIntergrated.setUrl("/OptionalCheckDetail/getAllByOptionalIntergrated");
            menus.add(optionalCheckDetailGetAllByOptionalIntergrated);

            Menu optionalCheckDetailSave = new Menu();
            optionalCheckDetailSave.setName("非强检器具检定信息 -- 保存非强检器具");
            optionalCheckDetailSave.setRequest("POST");
            optionalCheckDetailSave.setUrl("/OptionalCheckDetail/save");
            menus.add(optionalCheckDetailSave);

            Menu optionalIntergratedGetAll = new Menu();
            optionalIntergratedGetAll.setName("非强检器具 -- 获取非强检器具");
            optionalIntergratedGetAll.setRequest("GET");
            optionalIntergratedGetAll.setUrl("/OptionalIntergrated/getAll");
            menus.add(optionalIntergratedGetAll);

            Menu optionalIntergratedSave = new Menu();
            optionalIntergratedSave.setName("非强检器具 -- 保存非强检器具");
            optionalIntergratedSave.setRequest("GET");
            optionalIntergratedSave.setUrl("/OptionalIntergrated/save");
            menus.add(optionalIntergratedSave);

            Menu personalFileGetAll = new Menu();
            personalFileGetAll.setName("人员资质 -- 获取所有人员资质");
            personalFileGetAll.setRequest("GET");
            personalFileGetAll.setUrl("/PersonalFile/getAll");
            menus.add(personalFileGetAll);

            Menu personalFileSave = new Menu();
            personalFileSave.setName("人员资质 -- 保存人员资质");
            personalFileSave.setRequest("POST");
            personalFileSave.setUrl("/PersonalFile/save");
            menus.add(personalFileSave);

            Menu roleGet = new Menu();
            roleGet.setName("角色 -- 获取一个角色");
            roleGet.setRequest("GET");
            roleGet.setUrl("/Role/get/{id}");
            menus.add(roleGet);

            Menu roleGetAll = new Menu();
            roleGetAll.setName("角色 -- 获取所有角色");
            roleGetAll.setRequest("GET");
            roleGetAll.setUrl("/Role/getAll");
            menus.add(roleGetAll);

            Menu roleSave = new Menu();
            roleSave.setName("角色 -- 保存角色");
            roleSave.setRequest("POST");
            roleSave.setUrl("/Role/save");
            menus.add(roleSave);

            Menu standardAuthorizationgetAll = new Menu();
            standardAuthorizationgetAll.setName("标准装置-- 获取所有标准装置");
            standardAuthorizationgetAll.setRequest("GET");
            standardAuthorizationgetAll.setUrl("/StandardAuthorization/getAll");
            menus.add(standardAuthorizationgetAll);

            Menu standardAuthorizationSave = new Menu();
            standardAuthorizationSave.setName("标准装置-- 获取所有标准装置");
            standardAuthorizationSave.setRequest("POST");
            standardAuthorizationSave.setUrl("/StandardAuthorization/save");
            menus.add(standardAuthorizationSave);

            Menu standardDeviceGetAllByStandardFile = new Menu();
            standardDeviceGetAllByStandardFile.setName("标准器-- 获取标准装置的标准器");
            standardDeviceGetAllByStandardFile.setRequest("GET");
            standardDeviceGetAllByStandardFile.setUrl("/StandardDevice/getAllByStandardFile");
            menus.add(standardDeviceGetAllByStandardFile);

            Menu standardDeviceSave = new Menu();
            standardDeviceSave.setName("标准器-- 保存标准装置的标准器");
            standardDeviceSave.setRequest("POST");
            standardDeviceSave.setUrl("/StandardDevice/save");
            menus.add(standardDeviceSave);

            Menu standardDeviceCheckDetailGetAllByStandardDevice = new Menu();
            standardDeviceCheckDetailGetAllByStandardDevice.setName("标准器检定信息-- 获取标准器的检定信息");
            standardDeviceCheckDetailGetAllByStandardDevice.setRequest("GET");
            standardDeviceCheckDetailGetAllByStandardDevice.setUrl("/StandardDeviceCheckDetail/getAllByStandardDevice");
            menus.add(standardDeviceCheckDetailGetAllByStandardDevice);

            Menu standardDeviceCheckDetailSave = new Menu();
            standardDeviceCheckDetailSave.setName("标准器检定信息-- 保存标准器的检定信息");
            standardDeviceCheckDetailSave.setRequest("POST");
            standardDeviceCheckDetailSave.setUrl("/StandardDeviceCheckDetail/save");
            menus.add(standardDeviceCheckDetailSave);

            Menu standardFileGetAll = new Menu();
            standardFileGetAll.setName("标准装置-- 获取所有标准装置");
            standardFileGetAll.setRequest("GET");
            standardFileGetAll.setUrl("/StandardFile/getAll");
            menus.add(standardFileGetAll);

            Menu standardFileSave = new Menu();
            standardFileSave.setName("标准装置-- 保存标准装置");
            standardFileSave.setRequest("POST");
            standardFileSave.setUrl("/StandardFile/save");
            menus.add(standardFileSave);

            Menu userDelete = new Menu();
            userDelete.setName("用户-- 删除用户");
            userDelete.setRequest("DELETE");
            userDelete.setUrl("/User/delete");
            menus.add(userDelete);

            Menu userGet = new Menu();
            userGet.setName("用户-- 获取一个用户");
            userGet.setRequest("GET");
            userGet.setUrl("/User/get/{id}");
            menus.add(userGet);

            Menu userGetAll = new Menu();
            userGetAll.setName("用户-- 获取所有用户");
            userGetAll.setRequest("GET");
            userGetAll.setUrl("/User/getAll");
            menus.add(userGetAll);

            Menu userLogin = new Menu();
            userLogin.setName("用户-- 登录");
            userLogin.setRequest("GET");
            userLogin.setUrl("/User/login");
            menus.add(userLogin);

            Menu userLogout = new Menu();
            userLogout.setName("用户-- 登出");
            userLogout.setRequest("GET");
            userLogout.setUrl("/User/logout");
            menus.add(userLogout);

            Menu userSave = new Menu();
            userSave.setName("用户-- 保存用户");
            userSave.setRequest("POST");
            userSave.setUrl("/User/save");
            menus.add(userSave);

            Menu userUpdate = new Menu();
            userUpdate.setName("用户-- 更新用户");
            userUpdate.setRequest("PUT");
            userUpdate.setUrl("/User/update/{id}");
            menus.add(userUpdate);

            Menu webAppMenu = new Menu();
            webAppMenu.setName("前台菜单-- 获取所有前台菜单");
            webAppMenu.setRequest("GET");
            webAppMenu.setUrl("/WebAppMenu/");
            menus.add(webAppMenu);

            Menu applyDelete = new Menu();
            applyDelete.setName("前台菜单-- 删除前台菜单");
            applyDelete.setRequest("DELETE");
            applyDelete.setUrl("/Apply/delete/{id}");
            menus.add(applyDelete);

            Menu applyGet = new Menu();
            applyGet.setName("前台菜单-- 获取一个前台菜单");
            applyGet.setRequest("GET");
            applyGet.setUrl("/Apply/get/{id}");
            menus.add(applyGet);

            Menu applyGetAll = new Menu();
            applyGetAll.setName("前台菜单-- 获取所有前台菜单");
            applyGetAll.setRequest("GET");
            applyGetAll.setUrl("/Apply/getAll");
            menus.add(applyGetAll);

            Menu applySave = new Menu();
            applySave.setName("前台菜单-- 保存前台菜单");
            applySave.setRequest("POST");
            applySave.setUrl("/Apply/save");
            menus.add(applySave);

            menuRepository.save(menus);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
