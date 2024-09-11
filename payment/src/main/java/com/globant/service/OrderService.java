package com.globant.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.globant.domain.delivery.CreateDelivery;
import com.globant.domain.error.OrderNotFound;
import com.globant.domain.order.CreatedOrder;
import com.globant.domain.cart.CartComboSimple;
import com.globant.domain.delivery.Delivered;
import com.globant.domain.delivery.Delivery;
import com.globant.domain.delivery.Pending;
import com.globant.mapper.DeliveryMapper;
import com.globant.mapper.OrderMapper;
import com.globant.domain.order.CreateOrder;
import com.globant.domain.order.Order;
import com.globant.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.globant.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Transactional(readOnly = true)
    public List<CreatedOrder> getAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::fromEntity)
                .map(o -> CreatedOrder.fromOrder(o, checkDelivery(o.orderId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public Order getOrder(UUID orderId) throws OrderNotFound {
        return orderRepository
                .findByUuid(orderId)
                .map(orderMapper::fromEntity)
                .orElseThrow(() -> new OrderNotFound(STR."Order with uuid \{orderId} not found"));
    }

    @Transactional(readOnly = true)
    public Delivery checkDelivery(UUID orderId) {
        return deliveryRepository
                .findByOrderId(orderId)
                .<Delivery> map(
                        entity -> new Delivered(entity.getDeliveryTime())
                ).orElse(new Pending());
    }


    @Transactional
    public CreatedOrder createOrder(CreateOrder createOrder){
        final var orderId = UUID.randomUUID();
        final var order = new Order(
                orderId,
                LocalDateTime.now(),
                createOrder.documentNumber(),
                createOrder.cart()
                        .products()
                        .stream()
                        .map(
                                item -> new CartComboSimple(item.combo().uuid(), item.quantity())
                        )
                        .toList(),
                createOrder.extraInformation(),
                createOrder.cart().total()
        );

        return CreatedOrder.fromOrder(
                orderMapper.fromEntity(orderRepository.save(orderMapper.fromDto(order))),
                checkDelivery(orderId)
        );
    }


    @Transactional
    public CreatedOrder setDelivery(UUID orderId, LocalDateTime deliveryTime) {

        final var order = getOrder(orderId);

        deliveryRepository.save(
                deliveryMapper.fromDto(new CreateDelivery(order.orderId(), deliveryTime))
        );

        return CreatedOrder.fromOrder(order,
                checkDelivery(orderId)
        );

    }
}