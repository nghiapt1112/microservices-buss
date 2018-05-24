package com.nghia.tut.domain.user.service;

import com.nghia.tut.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    /**
     * Sample usage: curl $HOST:$PORT/product/1
     */
    public Product getProduct(String productCode) {
        return new Product(productCode, "name__".concat(productCode), 123);
    }
}
