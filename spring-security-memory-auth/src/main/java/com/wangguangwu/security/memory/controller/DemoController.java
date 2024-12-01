package com.wangguangwu.security.memory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 示例控制器 - 展示不同的权限控制方式
 *
 * @author wangguangwu
 */
@Tag(name = "示例接口", description = "用于演示 Spring Security 权限控制的不同方式")
@Slf4j
@RestController
public class DemoController {

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/api/user/info")
    public Map<String, Object> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> info = new HashMap<>(3);
        info.put("name", authentication.getName());
        info.put("authorities", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        info.put("details", authentication.getDetails());
        return info;
    }

    // 方式1：使用 URL 模式匹配（在 SecurityConfig 中配置）
    @Operation(summary = "用户页面 - URL权限控制")
    @GetMapping("/api/user/page")
    public String userPage() {
        return "这是用户页面，所有认证用户都可以访问 (URL 模式匹配控制)";
    }

    // 方式2：使用 @Secured 注解
    @Operation(summary = "管理员页面 - @Secured注解控制")
    @Secured("ROLE_ADMIN")
    @GetMapping("/api/admin/secured")
    public String adminSecuredPage() {
        return "这是管理员页面，使用 @Secured 注解控制";
    }

    // 方式3：使用 @RolesAllowed 注解
    @Operation(summary = "管理员页面 - @RolesAllowed注解控制")
    @RolesAllowed("ADMIN")
    @GetMapping("/api/admin/roles-allowed")
    public String adminRolesAllowedPage() {
        return "这是管理员页面，使用 @RolesAllowed 注解控制";
    }

    // 方式4：使用 @PreAuthorize 注解（支持复杂的 SpEL 表达式）
    @Operation(summary = "管理员页面 - @PreAuthorize注解控制")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('admin:read')")
    @GetMapping("/api/admin/pre-authorize")
    public String adminPreAuthorizePage() {
        return "这是管理员页面，使用 @PreAuthorize 注解控制，需要 ADMIN 角色和 admin:read 权限";
    }

    // 方式5：使用 @PreAuthorize 进行多角色控制
    @Operation(summary = "高级管理页面 - 多重权限控制")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('admin:write') or hasRole('SUPER_ADMIN')")
    @GetMapping("/api/admin/advanced")
    public String adminAdvancedPage() {
        return "这是高级管理页面，需要：(ADMIN角色 且 admin:write权限) 或 SUPER_ADMIN角色";
    }
}
