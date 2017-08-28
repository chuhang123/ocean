package com.mengyunzhi.measurement;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by panjie on 17/6/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiInitDataListenerTest {
    private Logger logger = Logger.getLogger(ApiInitDataListener.class.getName());

    // 数据库更新方式
    @Value("${spring.jpa.hibernate.ddl-auto}")
    protected String jpaDdlAuto;



    // 区域
    @Autowired
    private DistrictRepository districtRepository;

    //部门
    @Autowired
    private DepartmentRepository departmentRepository;


    //后台菜单
    @Autowired
    private MenuRepository menuRepository;

    // 精度显示名称
    @Autowired
    protected AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    // 岗位
    @Autowired
    protected PostRepository postRepository;

    // 用途
    @Autowired
    protected PurposeRepository purposeRepository;

    @Test
    public void DistrictTest() {
        logger.info("----- 测试区域是否添加成功 panjie -----");
        assertThat(districtRepository.getByName("廊坊市")).isNotNull();
    }

    @Test
    public void DepartmentTest() {
        logger.info("----- 测试部门是否添加成功 panjie -----");
        assertThat(departmentRepository.findByName("河北省管理部门")).isNotNull();
        assertThat(departmentRepository.findByName("廊坊市管理部门")).isNotNull();
    }

    @Test
    public void MenuTest() {
        logger.info("----- 测试后台菜单是否添加成功 panjie -----");
        List<Menu> menus = (List<Menu>) menuRepository.findAll();
        assertThat(menus.size()).isGreaterThan(20);
        return;
    }

    // 精度显示名称
    @Test
    public void addAccuracyDisplayName() {
        logger.info("----- 测试示值偏差显示名称是否添加成功 panjie -----");
        if (jpaDdlAuto == "create") {
            List<AccuracyDisplayName> accuracyDisplayNames = (List<AccuracyDisplayName>) accuracyDisplayNameRepository.findAll();
            assertThat(accuracyDisplayNames.size()).isEqualTo(2);
        }
    }


    // 用途
    @Test
    public void addPurpose() {
        logger.info("断言6个用途已成功添加到数据库");
        List<Purpose> purposes = (List<Purpose>) purposeRepository.findAll();
        assertThat(purposes.size()).isEqualTo(6);
    }

}