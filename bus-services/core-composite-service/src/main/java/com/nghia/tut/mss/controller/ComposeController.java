package com.nghia.tut.mss.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nghia.tut.mss.domain.CartDetail;
import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.service.ComposeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/composite")
@RefreshScope
public class ComposeController {
    protected static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger(ComposeController.class);

    @Autowired
    private Environment env;
    @Autowired
    private ComposeService composeService;

    @RequestMapping(value = "/findBy", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultGetProduct")
    public CartDetail getProduct(HttpServletRequest httpServletRequest, @RequestParam String userCode) {
        CONTROLLER_LOGGER.info("\n====================\nFinding composite info");
        CONTROLLER_LOGGER.info("service-id:\t" + env.getProperty("spring.application.name"));

        String authKey = httpServletRequest.getHeader("Authorization");

        authKey = authKey == null ? "" : authKey;
        return composeService.getCartDetail(authKey, userCode, "productCode" + userCode);
    }

    public CartDetail defaultGetProduct(HttpServletRequest httpServletRequest, @RequestParam String userCode) {
        return new CartDetail()
                .setProduct(new Product().defaultProduct())
                .setUserCode(userCode)
                .setUserOwner(null); //TODO: create default user data.
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String getEnv(@RequestParam String envVar) {
        CONTROLLER_LOGGER.info("Requesting envVar {} with value: {}", envVar, env.getProperty(envVar));
        return env.getProperty(envVar);

    }
}
