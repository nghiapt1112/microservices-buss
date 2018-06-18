package com.nghia.test.mss;

import com.nghia.test.mss.EmailTest.CommonSenderTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.nghia.libraries.email"})
@PropertySource(value = {"classpath:email.properties", "classpath:error.info.properties"})
public abstract class ApplicationTest {
    // beans will defin here

    @Bean
    public CommonSenderTest commonSenderTest(){
        return new CommonSenderTest();
    }
}
