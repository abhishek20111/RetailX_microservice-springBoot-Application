package com.microservice.productService.controller;

import com.microservice.productService.dto.ProductRequest;
import com.microservice.productService.dto.ProductResponse;
import com.microservice.productService.model.Product;
import com.microservice.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){

        System.out.println("productService.getAllProducts()");
        return productService.getAllProducts();

    }
}
