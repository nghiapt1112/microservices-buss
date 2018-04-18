package com.nghia.tut.mss.domain.service;

import com.nghia.tut.mss.domain.Cart;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    public Cart findCartForUser(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            // TODO: wrap into custom exception;
            throw new RuntimeException(String.format("userCode is invalid, \ninput value:%s", userCode));
        }
        return new Cart(userCode).initTest();
    }
}
