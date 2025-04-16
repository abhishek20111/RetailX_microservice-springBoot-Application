package com.microservice.inventoryService.repository;

import com.microservice.inventoryService.model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

    boolean existsBySkuCodeAndQuantityIsLessThanEqual(String skuCode, Integer quantity);

    InventoryModel findBySkuCode(String skuCode);
}
