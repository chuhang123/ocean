package com.mengyunzhi.measurement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.logging.Logger;

/**
 * Created by panjie on 17/5/31.
 * 设置spring repository rest 的全局配置信息。由于repository rest与MVC是由两个类单独控制
 * 所以需要分别进行配置.
 * http://docs.spring.io/spring-data/rest/docs/current/api/org/springframework/data/rest/webmvc/config/RepositoryRestConfigurerAdapter.html
 * http://www.imooc.com/article/15722?block_id=tuijian_wz
 * todo:将两个配置信息中的常量进行合并。
 */
@Configuration
public class GlobalRepositoryRestConfigurer extends RepositoryRestConfigurerAdapter {
    static private Logger logger = Logger.getLogger(GlobalRepositoryRestConfigurer.class.getName());
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        logger.info("设置spring repository rest 的全局配置信息：与webConfig配置信息相同");
        config.getCorsRegistry()
                .addMapping("/**")          // 映射信息
                .allowedOrigins("*")                    // 跨域信息
                .allowedHeaders("*")                    // 允许的头信息
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"); // 允许的请求方法信息
    }
}
