package com.wangguangwu.springsecurity02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangguangwu
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout.html")
    public String logout() {
        return "logout";
    }

}
