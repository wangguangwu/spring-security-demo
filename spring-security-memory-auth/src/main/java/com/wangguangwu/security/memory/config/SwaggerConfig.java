package com.wangguangwu.security.memory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 配置类
 *
 * @author wangguangwu
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Security Memory Auth API")
                        .description("基于内存用户的认证示例")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("wangguangwu")
                                .email("example@example.com")
                                .url("https://github.com/wangguangwu"))
                );
    }
}
