package com.ecomerce.order_service.controller;


import com.ecomerce.order_service.dto.OrderResponseDto;
import com.ecomerce.order_service.dto.ProductDto;
import com.ecomerce.order_service.entity.Order;
import com.ecomerce.order_service.repository.OrderRepository;
import com.ecomerce.order_service.service.GenericServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder builder;

    private final GenericServiceCaller genericServiceCaller;

    public OrderController(GenericServiceCaller genericServiceCaller) {
        this.genericServiceCaller = genericServiceCaller;
    }


    @PostMapping("/placeorder")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody Order order) {
        // Fetch Product Details using Generic Feign Client
        ProductDto productDto = genericServiceCaller.getProductDetails(order.getProductId());

        // Prepare Response DTO
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setProductId(order.getProductId());
        orderResponseDto.setQuantity(order.getQuantity());
        orderResponseDto.setProductName(productDto.getName());
        orderResponseDto.setProductPrice(productDto.getPrice());
        orderResponseDto.setTotalPrice(order.getQuantity() * productDto.getPrice());

        // Save Order to Database
        orderRepository.save(order);
        orderResponseDto.setOrderId(order.getId());

        return ResponseEntity.ok(orderResponseDto);
    }


    @GetMapping
    public List<Order> getAllOrder(){


       return  orderRepository.findAll();
    }
}
