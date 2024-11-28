# Spring Security Demo

这是一个基于 **《Spring Security 实战教程》** 的学习项目，教程视频地址：  
[点击观看视频](https://www.bilibili.com/video/BV1z44y1j7WZ)

学习笔记：  
[点击查看笔记](https://blog.csdn.net/qq_36437693/article/details/133995888)

---

## 项目简介

本项目包含多个模块，涵盖了 Spring Security 的各个重要功能点，从基础整合到高级功能实现（如 OAuth2 授权登录、动态权限管理、跨域解决方案等）。通过这些模块的学习，可以快速掌握 Spring Security 的核心功能及其在实际开发中的应用。

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

## 资源链接

- 视频教程：[Spring Security 实战教程](https://www.bilibili.com/video/BV1z44y1j7WZ)
- 学习笔记：[点击查看](