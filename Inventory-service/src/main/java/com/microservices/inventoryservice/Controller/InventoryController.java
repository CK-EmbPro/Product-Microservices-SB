package com.microservices.inventoryservice.Controller;

import com.microservices.inventoryservice.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    @Autowired
    public InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCode){
        boolean isInTheStock = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(isInTheStock);
    }
}
