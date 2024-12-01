package com.wangguangwu.security.memory.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 安全配置属性类
 *
 * @author wangguangwu
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    /**
     * 允许匿名访问的路径
     */
    private String[] anonymousUrls = {
            "/doc.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    /**
     * 登录相关配置
     */
    private Login login = new Login();

    /**
     * JWT 相关配置
     */
    private Jwt jwt = new Jwt();

    @Data
    public static class Login {
        /**
         * 登录页面
         */
        private String loginPage = "/login";

        /**
         * 登录处理 URL
         */
        private String loginProcessingUrl = "/login";

        /**
         * 登录成功跳转页面
         */
        private String successUrl = "/";

        /**
         * 登录失败跳转页面
         */
        private String failureUrl = "/login?error";

        /**
         * 注销 URL
         */
        private String logoutUrl = "/logout";

        /**
         * 记住我超时时间（秒）
         */
        private int rememberMeSeconds = 3600;
    }

    @Data
    public static class Jwt {
        /**
         * JWT 密钥
         */
        private String secret = "your-secret-key";

        /**
         * token 有效期（分钟）
         */
        private long tokenValidityInMinutes = 30;

        /**
         * 记住我 token 有效期（分钟）
         */
        private long rememberMeTokenValidityInMinutes = 1440; // 24小时
    }
}
