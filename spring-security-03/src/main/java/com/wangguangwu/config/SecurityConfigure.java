package com.wangguangwu.config;

import com.wangguangwu.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 自定义 security 相关配置
 * @author wangguangwu
 */
@Configuration
public class SecurityConfigure {

    private final MyUserDetailService myUserDetailService;

    public SecurityConfigure(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    // 配置 UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    // 配置 AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 配置 SecurityFilterChain 替代 WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login.html").permitAll() // 指定登录页面允许所有人访问
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .formLogin(form -> form
                        .loginPage("/login.html") // 自定义登录页面
                        .loginProcessingUrl("/doLogin") // 登录处理 URL
                        .usernameParameter("uname") // 自定义用户名参数名
                        .passwordParameter("passwd") // 自定义密码参数名
                        .defaultSuccessUrl("/index.html", true) // 登录成功跳转页面
                        .failureUrl("/login.html") // 登录失败跳转页面
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 注销 URL
                        .logoutSuccessUrl("/login.html") // 注销成功后跳转页面
                )
                .csrf(csrf -> csrf.disable()); // 关闭 CSRF 保护

        return http.build();
    }
}
