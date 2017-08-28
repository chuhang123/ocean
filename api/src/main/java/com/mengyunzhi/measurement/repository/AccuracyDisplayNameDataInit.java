package com.mengyunzhi.measurement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/27.
 * 精度显示名称 数据初始化
 */
@Component
public class AccuracyDisplayNameDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private static Logger logger = Logger.getLogger(AccuracyDisplayNameDataInit.class.getName());
    @Autowired private AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<AccuracyDisplayName> accuracyDisplayNamesAll = (List<AccuracyDisplayName>) accuracyDisplayNameRepository.findAll();
        if (accuracyDisplayNamesAll.size() == 0) {
            logger.info("------------------添加检定结果---------------------");
            Set<AccuracyDisplayName> accuracyDisplayNames = new HashSet<AccuracyDisplayName>();

            AccuracyDisplayName accuracyDisplayName1 = new AccuracyDisplayName();
            accuracyDisplayName1.setName("准确度等级");
            accuracyDisplayName1.setPinyin("zhunquedudengji");
            accuracyDisplayNames.add(accuracyDisplayName1);

            AccuracyDisplayName accuracyDisplayName2 = new AccuracyDisplayName();
            accuracyDisplayName2.setName("最大允许误差");
            accuracyDisplayName2.setPinyin("zuidayunxuwucha");
            accuracyDisplayNames.add(accuracyDisplayName2);

            AccuracyDisplayName accuracyDisplayName3 = new AccuracyDisplayName();
            accuracyDisplayName3.setName("MPE");
            accuracyDisplayName3.setPinyin("MPE");
            accuracyDisplayNames.add(accuracyDisplayName3);

            AccuracyDisplayName accuracyDisplayName4 = new AccuracyDisplayName();
            accuracyDisplayName4.setName("精度");
            accuracyDisplayName4.setPinyin("jingdu");
            accuracyDisplayNames.add(accuracyDisplayName4);

            AccuracyDisplayName accuracyDisplayName5 = new AccuracyDisplayName();
            accuracyDisplayName5.setName("示值误差");
            accuracyDisplayName5.setPinyin("shizhiwucha");
            accuracyDisplayNames.add(accuracyDisplayName5);

            AccuracyDisplayName accuracyDisplayName6 = new AccuracyDisplayName();
            accuracyDisplayName6.setName("测量误差");
            accuracyDisplayName6.setPinyin("celiangwucha");
            accuracyDisplayNames.add(accuracyDisplayName6);

            AccuracyDisplayName accuracyDisplayName7 = new AccuracyDisplayName();
            accuracyDisplayName7.setName("检测误差");
            accuracyDisplayName7.setPinyin("jiancewucha");
            accuracyDisplayNames.add(accuracyDisplayName7);

            AccuracyDisplayName accuracyDisplayName8 = new AccuracyDisplayName();
            accuracyDisplayName8.setName("示值相对误差");
            accuracyDisplayName8.setPinyin("shizhixiangduiwucha");
            accuracyDisplayNames.add(accuracyDisplayName8);

            AccuracyDisplayName accuracyDisplayName9 = new AccuracyDisplayName();
            accuracyDisplayName9.setName("基本误差");
            accuracyDisplayName9.setPinyin("jibenwucha");
            accuracyDisplayNames.add(accuracyDisplayName9);

            accuracyDisplayNameRepository.save(accuracyDisplayNames);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
