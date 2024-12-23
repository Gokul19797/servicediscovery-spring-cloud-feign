package com.ecomerce.order_service.feign;


import com.ecomerce.order_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "generic-client", url = "${gateway.url}") // Use the API Gateway URL
public interface GenericFeignClient {

    @GetMapping(value = "/{serviceName}/{endpoint}")
    @ResponseBody <T> T  callServiceGet(
            @PathVariable("serviceName") String serviceName,
            @PathVariable("endpoint") String endpoint,
            @RequestParam Map<String, String> params,
            Class<T> responseType
    );

    @GetMapping("/PRODUCT-SERVICE/product/6")
     ResponseEntity<ProductDto>   getProduct();



    @RequestMapping(method = RequestMethod.POST, value = "/{serviceName}/{endpoint}")
    String callServicePost(
            @PathVariable("serviceName") String serviceName,
            @PathVariable("endpoint") String endpoint,
            @RequestBody Object body
    );


}
