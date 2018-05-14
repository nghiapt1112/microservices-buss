package com.nghia.tut.mss.service.impl;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nghia.libraries.commons.mss.infrustructure.service.AbstractServiceImpl;
import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.service.ProductService;
import com.nghia.tut.mss.utils.BaseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService {
    String PRODUCT_SERVICE = "/product";
    String PRODUCT_DETAIL = "/product/";

    private String productWithCode(String pCode) {
        return this.getGATE_WAY_URL() + PRODUCT_SERVICE.concat(PRODUCT_DETAIL).concat(pCode);
    }

    @HystrixCommand(fallbackMethod = "defaultProduct")
    public Product findByCode(String authKey, String pCode) {
        Map header = ImmutableMap.of(AUTHORIZATION, authKey);
        try {
            super.getForObject(productWithCode(pCode), header, Product.class);
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n===================================");
            e.printStackTrace();
            System.out.println("\n\n\n\n\n\n\n\n===================================");
        }
        return super.getForObject(productWithCode(pCode), header, Product.class);
    }

    public Product defaultProduct(String authKey, String productCode) {
        LOG.warn("Using fallback method for product-service");
        ResponseEntity<Product> productResponseEntity = super.badGateWay(Product.class);
        return productResponseEntity.getBody();
    }

}
