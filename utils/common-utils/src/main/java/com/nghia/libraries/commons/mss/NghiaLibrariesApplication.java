package com.nghia.libraries.commons.mss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NghiaLibrariesApplication {
    @Autowired
    protected Environment env;

    public static void main(String[] args) {
        SpringApplication.run(NghiaLibrariesApplication.class, args);
    }

}
