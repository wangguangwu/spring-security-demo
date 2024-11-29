package com.wangguangwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangguangwu
 */
@Controller
public class LoginController {

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

}
