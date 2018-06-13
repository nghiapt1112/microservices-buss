package com.nghia.tut.mss.service.impl;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nghia.tut.mss.domain.Product;
import com.nghia.tut.mss.service.ProductService;
import com.nghia.tut.mss.utils.CompositeAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

@Service
public class ProductServiceImpl extends CompositeAbstractService implements ProductService {
    protected static final Logger LOG = LoggerFactory.getLogger("ProductService_Logger");
    private String PRODUCT_SERVICE = "BUS-PRODUCT";
    private String PRODUCT_DETAIL = "/product/";

    private String DEFAULT_PRODUCT_SERVICE_URL() {
        return this.getGATE_WAY_URL().concat("/product");
    }

    //FIXME: Duplicate code with UserServiceImpl.getUserSchema(...)

    private StringBuilder getProjectURISchema() {
        URI uri = super.getServiceURL(PRODUCT_SERVICE, this.DEFAULT_PRODUCT_SERVICE_URL());
        StringBuilder serviceUrl = new StringBuilder(uri.toString());
//        serviceUrl = new StringBuilder(StringUtils.replaceAll(serviceUrl.toString(), "//", "/"));
        if (serviceUrl.charAt(serviceUrl.length() - 1) == '/') {
            serviceUrl = serviceUrl.deleteCharAt(serviceUrl.length() - 1);
        }
        return serviceUrl;
    }

    private String productWithCode(String pCode) {
        String productURL = this.getProjectURISchema()
                .append("/product") // ProductController
                .append("/").append(pCode) // findProductByCode()
                .toString();
        return productURL;
    }

    @HystrixCommand(fallbackMethod = "defaultProduct")
    public Product findProductByCode(String authKey, String pCode) {
        Map header = ImmutableMap.of(AUTHORIZATION, authKey);
        String uri = "";
        uri = productWithCode(pCode);
        return super.getForObject(uri, header, Product.class);

    }

    public Product defaultProduct(String authKey, String productCode) {
        LOG.error("failed to get project");
        LOG.info("Using fallback method for product-service");
//        ResponseEntity<Product> productResponseEntity = super.badGateWay(Product.class);
//        return productResponseEntity.getBody();
        return new Product().defaultProduct();
    }

}
