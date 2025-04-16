package com.microservice.inventoryService;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestcontainersConfiguration {

	@Container
	public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.3.0")
			.withDatabaseName("inventory_service")
			.withUsername("root")
			.withPassword("mysql");

	// Optional: You can set up additional configuration if needed, such as a DataSource for your tests.
}
