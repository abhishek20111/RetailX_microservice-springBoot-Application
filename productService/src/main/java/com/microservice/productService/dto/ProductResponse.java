package com.microservice.productService.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String desciption, BigDecimal price) {

}
