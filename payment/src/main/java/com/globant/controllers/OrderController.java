package com.globant.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import domain.payment.CreatedOrder;
import domain.payment.CreateOrder;
import domain.payment.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<CreatedOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Order> getOrder(@PathVariable @UUID String uuid) {
        return ResponseEntity.ok(orderService.getOrder(java.util.UUID.fromString(uuid)));
    }

    @PostMapping
    public ResponseEntity<CreatedOrder>createOrder(@RequestBody @Valid CreateOrder createOrder){
        log.info(STR."Inminent order: \{createOrder.toString()}");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(createOrder));
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<CreatedOrder> markAsDelivered(
            @PathVariable @UUID @NotNull String uuid,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String timestamp
    ){
        return ResponseEntity.ok(
                orderService.setDelivery(
                        java.util.UUID.fromString(uuid),
                        LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(timestamp))
                )
        );
    }
}