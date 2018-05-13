package com.nghia.tut.mss.service.impl;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.service.UserService;
import com.nghia.tut.mss.utils.BaseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private String userWithCode(String uCode) {
        String fallBack = this.getGATE_WAY_URL().concat("/user_path/");
        String resolvedURL = this.getServiceURL("bus-user", fallBack);


        return resolvedURL.concat("user/").concat(uCode);
    }

//    @HystrixCommand(fallbackMethod = "defaultUser")
    public User findByCode(String authKey, String uCode) {
//        Map header = ImmutableMap.of(AUTHORIZATION, authKey);
        return super.getForObject(userWithCode(uCode), null, User.class);
    }

    public User defaultUser(String authKey, String uCode) {
        LOG.warn("Using fallback method for user-service");
        ResponseEntity<User> userResponse = super.badGateWay(User.class);
        return userResponse.getBody();
    }

}
