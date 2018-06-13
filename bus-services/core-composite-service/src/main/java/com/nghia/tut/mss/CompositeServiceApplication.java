package com.nghia.tut.mss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableHystrix
@EnableSwagger2
@ComponentScan(basePackages= {"com.nghia.libraries.commons.mss", "com.nghia.tut.mss"})

public class CompositeServiceApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CompositeServiceApplication.class);

    public static void main(String[] args) {

        System.out.println();
        LOG.info("\n\n===========================BOOTING APPLICATION=========================\n\n\n");
        ConfigurableApplicationContext ctx = SpringApplication.run(CompositeServiceApplication.class, args);
        System.out.println("\n\n===========================Application STARTED==============================\n\n\n");
    }

    @Bean
    public RestTemplate loadBalancer() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Docket  swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .pathMapping("/")
                .apiInfo(ApiInfo.DEFAULT);
    }
}
