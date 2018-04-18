package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.Cart;
import com.nghia.tut.mss.domain.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/{userCode}")
    public Cart getProduct(@PathVariable String userCode) {
        return shoppingCartService.findCartForUser(userCode);
    }

}
