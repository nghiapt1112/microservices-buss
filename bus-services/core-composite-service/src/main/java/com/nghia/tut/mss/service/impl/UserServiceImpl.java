package com.nghia.tut.mss.service.impl;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.service.UserService;
import com.nghia.tut.mss.utils.CompositeAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

@Service
public class UserServiceImpl extends CompositeAbstractService implements UserService {
    protected static final Logger LOG = LoggerFactory.getLogger("UserService_Logger");
    private final String USER_SERVICE_ID = "BUS-USER";


    private String DEFAULT_USER_SERVICE_URI() {
        return this.getGATE_WAY_URL().concat("/user_path/user/");
    }


    private StringBuilder getUserSchema() {
        URI uri = super.getServiceURL(USER_SERVICE_ID, DEFAULT_USER_SERVICE_URI());
        StringBuilder serviceUrl = new StringBuilder(uri.toString());
        if (serviceUrl.lastIndexOf("/") != serviceUrl.length() - 1) { // last character not equal with /
            serviceUrl = serviceUrl.append("/");
        }
        return serviceUrl;
    }

    private String userWithCode(String uCode) {
        return this.getUserSchema()
                .append("user") // UserController
                .append("/").append(uCode) // findUserByCode()
                .toString();
    }

    @HystrixCommand(fallbackMethod = "defaultUser")
    public User findUserByCode(String authKey, String uCode) {
        Map header = ImmutableMap.of(AUTHORIZATION, authKey == null ? "" : authKey);
        return super.getForObject(userWithCode(uCode), header, User.class);
    }

    public User defaultUser(String authKey, String uCode) {
        LOG.warn("Using fallback method for user-service");
//        ResponseEntity<User> userResponse = super.badGateWay(User.class);
//        return userResponse.getBody();
        return null;
    }

}
