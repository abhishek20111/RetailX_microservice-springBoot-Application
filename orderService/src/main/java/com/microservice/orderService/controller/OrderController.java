package com.microservice.orderService.controller;

import com.microservice.orderService.dto.OrderRequest;
import com.microservice.orderService.model.Order;
import com.microservice.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
//        System.out.println("1 "+ orderRequest);
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order Deleted Successfully";
    }

}
