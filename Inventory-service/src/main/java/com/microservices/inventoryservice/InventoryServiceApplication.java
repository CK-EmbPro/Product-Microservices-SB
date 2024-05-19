package com.microservices.inventoryservice;
import com.microservices.inventoryservice.Model.Inventory;
import com.microservices.inventoryservice.Repository.InventoryRepository;
import com.microservices.inventoryservice.Services.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepo){
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("iphone-12");
            inventory.setQuantity(100);

            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("iphone-14");
            inventory1.setQuantity(200);

            inventoryRepo.save(inventory);
            inventoryRepo.save(inventory1);
        };
    }

}
