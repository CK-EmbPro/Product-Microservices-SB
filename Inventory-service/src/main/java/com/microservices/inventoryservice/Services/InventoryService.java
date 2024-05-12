package com.microservices.inventoryservice.Services;

import com.microservices.inventoryservice.DTO.InventoryResponse;
import com.microservices.inventoryservice.Mapper.InventoryServiceMapper;
import com.microservices.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    public InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes){
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream().map(InventoryServiceMapper::inventoryToInventoryResp).toList();
    }
}
