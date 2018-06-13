package com.nghia.tut.mss.service.impl;

import com.nghia.tut.mss.domain.CartDetail;
import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.domain.User;
import com.nghia.tut.mss.service.ComposeService;
import com.nghia.tut.mss.service.ProductService;
import com.nghia.tut.mss.service.UserService;
import com.nghia.tut.mss.utils.CompositeAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComposeServiceImpl extends CompositeAbstractService implements ComposeService {
    protected static final Logger LOG = LoggerFactory.getLogger("ComposeService_Logger");
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public CartDetail getCartDetail(String authKey, String userCode, String productCode) {
        LOG.info("requesting user ...................... with : {}", userCode);
        LOG.info("requesting product ...................... with : {}", productCode);

        User userInfo = userService.findUserByCode(authKey, userCode);
        LOG.info("user after parse: {}", userInfo);

        Product productInfo = productService.findProductByCode(authKey, productCode);
        LOG.info("product after parse: {}", productInfo);

        CartDetail cartDetail = new CartDetail().setProduct(productInfo).setUserOwner(userInfo).setUserCode(userCode);
        LOG.info("cart Detail after call 2 services: {}\n", cartDetail);
        return cartDetail;
    }


}
