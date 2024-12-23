package com.ecomerce.order_service.service;

import com.ecomerce.order_service.dto.ProductDto;
import com.ecomerce.order_service.feign.GenericFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GenericServiceCaller {

    private final GenericFeignClient genericFeignClient;

    public GenericServiceCaller(GenericFeignClient genericFeignClient) {
        this.genericFeignClient = genericFeignClient;
    }

    public ProductDto getProductDetails(Long productId) {
        Map<String, String> params = new HashMap<>();

//        ResponseEntity<ProductDto> productDtoResponseEntity= genericFeignClient.getProduct();



//        return productDtoResponseEntity.getBody();
        return genericFeignClient.callServiceGet(
                "PRODUCT-SERVICE",             // Name of the Product Service
                "product/" + productId,   // Endpoint to fetch product details
                params,
                ProductDto.class               // Specify the response type
        );
    }
}
