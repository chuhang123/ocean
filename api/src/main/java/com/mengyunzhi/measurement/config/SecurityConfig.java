package com.mengyunzhi.measurement.config;

import com.mengyunzhi.measurement.security.CustomerUserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.logging.Logger;

/**
 * Created by panjie on 17/5/17.
 * Spring 安全设置
 */
@Api(tags = "SecurityConfig 安全策略配置", description = "使用spring security进行安全策略配置")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    static private Logger logger = Logger.getLogger(SecurityConfig.class.getName());

    @ApiModelProperty("将自定义的CustomerUserDetailsServiceImpl注入")
    @Bean
    UserDetailsService customUserService() {
        return new CustomerUserDetailsServiceImpl();
    }

    @ApiModelProperty("@Override configure 注入用户认证信息")
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        logger.info("调用自定义的UserDetailsService进行用户的认证");
        authenticationManagerBuilder.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            // 加密算法
            @Override
            public String encode(CharSequence charSequence) {
                return (String) charSequence;
            }

            // 验证密码是否正确算法（解密算法）
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        });
    }

    @ApiOperation("@Override configure 重写spring security基本设置信息")
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        logger.info("设置httpSecurity:认证方式，授权请求，跨域应用，CSRF信息等");
        httpSecurity
                // 基本认证方式BasicAuth.相对还有Digest Auth,OAuth1.0,2.0等
                .httpBasic()
                .and()


                // 设置授权请求,如果是/User/login /v2/api-docs则允许所有操作
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/User/login", "/v2/api-docs", "/District/getAll", "/User/checkUsernameIsExist/*", "/User/register", "/District/getRootDistrictTree",  "/DepartmentType/", "/MandatoryInstrumentApply/generateWordApplyByToken/*").permitAll()
                .anyRequest().authenticated()
                .and()

                // 设置为跨域应用
                .cors()
                .and()

                // 设置CSRF信息。否则在进行请求，将报csrf token not found错误
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                // 设置禁用csrf检测。TODO:深入学习csrf()在此的作用
                .and()
                .csrf().disable();
    }
}
