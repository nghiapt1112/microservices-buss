package com.nghia.tut.mss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.nghia.tut.mss.controller", "com.nghia.tut.mss.domain", "com.nghia.tut.mss.base"})
public abstract class ApplicationTest {
    // beans will defin here

}
