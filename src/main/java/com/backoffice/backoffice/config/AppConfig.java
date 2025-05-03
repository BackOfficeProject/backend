package com.backoffice.backoffice.config;

import com.backoffice.backoffice.util.PhoneNumberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PhoneNumberService phoneNumberService() {
        return new PhoneNumberService();
    }
}
