package com.wangguangwu.springsecuritysimpledemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangguangwu
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String login() {
        return "Hello World!";
    }

}
