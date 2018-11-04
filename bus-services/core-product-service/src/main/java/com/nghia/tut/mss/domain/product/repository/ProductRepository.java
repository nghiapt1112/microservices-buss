package com.nghia.tut.mss.domain.product.repository;

import com.nghia.libraries.mysql.infrustructure.domain.AbstractEntity;
import com.nghia.tut.mss.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository<E extends AbstractEntity, ID> extends JpaRepository<E, ID> {

    List<Product> findByName(String name);
}
