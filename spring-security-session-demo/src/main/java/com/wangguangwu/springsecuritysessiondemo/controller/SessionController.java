package com.wangguangwu.springsecuritysessiondemo.controller;

import jakarta.annotation.Resource;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * 管理端控制器，提供会话管理功能。
 *
 * @author wangguangwu
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Resource
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    /**
     * 查询指定用户名的会话信息。
     *
     * @param username 用户名，用于查找关联的会话
     * @return 用户名对应的会话列表，若不存在则返回空集合
     */
    @GetMapping("/list")
    public Map<String, ? extends Session> getSessionsByUsername(@RequestParam("username") String username) {
        // 参数校验，防止空值引发问题
        if (username == null || username.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        // 查询并返回会话信息
        return sessionRepository.findByPrincipalName(username);
    }
}
