package com.microservice.inventoryService;


import com.microservice.inventoryService.model.InventoryModel;
import com.microservice.inventoryService.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Test
	void contextLoads() {
		// Ensure the application context loads correctly.
	}

	@Test
	void testIsInStock() {
		// Insert data directly into the repository before testing.
		InventoryModel inventoryItem = new InventoryModel(0, "iphone", 10000);
		inventoryRepository.save(inventoryItem);

		// Make a GET request to check the stock
		ResponseEntity<Boolean> response = restTemplate.getForEntity(
				"/api/inventory?skuCode=iphone&quantity=5000", Boolean.class);

		// Assert that the response is successful and the item is in stock.
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(true, response.getBody());
	}

	@Test
	void testIsOutOfStock() {
		// Insert data directly into the repository before testing.
		InventoryModel inventoryItem = new InventoryModel(0, "nokia", 100);
		inventoryRepository.save(inventoryItem);

		// Make a GET request to check if the stock is available for a higher quantity
		ResponseEntity<Boolean> response = restTemplate.getForEntity(
				"/api/inventory?skuCode=nokia&quantity=200", Boolean.class);

		// Assert that the response is successful and the item is out of stock.
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(false, response.getBody());
	}
}
