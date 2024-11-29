package com.wangguangwu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangguangwu
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        System.out.println("hello index");
        return "hello index";
    }
}
