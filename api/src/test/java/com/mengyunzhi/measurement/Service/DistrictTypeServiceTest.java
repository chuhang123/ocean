package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DistrictType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

/**
 * Created by liming on 17-6-2.
 */
public class DistrictTypeServiceTest extends ServiceTest{
    @Autowired
    private DistrictTypeService districtTypeService;
    @Autowired
    private UserService userService;
    static private Logger logger = Logger.getLogger(DistrictTypeService.class.getName());
    @Test
    public void save() throws Exception {
        logger.info("创建实体");
        DistrictType districtType = new DistrictType();
        logger.info("保存");
        districtTypeService.save(districtType);
        logger.info("断言");
        assertThat(districtType.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("创建实体");
        DistrictType districtType = new DistrictType();
        logger.info("保存实体");
        districtTypeService.save(districtType);
        List<DistrictType> listDistrictType = new ArrayList<DistrictType>();
        logger.info("取出所有的数据");
        listDistrictType = districtTypeService.getAll();
        logger.info("断言");
        assertThat(listDistrictType.size()).isNotEqualTo(0);
    }

}