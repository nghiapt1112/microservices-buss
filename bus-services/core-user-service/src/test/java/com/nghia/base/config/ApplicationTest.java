package com.nghia.base.config;

import com.nghia.service.test.mail.EmailServiceNullInfo;
import com.nghia.service.test.mail.EmailServiceSuccess;
import com.nghia.service.test.mail.EmailServiceWrongTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <b>Beans will be defined here</b>
 */
@Configuration
@ComponentScan(basePackages = {
        "com.nghia.tut.controller",
        "com.nghia.tut.domain",
        "com.nghia.tut.infrastructure"
})
public abstract class ApplicationTest {


}
