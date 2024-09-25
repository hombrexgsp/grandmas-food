package com.globant.resolvers;

import com.globant.domain.order.CheckoutOrder;
import domain.payment.CreateOrder;
import domain.payment.CreatedOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PaymentResolver {
    Flux<CreatedOrder> getAllOrders();
    Mono<CreatedOrder> checkout(CheckoutOrder checkoutOrder);
    Mono<CreatedOrder> deliverOrder(UUID orderId, LocalDateTime deliveryTime);
}
