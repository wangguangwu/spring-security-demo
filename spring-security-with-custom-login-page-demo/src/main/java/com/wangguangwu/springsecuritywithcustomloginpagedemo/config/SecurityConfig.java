package cn.daimajiangxin.springboot.learning.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {
    @Resource
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/index")
                                .permitAll()
                                .anyRequest().authenticated() // 其他所有请求都需要认证
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginProcessingUrl("/doLogin")
                                .loginPage("/login") // 指定登录页面
                                .successForwardUrl("/index")
                                .permitAll() // 允许所有人访问登录页面
                )
                .logout(logout ->
                        logout
                                .permitAll() // 允许所有人访问注销URL
                )    // 注册重写后的UserDetailsService实现
                .userDetailsService(userDetailsService);
        return http.build();
        // 添加自定义过滤器或其他配置
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}
