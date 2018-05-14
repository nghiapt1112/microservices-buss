package com.nghia.tut.mss.service.impl;

import com.nghia.tut.mss.domain.CartDetail;
import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.domain.User;
import com.nghia.libraries.commons.mss.infrustructure.service.AbstractServiceImpl;
import com.nghia.tut.mss.service.ComposeService;
import com.nghia.tut.mss.service.ProductService;
import com.nghia.tut.mss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComposeServiceImpl extends AbstractServiceImpl implements ComposeService {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public CartDetail getCartDetail(String  authKey, String userCode, String productCode) {
        LOG.warn("requesting ...................... with : {}", userCode);
        LOG.warn("requesting ...................... with : {}", productCode);

        Product productInfo = productService.findByCode(authKey, productCode);
        LOG.warn("product after parse: {}", productInfo);

        User userInfo = userService.findByCode(authKey, userCode);
        LOG.warn("user after parse: {}", userInfo);

        CartDetail cartDetail = new CartDetail().setProduct(productInfo).setUserOwner(userInfo).setUserCode(userCode);
        return cartDetail;
    }


}
