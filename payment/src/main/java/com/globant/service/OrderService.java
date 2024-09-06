package com.globant.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.globant.dto.OrderDto;
import com.globant.dto.delivery.Pending;
import com.globant.mapper.OrderMapper;
import com.globant.model.CreateOrder;
import com.globant.model.PersistOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globant.model.entity.Order;
import com.globant.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;



    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;

        this.orderMapper = orderMapper;
    }

    public List<Order> getAll (){
        return orderRepository.getAllOrders();
    }
    public OrderDto createOrder(CreateOrder createOrder){
        var order = new PersistOrder(
                UUID.randomUUID(),
                createOrder.documentNumber(),
                LocalDateTime.now(),
                createOrder.extraInformation(),
                createOrder.cart().total()
        );

        var createdOrder = orderRepository.save(orderMapper.fromDto(order));
        return new OrderDto(
                createdOrder.getUuid(),
                createdOrder.getCreationTime(),
                createdOrder.getDocumentNumber(),
                createOrder.cart(),
                createdOrder.getExtraInformation(),
                createOrder.cart().total() * 0.19F,
                createOrder.cart().total() * 1.19F,
                new Pending()
        );

    }
}