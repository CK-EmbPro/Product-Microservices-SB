package com.microservices.orderservice.Services;

import com.microservices.orderservice.DTO.InventoryResponse;
import com.microservices.orderservice.DTO.OrderRequestDTO;
import com.microservices.orderservice.Mapper.Mapper;
import com.microservices.orderservice.Model.Order;
import com.microservices.orderservice.Model.OrderLineItems;
import com.microservices.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepo;
    public  WebClient webClient;

    public OrderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequestDTO orderRequestDTO){
        Order orderRequest = new Order();
        orderRequest.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> lineItems = orderRequestDTO.getOrderLineItemsDTOList().stream().map(Mapper::mapToLineItems).toList();
        orderRequest.setOrderLineItemsList(lineItems);

        List<String> skuCodes = lineItems.stream().map(OrderLineItems::getSkuCode).toList();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8083/api/inventory");
        for (String skuCode : skuCodes) {
            uriBuilder.queryParam("skuCode", skuCode);
        }
        InventoryResponse[] inventoryResponses = webClient.get()
                        .uri(uriBuilder.build().toUri())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                            .block();

        boolean allAreInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(allAreInStock){
            orderRepo.save(orderRequest);
        }else {
            throw new IllegalArgumentException("The product is not in stock, please try again later");
        }
    }
}
