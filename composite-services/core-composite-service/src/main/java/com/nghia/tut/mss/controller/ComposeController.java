package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.CartDetail;
import com.nghia.tut.mss.service.ComposeService;
import com.nghia.tut.mss.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/composite")
public class ComposeController extends BaseController {

    @Autowired
    private ComposeService composeService;

    @RequestMapping("/findBy")
    public CartDetail getProduct(HttpServletRequest httpServletRequest, @RequestParam String userCode) {

        String authKey = httpServletRequest.getHeader("Authorization");
        composeService.addAuthKey(authKey); // remove this line.
        return composeService.getCartDetail(authKey, userCode, "productCode" + userCode);
    }

}
