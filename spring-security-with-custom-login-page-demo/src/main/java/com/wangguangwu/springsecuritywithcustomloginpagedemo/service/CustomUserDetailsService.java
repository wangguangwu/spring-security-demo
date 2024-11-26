package com.wangguangwu.springsecuritywithcustomloginpagedemo.service;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wangguangwu
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Lazy
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 模拟数据库
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 确保密码是用同一个 PasswordEncoder 加密后的结果
        return User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();
    }
}
