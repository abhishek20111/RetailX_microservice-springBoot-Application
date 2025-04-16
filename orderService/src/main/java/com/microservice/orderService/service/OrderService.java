package com.microservice.orderService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.orderService.client.InventoryClient;
import com.microservice.orderService.dto.OrderRequest;
import com.microservice.orderService.event.OrderPlaceEvent;
import com.microservice.orderService.model.Order;
import com.microservice.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, String> kafkaTemplate; // Use KafkaTemplate<String, String>

    public String placeOrder(OrderRequest orderRequest) {
        System.out.println(orderRequest);
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        System.out.println(isProductInStock + " " + orderRequest.skuCode() + " " + orderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);

            OrderPlaceEvent orderPlaceEvent = new OrderPlaceEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            String orderPlaceEventJson = serializeToJson(orderPlaceEvent); // Serialize to JSON
            System.out.println("Start- Sending OrderPlacedEvent {} to Kafka Topic " + orderPlaceEventJson);
            kafkaTemplate.send("order-placed", orderPlaceEventJson); // Send JSON string
            System.out.println("End- Sending OrderPlacedEvent {} to Kafka Topic " + orderPlaceEventJson);

            return "Order Placed Successfully";
        } else {
            return "Sorry, product " + orderRequest.skuCode() + " is out of stock!";
        }
    }

    private String serializeToJson(OrderPlaceEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize OrderPlaceEvent to JSON", e);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
