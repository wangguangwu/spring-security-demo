package com.wangguangwu.springsecuritysessiondemo.controller;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 管理端控制器，提供会话管理功能。
 *
 * @author wangguangwu
 */
@RestController
@RequestMapping("/session")
public class SessionController {

//    @Resource
//    private RedisSessionRepository sessionRepository;
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * 根据用户名获取对应的会话信息。
//     *
//     * @param username 用户名
//     * @return 返回该用户的所有会话信息
//     */
//    @GetMapping("/list")
//    public Map<String, Session> getSessionsByUsername(@RequestParam("username") String username) {
//        if (username == null || username.trim().isEmpty()) {
//            return Collections.emptyMap();
//        }
//
//        // 获取 Redis 中存储的所有会话键
//        Set<String> keys = redisTemplate.keys("spring:session:sessions:*");
//        if (keys.isEmpty()) {
//            return Collections.emptyMap();
//        }
//
//        Map<String, Session> result = new HashMap<>();
//        for (String key : keys) {
//            // 提取 sessionId
//            if (!key.startsWith("spring:session:sessions:")) {
//                continue;
//            }
//            String sessionId = key.substring("spring:session:sessions:".length());
//            Session session = sessionRepository.findById(sessionId);
//            if (session != null) {
//                // 获取用户名
//                String principalName = extractPrincipalName(session);
//                if (username.equals(principalName)) {
//                    result.put(sessionId, session);
//                }
//            }
//        }
//        return result;
//    }
//
//    private String extractPrincipalName(Session session) {
//        Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
//        if (securityContext instanceof SecurityContext context) {
//            // 提取用户名
//            return context.getAuthentication().getName();
//        }
//        return null;
//    }
}
