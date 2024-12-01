# Spring Security 01 - 基础认证示例

## 功能简介

本模块展示了 Spring Security 的基础功能，包括：
- 基本的登录认证
- 用户信息获取
- 退出登录流程
- 安全配置示例

## 技术要点

- Spring Boot 3.x
- Spring Security 6.x
- 基于内存的用户认证
- 自定义安全配置
- SecurityContextHolder 的使用

## 快速开始

### 1. 配置说明

本示例使用了以下配置：
- 服务端口：8080
- 默认用户名：admin
- 默认密码：123456

### 2. 启动服务

运行 `SpringSecurity01Application` 类启动服务。

### 3. 接口说明

#### 3.1 Hello 接口

```http request
GET http://localhost:8080/hello
```

返回欢迎信息，包含当前登录用户的用户名。

#### 3.2 用户信息接口

```http request
GET http://localhost:8080/info
```

返回当前登录用户的完整认证信息。

### 4. 认证流程

#### 4.1 访问受保护资源

访问任何接口都会重定向到登录页面：

```
http://localhost:8080/login
```

#### 4.2 登录认证

在登录页面输入以下账号和密码：
- 用户名：`admin`
- 密码：`123456`

#### 4.3 退出登录

访问以下地址即可退出登录：

```
http://localhost:8080/logout
```

## 核心代码说明

### SecurityConfig

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 配置安全过滤链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // 配置认证和授权规则
    }

    // 配置内存用户
    @Bean
    public UserDetailsService userDetailsService() {
        // 创建测试用户
    }
}
```

### HelloController

```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        // 获取当前登录用户信息
    }

    @GetMapping("/info")
    public Authentication userInfo() {
        // 返回完整的认证信息
    }
}
```

## 调试说明

应用配置了详细的日志级别，可以在控制台查看 Spring Security 的详细认证流程：

```yaml
logging:
  level:
    com.wangguangwu: debug
    org.springframework.security: debug
```

## 注意事项

1. 本示例使用了内存中的用户存储，仅用于演示
2. 密码编码使用了不安全的 `withDefaultPasswordEncoder()`，仅用于开发测试
3. 生产环境建议使用数据库存储用户信息，并使用更安全的密码编码器（如 BCrypt）