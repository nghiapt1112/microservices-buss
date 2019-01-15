package com.nghia.tut.mss.domain.product;

import com.nghia.libraries.mysql.infrustructure.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends AbstractEntity {
    private String productCode;
    private String name;
    private int weight;

    public Product() {}

    public Product(String productCode, String name, int weight) {
        this.productCode = productCode;
        this.name = name;
        this.weight = weight;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
