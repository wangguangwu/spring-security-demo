package com.wangguangwu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例控制器
 *
 * @author wangguangwu
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("当前登录用户: {}", authentication.getName());
        return "Hello " + authentication.getName() + "! Welcome to Spring Security!";
    }

    @GetMapping("/info")
    public Authentication userInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
