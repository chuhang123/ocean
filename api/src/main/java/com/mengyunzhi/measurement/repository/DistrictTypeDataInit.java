package com.mengyunzhi.measurement.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * Created by panjie on 17/7/25.
 * 区域类型数据初始化
 */
@Component
public class DistrictTypeDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(DistrictTypeDataInit.class.getName());
    @Autowired
    private DistrictTypeRepository districtTypeRepository;       // 区域类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DistrictType> districtTypes1 = (List<DistrictType>) districtTypeRepository.findAll();
        if (districtTypes1.size() == 0) {
            logger.info("----- 添加区域类型 panjie -----");
            HashSet<DistrictType> districtTypes = new HashSet<DistrictType>();
            districtTypes.add(new DistrictType("省", "sheng", null));
            districtTypes.add(new DistrictType("市", "shi", null));
            districtTypes.add(new DistrictType("区\\县", "quxian", null));
            districtTypeRepository.save(districtTypes);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
