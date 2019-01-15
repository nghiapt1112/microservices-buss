package com.nghia.tut.mss.domain.product.repository.impl;

import com.nghia.tut.mss.domain.product.Product;
import com.nghia.tut.mss.domain.product.repository.ProductRepository;
import com.nghia.tut.mss.infrastructure.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl extends AbstractRepository<Product, Long> implements ProductRepository<Product, Long> {

    public ProductRepositoryImpl(EntityManager em) {
        super(Product.class, em);
    }

    public List<Product> findByName(String name) {
        StringBuilder hql = new StringBuilder(" SELECT DISTINCT p FROM Product p  where p.name = :name");

        Query query = entityManager.createQuery(hql.toString(), Product.class);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.keySet().forEach(p -> query.setParameter(p, params.get(p)));

        return query.getResultList();
    }
}
