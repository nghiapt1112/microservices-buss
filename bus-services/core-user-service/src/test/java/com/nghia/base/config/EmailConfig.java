package com.nghia.base.config;

import com.nghia.service.test.mail.EmailServiceNullInfo;
import com.nghia.service.test.mail.EmailServiceSuccess;
import com.nghia.service.test.mail.EmailServiceWrongTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackages = {"com.nghia.libraries.email"})
public class EmailConfig {

    @Bean
    public EmailServiceWrongTemplate emailSenderTest() {
        return new EmailServiceWrongTemplate();
    }

    @Bean
    public EmailServiceNullInfo emailServiceNullInfo() {
        return new EmailServiceNullInfo();
    }

    @Bean
    public EmailServiceSuccess emailServiceSuccess() {
        return new EmailServiceSuccess();
    }
}
