# Spring Security 自定义登录页面示例

此项目演示如何通过 Spring Security 实现自定义登录页面，并完成基础用户认证功能。

## 项目结构

```
└── src
    └── main
        ├── java
        │   └── com
        │       └── wangguangwu
        │           └── springsecuritywithcustomloginpagedemo
        │               ├── SpringSecurityWithCustomLoginPageDemoApplication.java  # 项目主启动类
        │               ├── config
        │               │   └── SecurityConfig.java                               # Spring Security 配置类
        │               ├── controller
        │               │   └── LoginController.java                              # 控制器类，处理登录和主页请求
        │               └── service
        │                   └── CustomUserDetailsService.java                     # 自定义用户认证逻辑
        └── resources
            ├── application.yaml                                                  # Spring Boot 配置文件
            └── templates
                ├── index.html                                                    # 自定义主页
                └── login.html                                                    # 自定义登录页面
```

## 功能特点

- **自定义登录页面**：提供用户友好的登录页面，路径为 `/admin/login`。
- **Spring Security 集成**：安全保护所有请求，登录页面和静态资源除外。
- **自定义用户认证**：使用 `UserDetailsService` 接口实现用户认证逻辑。
- **基础用户设置**：
    - 默认账号：`admin`
    - 默认密码：`123456`

## 快速开始

### 运行前置条件

- Java 17 或更高版本
- Maven 3.6 或更高版本
- 开发工具（例如 IntelliJ IDEA 或 Eclipse）

## 使用方法

1. 在浏览器中访问 `http://localhost:8081/admin/login` 页面。
2. 输入以下默认账号信息：
    - 账号：`admin`
    - 密码：`123456`
3. 点击登录。
4. 登录成功后，跳转到主页 `/index`。

## 如何自定义

### 1. 修改默认账号和密码

在 `CustomUserDetailsService` 中更改默认账号和密码：

```java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!"你的用户名".equals(username)) {
        throw new UsernameNotFoundException("用户不存在");
    }
    return User.withUsername("你的用户名")
            .password(passwordEncoder.encode("你的密码"))
            .roles("ADMIN")
            .build();
}
```

### 2. 修改登录页面样式

编辑 `src/main/resources/templates/login.html` 文件，调整页面布局和样式。

### 3. 从数据库加载用户

可以扩展 `CustomUserDetailsService`，将用户数据从数据库中加载，替换模拟的用户认证逻辑。

## 项目接口说明

| 接口路径       | 描述     | 访问控制   |
| -------------- | -------- | ---------- |
| `/admin/login` | 登录页面 | 公共访问   |
| `/index`       | 主页     | 需登录认证 |
| `/logout`      | 注销接口 | 需登录认证 |

## 技术细节

1. **Spring Security 配置**：
    - 自定义登录页面和登录处理地址。
    - 配置认证成功和失败后的跳转逻辑。
    - 自定义用户认证服务。
2. **前端模板**：
    - 使用 Thymeleaf 模板引擎渲染登录页面和主页。
    - 简洁美观的页面设计，支持快速调整样式。

## 常见问题

1. **登录失败怎么办？**
    - 请检查输入的账号和密码是否正确。
    - 默认账号为 `admin`，密码为 `123456`。
    - 如果已更改账号密码，请根据最新的设置重新输入。

2. **如何查看登录状态？**
    - 登录成功后，会跳转到 `/index`，并显示当前登录用户的信息。