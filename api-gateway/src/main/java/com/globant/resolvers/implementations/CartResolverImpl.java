package com.globant.resolvers.implementations;

import com.globant.domain.cart.AddCartComboInput;
import com.globant.resolvers.CartResolver;
import domain.cart.CartTotal;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CartResolverImpl implements CartResolver {

    private final WebClient cartClient;

    public CartResolverImpl(WebClient.Builder builder) {
        this.cartClient = builder
                .baseUrl("http://localhost:8083/cart")
                .build();
    }

    @Override
    public Mono<CartTotal> getCart(Integer userId) {
        return cartClient.get()
                .uri("/{userId}", userId)
                .retrieve()
                .bodyToMono(CartTotal.class);
    }

    @Override
    public Mono<CartTotal> addToCart(Integer userId, AddCartComboInput cartCombo) {
        return cartClient.post()
                .uri("/{userId}", userId)
                .body(cartCombo, AddCartComboInput.class)
                .retrieve()
                .bodyToMono(Void.class)
                .then(getCart(userId));
    }

    @Override
    public Mono<CartTotal> removeFromCart(Integer userId, UUID productId) {
        return cartClient.delete()
                .uri("/{userId}/{productId}", userId, productId)
                .retrieve()
                .bodyToMono(Void.class)
                .then(getCart(userId));
    }

    @Override
    public Mono<CartTotal> clearCart(Integer userId) {
        return cartClient.delete()
                .uri("/{userId}", userId)
                .retrieve()
                .bodyToMono(Void.class)
                .then(getCart(userId));
    }
}
