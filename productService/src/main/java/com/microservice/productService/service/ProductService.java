package com.microservice.productService.service;

import com.microservice.productService.dto.ProductRequest;
import com.microservice.productService.dto.ProductResponse;
import com.microservice.productService.model.Product;
import com.microservice.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        System.out.println(productRequest);
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.desciption())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created Successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product-> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
