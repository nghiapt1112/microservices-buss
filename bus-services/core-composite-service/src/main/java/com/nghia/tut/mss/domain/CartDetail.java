package com.nghia.tut.mss.domain;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class CartDetail  extends AbstractObject {
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

    public CartDetail testData(){
        this.userCode = UUID.randomUUID().toString();
        this.userOwner = new User().testData();
        this.products = Collections.emptyList();
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
