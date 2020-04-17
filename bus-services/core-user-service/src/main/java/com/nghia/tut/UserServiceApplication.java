package com.nghia.tut;

//import com.nghia.libraries.email.infrustructure.mail.EmailAutoConfiguraiton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableDiscoveryClient
//@Import(EmailAutoConfiguraiton.class)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
