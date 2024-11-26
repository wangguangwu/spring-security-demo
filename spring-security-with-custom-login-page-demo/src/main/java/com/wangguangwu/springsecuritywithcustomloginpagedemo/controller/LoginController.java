package com.wangguangwu.springsecuritywithcustomloginpagedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * @author wangguangwu
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "用户名或密码错误！");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "您已成功注销！");
        }
        // 渲染 login.html 页面
        return "login";
    }

    @GetMapping("/index")
    public String indexPage(Principal principal, Model model) {
        // 获取当前登录用户的用户名
        model.addAttribute("username", principal.getName());
        // 渲染 index.html 页面
        return "index";
    }
}
