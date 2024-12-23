package com.ecomerce.product_service.feign;

import com.ecomerce.product_service.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product/{productId}")
    Product getProductDetails(@PathVariable("productId") Long productId);
}
