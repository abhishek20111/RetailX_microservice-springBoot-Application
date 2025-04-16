package com.microservice.productService.dto;

import com.microservice.productService.model.Product;

import java.math.BigDecimal;

public record ProductRequest (String id, String name, String desciption, BigDecimal price){


}
