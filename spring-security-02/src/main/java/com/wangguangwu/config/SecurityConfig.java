package com.wangguangwu.config;

import com.wangguangwu.handler.MyAuthenticationFailureHandler;
import com.wangguangwu.handler.MyAuthenticationSuccessHandler;
import com.wangguangwu.service.MyUserDetailService;
import com.wangguangwu.handler.MyLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * spring-security 配置类
 *
 * @author wangguangwu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 配置白名单
                        .requestMatchers("/index", "/login.html").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // 自定义登录页面
                        .loginPage("/login.html")
                        // 登录表单提交地址
                        .loginProcessingUrl("/doLogin")
                        // 自定义用户名和密码参数
                        .usernameParameter("uname")
                        .passwordParameter("passwd")
                        // 登录成功处理器
                        .successHandler(new MyAuthenticationSuccessHandler())
                        // 登录失败处理器
                        .failureHandler(new MyAuthenticationFailureHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        // 配置多个注销路径
                        .logoutRequestMatcher(new OrRequestMatcher(
                                new AntPathRequestMatcher("/aa", "GET"),
                                new AntPathRequestMatcher("/bb", "POST")
                        ))
                        // 会话失效
                        .invalidateHttpSession(true)
                        // 清除认证标记
                        .clearAuthentication(true)
                        // 自定义注销成功处理器
                        .logoutSuccessHandler(new MyLogoutSuccessHandler())
                        .permitAll()
                )
                // 禁用 CSRF（仅开发环境下，生产环境应启用）
                .csrf(csrf -> csrf.disable())
                // 配置自定义的 UserDetailsService
                .userDetailsService(myUserDetailService);
        return http.build();
    }
}
