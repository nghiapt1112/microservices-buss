package com.nghia.tut.mss.domain;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;

public class Product extends AbstractObject {
    private int productId;
    private String name;
    private int weight;

    public Product() {
    }

    public Product(int productId, String name, int weight) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
    }

    public Product defaultProduct() {
        this.productId = -1;
        this.name = "Fallback Project";
        this.weight = -1;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
