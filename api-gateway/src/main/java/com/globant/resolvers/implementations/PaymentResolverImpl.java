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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PaymentResolverImpl implements PaymentResolver {

    private final RestClient paymentClient;
    private final RestClient cartClient;

    @Autowired
    public PaymentResolverImpl(RestClient.Builder builder) {
        this.paymentClient = builder
                .baseUrl("http://localhost:8084/orders")
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .build();

        this.cartClient = builder
                .baseUrl("http://localhost:8083/cart")
                .build();
    }

    @Override
    public List<CreatedOrder> getAllOrders() {
        return paymentClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<CreatedOrder>>() {});
    }

    @Override
    public CreatedOrder checkout(CheckoutOrder checkoutOrder) {
        final var cart = cartClient.get()
                .uri("/{document}", checkoutOrder.documentNumber())
                .retrieve()
                .body(new ParameterizedTypeReference<CartTotal>() {});

        return paymentClient.post()
                .body(new CreateOrder(
                        checkoutOrder.documentNumber(),
                        cart,
                        checkoutOrder.extraInformation()
                ), new ParameterizedTypeReference<CreateOrder>() {})
                .retrieve()
                .body(CreatedOrder.class);
    }

    @Override
    public CreatedOrder deliverOrder(UUID orderId, LocalDateTime deliveryTime) {
        return paymentClient.patch()
                .uri("/{uuid}/delivered/{timestamp}", orderId, deliveryTime)
                .retrieve()
                .body(CreatedOrder.class);
    }
}
