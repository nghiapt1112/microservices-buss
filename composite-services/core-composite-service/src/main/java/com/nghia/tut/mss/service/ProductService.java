package com.nghia.tut.mss.service;

import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.utils.BaseService;

public interface ProductService extends BaseService {

    Product findByCode(String authKey, String pCode);
    Product defaultProduct(String authKey, String productCode);

}
