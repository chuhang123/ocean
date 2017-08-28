package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.ApplyType;
import com.mengyunzhi.measurement.repository.WebAppMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ApplyTypeServiceTest {
    @Autowired ApplyTypeService applyTypeService;   // 申请类型
    @Autowired ApplyTypeServiceTestData applyTypeServiceTestData;
    @Test
    public void getOneByWebAppMenuId() {
        ApplyType applyType = applyTypeService.getOneApplyType();
        WebAppMenu webAppMenu = WebAppMenuService.getOneWebAppMenu();
        applyTypeServiceTestData.getOneByWebAppMenuId(applyType, webAppMenu);

        ApplyType applyType1 = applyTypeService.getOneByWebAppMenuId(webAppMenu.getId());
        assertThat(applyType1.getId()).isEqualTo(applyType.getId());
    }
}
