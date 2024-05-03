package com.microservices.orderservice.Mapper;

import com.microservices.orderservice.DTO.OrderLineItemsDTO;
import com.microservices.orderservice.Model.OrderLineItems;

public class Mapper {
    static public OrderLineItems mapToLineItems(OrderLineItemsDTO lineItemsDto){
        OrderLineItems lineItem = new OrderLineItems();
        lineItem.setQuantity(lineItemsDto.getQuantity());
        lineItem.setSkuCode(lineItemsDto.getSkuCode());
        lineItem.setPrice(lineItemsDto.getPrice());

        return lineItem;
    }
}
