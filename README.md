# Spring Security Demo

这是一个基于 **《Spring Security 实战教程》** 的学习项目，教程视频地址：  
[点击观看视频](https://www.bilibili.com/video/BV1z44y1j7WZ)

学习笔记：  
[点击查看笔记](https://blog.csdn.net/qq_36437693/article/details/133995888)

---

## 项目简介

本项目包含多个模块，涵盖了 Spring Security 的各个重要功能点，从基础整合到高级功能实现（如 OAuth2 授权登录、动态权限管理、跨域解决方案等）。通过这些模块的学习，可以快速掌握 Spring Security 的核心功能及其在实际开发中的应用。

---

## 项目模块

### 基础认证模块

1. **spring-security-memory-auth**
   - 基于内存用户的认证
   - Spring Security 基本配置
   - 默认登录页面使用
   - 基本的授权规则配置

2. **spring-security-db-auth**
   - 基于数据库的用户认证
   - 自定义 UserDetailsService
   - 密码加密与验证
   - 记住我功能实现

3. **spring-security-custom-login**
   - 自定义登录页面
   - 自定义登录成功/失败处理
   - 登录验证码实现
   - Session 管理

### 前后端分离认证

4. **spring-security-jwt**
   - JWT 令牌认证
   - 无状态会话管理
   - Token 刷新机制
   - 跨域配置处理

5. **spring-security-rest**
   - RESTful API 安全配置
   - 动态权限控制
   - 接口访问频率限制
   - 全局异常处理

### OAuth2 认证

6. **spring-security-oauth2-client**
   - 社交登录集成（GitHub、Google）
   - OAuth2 客户端配置
   - 用户信息获取与存储
   - 多账号绑定

7. **spring-security-oauth2-server**
   - OAuth2 授权服务器
   - 客户端管理
   - 授权码与密码模式
   - Token 管理

8. **spring-security-resource-server**
   - 资源服务器实现
   - Token 校验
   - 资源访问控制
   - 权限范围控制

### 高级特性

9. **spring-security-acl**
   - 访问控制列表实现
   - 细粒度权限控制
   - 动态权限规则
   - 权限缓存处理

10. **spring-security-advanced**
    - 多因素认证
    - 会话固定攻击防护
    - CSRF 防护
    - XSS 防护

---

## 模块功能一览

| 模块名称                                 | 功能描述                             |
| ---------------------------------------- | ------------------------------------ |
| `spring-security-01`                     | Spring Security 基础整合             |
| `spring-security-02`                     | 自定义认证数据源                     |
| `spring-security-03web`                  | 完整示例 - 前后端不分离              |
| `spring-security-04split`                | 完整示例 - 前后端分离                |
| `spring-security-05web-verifyCode`       | 添加认证验证码 - 前后端不分离        |
| `spring-security-06split-verifyCode`     | 添加认证验证码 - 前后端分离          |
| `spring-security-07password`             | 密码加密                             |
| `spring-security-08rememberMe`           | RememberMe 功能实现                  |
| `spring-security-09rememberMe-web`       | 传统 Web 开发记住我实现              |
| `spring-security-10rememberMe-split`     | 前后端分离开发记住我实现             |
| `spring-security-11session-management`   | 会话管理                             |
| `spring-security-12csrf-attack`          | 创建恶意应用以验证 CSRF 攻击防御效果 |
| `spring-security-12csrf-blank`           | 开启 CSRF 防御                       |
| `spring-security-13csrf-split`           | 前后端分离场景下的 CSRF 使用         |
| `spring-security-13csrf-web`             | 传统 Web 开发中的 CSRF 使用          |
| `spring-security-14cors`                 | Spring Security 跨域解决方案         |
| `spring-security-15exception-handle`     | 自定义异常处理配置                   |
| `spring-security-16authorize`            | 授权功能                             |
| `spring-security-17dynamic-authorize`    | 数据库动态管理权限规则               |
| `spring-security-18oauth-client-github`  | GitHub 授权登录                      |
| `spring-security-19authorization-server` | 授权服务器搭建                       |
| `spring-security-20resource-server`      | 资源服务器搭建                       |

---

## 技术栈

- Spring Boot 3.4.0
- Spring Security
- Java 17
- Maven
- Docker (用于运行依赖服务)

## 环境要求

- JDK 17 或更高版本
- Maven 3.6+
- Docker 和 Docker Compose (可选，用于运行依赖服务)
- IDE 推荐使用 IntelliJ IDEA

---

## 快速开始

1. 克隆仓库：

   ```bash
   git clone https://github.com/your-repository/spring-security-demo.git
   ```

2. 导入到 IDE（如 IntelliJ IDEA）。
3. 运行 build 目录下的 docker-compose.yaml
   ```
   docker compose up -d
   ```
4. 按需切换到对应模块，运行代码。 
5. 根据模块的功能介绍和对应章节学习，实现功能验证。

---

## 贡献指南

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的改动 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建一个 Pull Request

## 问题反馈

如果你在使用过程中遇到任何问题，欢迎：

1. 提交 Issue
2. 在 CSDN 博客下方评论
3. 在视频评论区留言

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

---

## 资源链接

- 视频教程：[Spring Security 实战教程](https://www.bilibili.com/video/BV1z44y1j7WZ)
- 学习笔记：[点击查看](https://blog.csdn.net/qq_36437693/article/details/133995888)