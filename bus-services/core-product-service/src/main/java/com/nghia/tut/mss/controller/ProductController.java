package com.nghia.tut.mss.controller;

import com.nghia.tut.mss.domain.product.Product;
import com.nghia.tut.mss.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/{productCode}")
    public Product getProduct(@PathVariable String productCode) {

        System.out.println("Requesting product with : " + productCode);
        Product product = productService.getProduct(productCode);
        System.out.println("Response product  : " + product);
        return product;
    }

}
