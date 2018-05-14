package com.nghia.libraries.commons.mss.domain.service;

import com.nghia.libraries.commons.mss.domain.Product;
import com.nghia.tut.mss.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class ProductService {

    /**
     * Sample usage: curl $HOST:$PORT/product/1
     */
    public Product getProduct(String productCode) {
        return new Product(productCode, "name__".concat(productCode), 123);
    }
}
