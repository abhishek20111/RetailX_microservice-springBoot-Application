package com.microservice.inventoryService.service;

import com.microservice.inventoryService.model.InventoryModel;
import com.microservice.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
//        boolean ans =  inventoryRepository.existsBySkuCodeAndQuantityIsLessThanEqual(skuCode, quantity);
        if (skuCode.equalsIgnoreCase("IPhone 15")) {
            skuCode = "iphone_15";
        } else {
            skuCode = skuCode.toLowerCase();
        }
        InventoryModel inventory = inventoryRepository.findBySkuCode(skuCode.toLowerCase());
        System.out.println(inventory +" "+quantity+" "+skuCode.toLowerCase());
        // If inventory is found and quantity is sufficient, return true
        if (inventory != null && inventory.getQuantity() >= quantity) {
            System.out.println(inventory +" "+quantity+" "+skuCode+" L "+inventory.getQuantity());
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory); // Save the updated inventory back to the database
            return true;
        } else {
            // If no inventory is found or insufficient quantity, return false
            return false;
        }

    }
}
