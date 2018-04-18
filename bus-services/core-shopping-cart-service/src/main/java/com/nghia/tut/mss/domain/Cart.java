package com.nghia.tut.mss.domain;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Cart {
    private String cartId;
    private String cartName;
    private String userCode;
    private List<Item> cartItems;

    public Cart(String userCode) {
        this.userCode = userCode;
    }

    public Cart() {
    }

    public String getCartId() {
        return cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public String getUserCode() {
        return userCode;
    }

    public Cart initTest() {
        Cart cartInfo = new Cart();
        cartInfo.cartId = UUID.randomUUID().toString();
        cartInfo.cartName = this.userCode.concat("'s cart.");
        cartInfo.cartItems = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(el -> new Item(null, el))
                .collect(Collectors.toList());
        return cartInfo;
    }
}
