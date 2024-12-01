package com.wangguangwu.security.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于内存用户的认证示例应用
 *
 * @author wangguangwu
 */
@SpringBootApplication
public class MemoryAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryAuthApplication.class, args);
    }
}
