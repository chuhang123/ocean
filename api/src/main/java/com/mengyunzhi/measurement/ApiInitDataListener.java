package com.mengyunzhi.measurement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by panjie on 17/6/5.
 * 在应用启动时，进行数据初始化。数据初始化
 */
@Component
public class ApiInitDataListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(ApiInitDataListener.class.getName());

    // 自动加载application.properties中的配置项:spring.jpa.hibernate.ddl-auto
    @Value("${spring.jpa.hibernate.ddl-auto}")
    protected String jpaDdlAuto;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

    /**
     * 在初始化时的执行顺序，数值超小，执行超靠前。
     *
     * @return
     */
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 10;
    }
}