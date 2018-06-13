package com.nghia.libraries.commons.mss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nghia.tut.controller", "com.nghia.tut.domain", "com.nghia.tut.infrastructure"})
public abstract class ApplicationTest {
    // beans will defin here

}
