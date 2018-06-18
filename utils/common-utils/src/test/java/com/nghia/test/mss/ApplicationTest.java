package com.nghia.test.mss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.nghia.libraries.commons.mss"})
public abstract class ApplicationTest {
    // beans will be defined here

}
