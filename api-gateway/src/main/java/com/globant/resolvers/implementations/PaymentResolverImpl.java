package com.globant.resolvers.implementations;

import com.globant.domain.order.CheckoutOrder;
import com.globant.resolvers.PaymentResolver;
import domain.cart.CartTotal;
import domain.payment.CreateOrder;
import domain.payment.CreatedOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PaymentResolverImpl implements PaymentResolver {

    private final WebClient paymentClient;
    private final WebClient cartClient;

    @Autowired
    public PaymentResolverImpl(WebClient.Builder builder) {
        this.paymentClient = builder
                .baseUrl("http://localhost:8084/orders")
                .build();

        this.cartClient = builder
                .baseUrl("http://localhost:8083/cart")
                .build();
    }

    @Override
    public Flux<CreatedOrder> getAllOrders() {
        return paymentClient.get()
                .retrieve()
                .bodyToFlux(CreatedOrder.class);
    }

    @Override
    public Mono<CreatedOrder> checkout(CheckoutOrder checkoutOrder) {
        final var cart = cartClient.get()
                .uri("/{document}", checkoutOrder.documentNumber())
                .retrieve()
                .bodyToMono(CartTotal.class)
                .map( c ->
                        new CreateOrder(checkoutOrder.documentNumber(), c, checkoutOrder.extraInformation())
                );

        return paymentClient.post()
                .body(cart, CreateOrder.class)
                .retrieve()
                .bodyToMono(CreatedOrder.class);
    }

    @Override
    public Mono<CreatedOrder> deliverOrder(UUID orderId, LocalDateTime deliveryTime) {
        return paymentClient.patch()
                .uri("/{uuid}/delivered/{timestamp}", orderId, deliveryTime)
                .retrieve()
                .bodyToMono(CreatedOrder.class);
    }
}
