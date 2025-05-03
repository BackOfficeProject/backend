package com.backoffice.backoffice.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("backoffice API")
                .version("1.0.0")
                .description("HR 기업 내부용 인사관리 백오피스 시스템 API"));
    }
}
