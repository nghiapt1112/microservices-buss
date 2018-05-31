package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.CartDetail;
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
    public CartDetail getProduct(HttpServletRequest httpServletRequest, @RequestParam String userCode) {
        System.out.println("\n====================\nFinding composite info");
        System.out.println("service-id:\t" + env.getProperty("spring.application.name"));
        String authKey = httpServletRequest.getHeader("Authorization");
//        composeService.addAuthKey(authKey); // remove this line.
        authKey = authKey == null ? "" : authKey;
        return composeService.getCartDetail(authKey, userCode, "productCode" + userCode);
//        return new CartDetail().testData();
    }


    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String getEnv(@RequestParam String envVar) {
        CONTROLLER_LOGGER.info("Requesting envVar {} with value: {}", envVar, env.getProperty(envVar));
        return env.getProperty(envVar);

    }
}
