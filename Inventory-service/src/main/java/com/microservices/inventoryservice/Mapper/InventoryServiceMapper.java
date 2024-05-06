package com.microservices.inventoryservice.Mapper;

import com.microservices.inventoryservice.DTO.InventoryResponse;
import com.microservices.inventoryservice.Model.Inventory;

public class InventoryServiceMapper {
    static public InventoryResponse inventoryToInventoryResp(Inventory inventory){
       InventoryResponse inventResponse = InventoryResponse.builder()
               .skuCode(inventory.getSkuCode())
               .isInStock(inventory.getQuantity() > 0)
               .build();

       return inventResponse;

    }

}
