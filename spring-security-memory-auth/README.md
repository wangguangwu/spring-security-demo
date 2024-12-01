# Spring Security Memory Auth

基于内存用户的认证示例模块，演示 Spring Security 的基本使用方法。

## 功能特性

- 基于内存的用户存储
- 默认登录页面
- 多种权限控制方式
- 集成 Swagger 接口文档

## 权限控制详解

本模块实现了五种不同的权限控制方式，展示了 Spring Security 的强大和灵活性：

### 1. URL 模式匹配（配置式）

在 SecurityConfig 中通过配置实现：
```java
.authorizeHttpRequests(authorize -> authorize
    .requestMatchers("/api/user/**").hasRole("USER")
    .requestMatchers("/api/admin/**").hasRole("ADMIN")
)
```

### 2. @Secured 注解

简单的角色控制：
```java
@Secured("ROLE_ADMIN")
@GetMapping("/api/admin/secured")
public String adminSecuredPage() {
    return "这是管理员页面";
}
```

### 3. @RolesAllowed 注解

使用 JSR-250 标准注解：
```java
@RolesAllowed("ADMIN")
@GetMapping("/api/admin/roles-allowed")
public String adminRolesAllowedPage() {
    return "这是管理员页面";
}
```

### 4. @PreAuthorize 注解

支持复杂的 SpEL 表达式：
```java
@PreAuthorize("hasRole('ADMIN') and hasAuthority('admin:read')")
@GetMapping("/api/admin/pre-authorize")
public String adminPreAuthorizePage() {
    return "这是管理员页面";
}
```

### 5. 细粒度权限控制

通过 authorities 实现详细的权限控制：
```java
UserDetails admin = User.builder()
    .username("admin")
    .password(password)
    .roles("USER", "ADMIN")
    .authorities("ROLE_USER", "ROLE_ADMIN", 
        "user:read", "user:write",
        "admin:read", "admin:write")
    .build();
```

## 权限控制说明

### 角色与权限
- 角色（Role）是一组权限的集合，默认添加 "ROLE_" 前缀
- 权限（Authority）是更细粒度的控制单位，如 "user:read"
- 一个用户可以有多个角色和多个权限

### 选择建议
1. URL 模式匹配：适合基于路径的简单权限控制
2. @Secured：适合简单的角色检查
3. @RolesAllowed：适合需要遵循 JSR-250 标准的项目
4. @PreAuthorize：适合需要复杂权限表达式的场景
5. 细粒度权限：适合需要详细权限控制的企业应用

## 快速开始

1. 启动应用：
   ```bash
   mvn spring-boot:run
   ```

2. 访问接口文档：
   ```
   http://localhost:8080/doc.html
   ```

## 测试账号

1. 普通用户
   - 用户名：user
   - 密码：password
   - 角色：USER
   - 可访问：/api/user/** 接口

2. 管理员用户
   - 用户名：admin
   - 密码：password
   - 角色：USER, ADMIN
   - 权限：user:read, user:write, admin:read, admin:write
   - 可访问：所有接口

## 接口说明

1. 用户信息接口
   ```http request
   GET /api/user/info
   ```

2. 用户页面（URL 权限控制）
   ```http request
   GET /api/user/page
   ```

3. 管理员页面（@Secured）
   ```http request
   GET /api/admin/secured
   ```

4. 管理员页面（@RolesAllowed）
   ```http request
   GET /api/admin/roles-allowed
   ```

5. 管理员页面（@PreAuthorize）
   ```http request
   GET /api/admin/pre-authorize
   ```

## 核心配置

### Security 配置

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
    public InMemoryUserDetailsManager userDetailsService() {
        // 创建测试用户
    }
}
```

## 注意事项

1. 本示例仅用于演示，不要在生产环境中使用内存用户存储
2. 密码使用了 BCrypt 加密，可以根据需要更换其他加密方式
3. 为了演示方便，禁用了 CSRF 保护，生产环境应该启用
4. 方法级的权限控制需要启用 @EnableMethodSecurity

## 调试说明

1. 使用不同账号登录测试不同权限
2. 查看控制台日志了解权限判断过程
3. 可以通过 Swagger 文档测试所有接口
4. 注意观察不同权限控制方式的效果

## 常见问题

1. 403 错误：表示权限不足
2. 401 错误：表示未认证
3. 注解不生效：检查是否启用了 @EnableMethodSecurity
4. 角色不匹配：检查是否包含 "ROLE_" 前缀

## 单元测试

本模块包含完整的单元测试用例，展示了如何测试不同的权限控制场景：

### 测试场景

1. 未认证访问
   - 测试未登录用户访问受保护资源
   - 验证重定向到登录页面

2. 普通用户访问
   - 测试 USER 角色访问用户接口
   - 测试 USER 角色访问管理员接口（预期失败）

3. 管理员访问
   - 测试 ADMIN 角色访问管理员接口
   - 测试带有特定权限的访问控制

### 运行测试

```bash
mvn test
```

## 安全最佳实践

1. 密码安全
   - 使用强密码策略
   - 实现密码过期机制
   - 限制密码重试次数

2. 会话管理
   - 设置会话超时
   - 防止会话固定攻击
   - 实现单点登录（可选）

3. 访问控制
   - 遵循最小权限原则
   - 实现角色继承
   - 定期审查权限分配

4. 安全配置
   - 启用 HTTPS
   - 配置安全响应头
   - 实现 CORS 策略

## 性能优化建议

1. 缓存配置
   - 缓存用户权限信息
   - 使用分布式缓存（生产环境）

2. 数据库优化
   - 合理设计权限表结构
   - 优化权限查询语句

3. 并发处理
   - 使用线程池处理并发请求
   - 实现请求限流

## 扩展功能

1. 动态权限
   - 运行时更新权限配置
   - 基于时间的权限控制

2. 审计日志
   - 记录权限变更历史
   - 实现操作审计

3. 多因素认证
   - 集成短信验证
   - 支持 Google Authenticator

## 实际应用场景

### 1. 系统管理模块

在后台管理系统中，通常需要对不同角色进行权限控制：

```java
// 系统管理员接口
@PreAuthorize("hasRole('ADMIN') and hasAuthority('system:manage')")
@GetMapping("/system/config")
public String systemConfig() {
    return "系统配置页面";
}

// 用户管理接口
@PreAuthorize("hasAnyRole('ADMIN', 'USER_MANAGER')")
@GetMapping("/user/list")
public String userList() {
    return "用户列表页面";
}
```

### 2. 数据权限控制

针对同一接口，不同角色可以访问不同范围的数据：

```java
// 普通用户只能查看自己的订单
@PreAuthorize("hasRole('USER')")
@GetMapping("/order/self")
public String selfOrders() {
    return "个人订单列表";
}

// 管理员可以查看所有订单
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/order/all")
public String allOrders() {
    return "所有订单列表";
}
```

### 3. 操作权限控制

对数据的增删改查操作进行细粒度控制：

```java
// 查看权限
@PreAuthorize("hasAuthority('user:read')")
@GetMapping("/user/{id}")
public String getUser(@PathVariable Long id) {
    return "用户详情";
}

// 修改权限
@PreAuthorize("hasAuthority('user:write')")
@PutMapping("/user/{id}")
public String updateUser(@PathVariable Long id) {
    return "更新用户信息";
}

// 删除权限
@PreAuthorize("hasAuthority('user:delete')")
@DeleteMapping("/user/{id}")
public String deleteUser(@PathVariable Long id) {
    return "删除用户";
}
```

## 权限控制最佳实践

### 1. URL 匹配使用建议

- 使用 Ant 风格的路径匹配
- 从具体到模糊的顺序配置
- 考虑使用正则表达式进行精确控制

```java
.authorizeHttpRequests(authorize -> authorize
    // 具体路径在前
    .requestMatchers("/api/admin/specific/**").hasRole("SUPER_ADMIN")
    // 模糊路径在后
    .requestMatchers("/api/admin/**").hasRole("ADMIN")
)
```

### 2. 注解使用建议

- @Secured：简单的角色控制
- @RolesAllowed：标准 Java 注解，便于迁移
- @PreAuthorize：复杂的权限表达式

```java
// 简单角色控制
@Secured("ROLE_ADMIN")
public void simpleCheck() {}

// 标准注解
@RolesAllowed({"USER", "ADMIN"})
public void standardCheck() {}

// 复杂表达式
@PreAuthorize("hasRole('ADMIN') and hasAuthority('user:write') or #user.id == authentication.principal.id")
public void complexCheck() {}
```

### 3. 权限粒度控制

建议采用以下格式定义权限：
- 模块名:操作:对象
- 例如：system:read:user, order:write:detail

```java
// 完整示例
@PreAuthorize("hasAuthority('order:read:detail')")
public void viewOrderDetail() {}

@PreAuthorize("hasAuthority('order:write:status')")
public void updateOrderStatus() {}
```

## 常见问题解决方案

### 1. 权限不足问题

当出现 403 错误时的排查步骤：
1. 检查用户角色是否正确赋予
2. 验证权限表达式是否正确
3. 确认角色前缀是否正确（ROLE_）

### 2. 注解不生效问题

可能的原因和解决方案：
1. 检查是否启用了 @EnableMethodSecurity
2. 确认方法是否是 public
3. 验证是否通过代理调用方法

### 3. 动态权限问题

如何实现运行时的权限变更：
1. 使用自定义 SecurityExpressionRoot
2. 实现动态权限验证器
3. 配置权限决策管理器

## 进阶使用

### 1. 自定义投票器

```java
public class CustomVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, 
            Collection<ConfigAttribute> attributes) {
        // 自定义投票逻辑
        return ACCESS_GRANTED;
    }
}
```

### 2. 自定义过滤器

```java
public class CustomSecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 自定义过滤逻辑
        filterChain.doFilter(request, response);
    }
}
```

## 下一步学习建议

1. 了解 Spring Security 的过滤器链机制
2. 学习自定义认证和授权流程
3. 掌握 Spring Security 的事件机制
4. 研究与缓存的集成方案

以上内容涵盖了内存认证模块中权限控制的主要使用场景和最佳实践，可以作为实际开发的参考指南。
