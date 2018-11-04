package com.nghia.tut.mss.domain.product.service;

import com.nghia.tut.mss.domain.product.Product;
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
