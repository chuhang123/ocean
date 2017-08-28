package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by panjie on 17/7/28.
 */
@Component
public class ApplyTypeServiceTestData {
    @Autowired
    WebAppMenuRepository webAppMenuRepository;  // 前台菜单
    @Autowired
    ApplyTypeRepository applyTypeRepository;    // 申请申请

    public void getOneByWebAppMenuId(ApplyType applyType, WebAppMenu webAppMenu) {

        webAppMenuRepository.save(webAppMenu);

        applyType.setWebAppMenu(webAppMenu);
        applyTypeRepository.save(applyType);
    }
}
