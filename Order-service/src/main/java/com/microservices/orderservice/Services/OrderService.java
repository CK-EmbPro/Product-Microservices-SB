package com.microservices.orderservice.Services;

import com.microservices.orderservice.DTO.OrderRequestDTO;
import com.microservices.orderservice.Mapper.Mapper;
import com.microservices.orderservice.Model.Order;
import com.microservices.orderservice.Model.OrderLineItems;
import com.microservices.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepo;
    public void placeOrder(OrderRequestDTO orderRequestDTO){
        Order orderRequest = new Order();
        orderRequest.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> lineItems = orderRequestDTO.getOrderLineItemsDTOList().stream().map(Mapper::mapToLineItems).toList();
        orderRequest.setOrderLineItemsList(lineItems);

        orderRepo.save(orderRequest);
    }
}
