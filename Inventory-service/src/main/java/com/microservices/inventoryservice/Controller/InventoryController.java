package com.microservices.inventoryservice.Controller;

import com.microservices.inventoryservice.DTO.InventoryResponse;
import com.microservices.inventoryservice.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    @Autowired
    public InventoryService inventoryService;
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode){
        List<InventoryResponse> whatInStock = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(whatInStock);
    }
}
