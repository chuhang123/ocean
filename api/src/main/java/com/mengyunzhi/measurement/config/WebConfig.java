package com.mengyunzhi.measurement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/5/10.
 * Spring MVC 配置信息
 */
// 设置允许跨域请求
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    static private Logger logger = Logger.getLogger(WebConfig.class.getName());

    // 配置允许跨域请求的映射(路由、地址、URL）信息
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("设置请求信息: 路由信息，允许的header，跨域地址，允许方法，暴露的x-auth-token");
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
                .exposedHeaders("x-auth-token")
                .maxAge(3600);
    }

    // 配置swagger使用的路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("配置swagger-ui.html资源地址");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        logger.info("配置/webjars资源地址");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 解决spring mvc @jsonview page empty(springMvc 使用jsonView时，返回page信息为空对象)的问题
     * 这个问题的解决方案适用的比较少，而且还有信息存在误导。
     * 最后总结下解决方法：
     * 1.绝对不能在配置文件中去配置那个 DEFAULT_VIEW_INCLUSION。
     * 如果这么做了，就会发现C层配置的JsonView会完全失去作用
     * 2. 本问题中，spring 官方社区的回答比stackoverflow 还要靠谱。
     * 在 https://jira.spring.io/browse/SPR-13331 的回答中，甚至有了给出了github的地址
     * 3. 参考代码时，要使其它的配置完全相同，比如我在参考时，就是在已经设置了DEFAULT_VIEW_INCLUSION为true的前提下；
     *    而这个配置项，参考代码中并没有。进而没有在第一时间内验证出参考代码的正确性。
     * 正确的可被参考的代码：https://github.com/sdeleuze/SpringSampleProject
     * 
     * 总结：问题解决了大概两天的时间。开始低估了问题了难度，当发现初步找的答案并不能解决实际问题时。
     * 并没有沉下心来去看每一篇google到的文章。
     * 最后，静心的仔细的学习了每一篇文章，并找到了相关的示例代码，同时删除了按照其它方法配置的不适用配置项后。
     * 问题得以解决。
     * 所以：
     * 1. 当发现低估问题的难度的时候，确诊关键字没有问题，就要静心来学习。
     * 2. 每找到一个方法，都值得一试。但不要将两种解决方案同时试。试第一种不行，就要将代码进行恢复。
     * 然后才能继续试第二种。
     * 3. 读懂找到资料的每一行英文，这很重要！
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().defaultViewInclusion(true).build();
        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    }
}
