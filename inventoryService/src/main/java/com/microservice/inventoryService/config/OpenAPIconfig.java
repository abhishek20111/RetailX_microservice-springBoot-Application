package com.microservice.inventoryService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIconfig {

    @Bean
    public OpenAPI productServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Inventory Service API")
                        .version("1.0")
                        .description("Product Service API")
                        .license(new License().name("Apache 2.0"))

                );
    }
}