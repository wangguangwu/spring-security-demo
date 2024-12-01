# Spring Security 从入门到实践

## 前言

在当今的互联网应用中，安全性已经成为一个不可忽视的关键要素。Spring Security 作为 Spring 生态系统中的安全框架，提供了完整的认证、授权和攻击防护等功能。本文将从基础到实践，全面介绍 Spring Security 的使用，帮助读者构建安全可靠的应用程序。

## 一、Spring Security 基础概念

### 1.1 什么是 Spring Security

Spring Security 是一个功能强大且高度可定制的身份验证和访问控制框架。作为 Spring 框架的重要组成部分，它是保护基于 Spring 的应用程序的事实标准。

**核心功能：**
- 全面的认证和授权支持
- 防护常见的网络攻击
- Servlet API 集成
- 可选的 OAuth2/OpenID 支持

**主要特性：**
- 可扩展的认证和授权架构
- 密码加密工具
- 会话管理
- 方法级安全控制

**与其他安全框架对比：**

| 特性 | Spring Security | Shiro | JWT |
|-----|----------------|-------|-----|
| 社区支持 | 强大 | 一般 | 一般 |
| 学习曲线 | 较陡 | 平缓 | 简单 |
| 功能完整性 | 完整 | 基础完整 | 仅令牌 |
| Spring 集成 | 原生支持 | 需配置 | 需配置 |
| 微服务支持 | 良好 | 一般 | 良好 |

### 1.2 核心组件

#### SecurityContextHolder
SecurityContextHolder 是 Spring Security 存储认证信息的地方。默认使用 ThreadLocal 策略，确保每个线程都有其独立的安全上下文。

```java
// 获取当前认证信息
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
```

#### Authentication
Authentication 接口是 Spring Security 最核心的接口之一，代表认证请求或已认证的主体。主要包含：
- 主体（Principal）：用户的标识
- 凭证（Credentials）：通常是密码
- 权限（Authorities）：用户被授予的权限

```java
public interface Authentication extends Principal, Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();
    Object getCredentials();
    Object getDetails();
    Object getPrincipal();
    boolean isAuthenticated();
    void setAuthenticated(boolean isAuthenticated);
}
```

#### UserDetails
UserDetails 接口提供了用户的核心信息。实现这个接口的类通常用于存储用户信息。

```java
public interface UserDetails extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
```

#### UserDetailsService
UserDetailsService 是加载用户特定数据的核心接口。它只有一个方法：

```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

#### GrantedAuthority
GrantedAuthority 接口表示授予给主体的权限。

```java
public interface GrantedAuthority extends Serializable {
    String getAuthority();
}
```

### 1.3 认证与授权

#### 认证（Authentication）
认证是验证用户身份的过程。Spring Security 提供了多种认证方式：
- 表单登录
- HTTP Basic 认证
- OAuth2/OpenID
- Remember-me
- 自定义认证

认证流程示例：
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
            .and()
            .rememberMe()
                .key("uniqueAndSecret");
    }
}
```

#### 授权（Authorization）
授权决定用户能够访问哪些资源。Spring Security 提供了多种授权方式：
- URL 模式匹配
- 方法注解
- 领域对象安全表达式

授权配置示例：
```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
```

#### 认证与授权的关系
1. 认证先于授权发生
2. 认证确认"你是谁"，授权决定"你能做什么"
3. 认证信息通常包含授权所需的角色/权限信息

在下一部分，我们将深入探讨 Spring Security 的工作原理，包括过滤器链、认证流程和授权决策的具体实现。

## 二、Spring Security 工作原理

### 2.1 过滤器链

Spring Security 的核心是一系列的 Servlet 过滤器，它们组成了一个过滤器链。每个过滤器都负责特定的安全功能，按照特定的顺序执行。

#### 主要过滤器及其顺序

1. **SecurityContextPersistenceFilter**
   - 位置：过滤器链的最前面
   - 功能：在请求开始时从 SecurityContextRepository 加载 SecurityContext，请求结束时保存回去
   - 作用：确保每个请求都有一个 SecurityContext

2. **UsernamePasswordAuthenticationFilter**
   - 位置：主要认证过滤器
   - 功能：处理表单登录认证
   - 处理路径：默认为 `/login`

3. **BasicAuthenticationFilter**
   - 功能：处理 HTTP Basic 认证
   - 特点：支持基于浏览器的基本认证

4. **ExceptionTranslationFilter**
   - 位置：在 FilterSecurityInterceptor 之前
   - 功能：处理 FilterSecurityInterceptor 抛出的异常
   - 作用：将 Java 异常转换为 HTTP 响应

5. **FilterSecurityInterceptor**
   - 位置：过滤器链的最后
   - 功能：权限校验
   - 作用：保护 Web 资源

#### 过滤器链配置示例

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 添加自定义过滤器
            .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
            // 配置认证
            .formLogin()
                .loginPage("/login")
            // 配置授权
            .authorizeRequests()
                .anyRequest().authenticated();
    }
}
```

#### 自定义过滤器实现

```java
public class CustomFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
        // 在请求处理之前进行处理
        System.out.println("请求处理之前");
        
        // 继续过滤器链
        filterChain.doFilter(request, response);
        
        // 在请求处理之后进行处理
        System.out.println("请求处理之后");
    }
}
```

### 2.2 认证流程

Spring Security 的认证流程是一个复杂的过程，涉及多个组件的协作。

#### 认证流程图

```
┌──────────────┐      ┌─────────────────┐      ┌────────────────┐
│Authentication│      │Authentication    │      │Authentication  │
│Filter        │─────>│Manager          │─────>│Provider        │
└──────────────┘      └─────────────────┘      └────────────────┘
                                                       │
                             ┌───────────────┐         │
                             │UserDetails    │<────────┘
                             │Service        │
                             └───────────────┘
```

#### AuthenticationManager

AuthenticationManager 是认证的核心接口，它只有一个方法：

```java
public interface AuthenticationManager {
    Authentication authenticate(Authentication authentication) 
            throws AuthenticationException;
}
```

最常用的实现是 `ProviderManager`：

```java
@Configuration
public class AuthenticationConfig {
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationProvider... providers) {
        return new ProviderManager(Arrays.asList(providers));
    }
}
```

#### AuthenticationProvider

AuthenticationProvider 负责具体的认证逻辑：

```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication) 
            throws AuthenticationException {
        // 获取用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        // 进行认证逻辑
        if (/* 认证成功 */) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(
                    username, password, authorities);
        }
        
        throw new BadCredentialsException("认证失败");
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```

### 2.3 授权决策

Spring Security 的授权决策涉及三个主要组件：AccessDecisionManager、AccessDecisionVoter 和 ConfigAttribute。

#### AccessDecisionManager

AccessDecisionManager 负责做出最终的授权决定：

```java
public interface AccessDecisionManager {
    void decide(Authentication authentication, Object secureObject,
            Collection<ConfigAttribute> attrs) throws AccessDeniedException;
    boolean supports(ConfigAttribute attribute);
    boolean supports(Class<?> clazz);
}
```

常用实现是 `AffirmativeBased`（一票通过）：

```java
@Configuration
public class AuthorizationConfig {
    
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
                new RoleVoter(),
                new AuthenticatedVoter(),
                new WebExpressionVoter()
        );
        return new AffirmativeBased(decisionVoters);
    }
}
```

#### 投票机制

Spring Security 使用投票机制来做出授权决定：

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
        
        // 进行投票逻辑
        if (/* 同意授权 */) {
            return ACCESS_GRANTED;
        } else if (/* 拒绝授权 */) {
            return ACCESS_DENIED;
        } else {
            return ACCESS_ABSTAIN;
        }
    }
}
```

#### 权限评估过程

1. **收集配置属性**
   - 从 URL 配置
   - 从方法注解
   - 从领域对象

2. **调用投票器**
   - 每个投票器投票
   - 统计投票结果

3. **做出决定**
   - AffirmativeBased：一票通过
   - ConsensusBased：多数通过
   - UnanimousBased：全票通过

```java
@PreAuthorize("hasRole('ADMIN') and hasAuthority('WRITE_PRIVILEGE')")
public void someBusinessMethod() {
    // 业务逻辑
}
```

通过了解 Spring Security 的工作原理，我们可以更好地理解如何配置和扩展它。在下一部分，我们将探讨如何在实际应用中使用这些特性。
