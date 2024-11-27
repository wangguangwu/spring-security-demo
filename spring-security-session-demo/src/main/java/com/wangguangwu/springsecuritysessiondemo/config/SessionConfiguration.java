package com.wangguangwu.springsecuritysessiondemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;

/**
 * 配置类，用于启用 Spring Session 并使用 Redis 作为数据存储。
 *
 * @author wangguangwu
 * &#064;EnableRedisIndexedHttpSession  自动化配置 Spring Session，默认 30 分钟过期。
 * 将会话数据存储到 Redis 中，实现会话的集中式管理。
 * <p>
 * 功能特点：
 * 1. 会话数据集中存储：将会话数据保存到 Redis 中，支持分布式应用共享会话数据。
 * 2. 会话过期支持：可通过配置设置会话的过期时间。
 * 3. 支持分布式：适用于多实例部署的场景。
 * <p>
 * 使用说明：
 * - 确保 Redis 服务已正确配置，并在 application.yaml 中定义 Redis 连接参数。
 * - 启用此配置后，Spring Boot 的默认会话管理将被替换为 Redis 会话管理。
 */
@Configuration
@EnableRedisIndexedHttpSession
public class SessionConfiguration {

}

