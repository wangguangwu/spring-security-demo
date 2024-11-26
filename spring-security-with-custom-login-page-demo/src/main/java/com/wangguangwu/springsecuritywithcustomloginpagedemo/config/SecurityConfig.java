package com.wangguangwu.springsecuritywithcustomloginpagedemo.config;

import com.wangguangwu.springsecuritywithcustomloginpagedemo.service.CustomUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author wangguangwu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 允许访问登录页面和静态资源
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // 指定自定义登录页面
                        .loginPage("/login")
                        // 登录表单提交地址
                        .loginProcessingUrl("/doLogin")
                        // 登录成功后跳转到首页
                        .defaultSuccessUrl("/index", true)
                        // 登录失败返回登录页面
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        // 配置注销地址
                        .logoutUrl("/logout")
                        // 注销成功后返回登录页面
                        .logoutSuccessUrl("/login?logout=true")
                )
                .userDetailsService(customUserDetailsService)
                // 禁用 CSRF（仅开发环境下）
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
