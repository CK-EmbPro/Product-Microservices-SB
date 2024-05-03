package com.microservices.orderservice.Controller;

import com.microservices.orderservice.DTO.OrderRequestDTO;
import com.microservices.orderservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    public OrderService orderService;
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO orderRequest){
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("Order placed successfully",HttpStatus.CREATED);
    }

}
