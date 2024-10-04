package com.blogrestapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//http://localhost:8080/swagger-ui/index.html
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info()
        .title("BLOG API")
        .version("1.0")
        .description("This is a blog applicaiton REST API using spring boot"));
    }
}
