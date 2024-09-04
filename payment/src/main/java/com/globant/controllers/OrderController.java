package com.globant.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.globant.dto.OrderDto;
import com.globant.model.CreateOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.globant.model.entity.Order;
import com.globant.service.OrderService;

@RestController
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDto>createOrder(@RequestBody CreateOrder createOrder){
        log.debug(createOrder.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(createOrder));
    }


}