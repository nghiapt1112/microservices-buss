package com.nghia.tut.mss.domain;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDetail extends BaseDomain {
    private String userCode;
    private User userOwner;
    private List<Product> products;

    public CartDetail setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public CartDetail setUserOwner(User userOwner) {
        if (Objects.isNull(userOwner)) {
            return this;
        }
        this.userOwner = userOwner;
        return this;
    }

    public CartDetail setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public CartDetail setProduct(Product product) {
        if (CollectionUtils.isEmpty(this.products)) {
            this.products = new ArrayList<>();
        }
        if (!Objects.isNull(product)) {
            this.products.add(product);
        }
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public List<Product> getProducts() {
        return products;
    }

}
