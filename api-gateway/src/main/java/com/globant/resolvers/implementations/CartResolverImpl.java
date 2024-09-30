package com.globant.resolvers.implementations;

import com.globant.domain.cart.AddCartComboInput;
import com.globant.resolvers.CartResolver;
import domain.cart.CartTotal;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class CartResolverImpl implements CartResolver {

    private final RestClient cartClient;

    public CartResolverImpl(RestClient.Builder builder) {
        this.cartClient = builder
                .baseUrl("http://localhost:8083/cart")
                .build();
    }

    @Override
    public CartTotal getCart(Integer userId) {
        return cartClient.get()
                .uri("/{userId}", userId)
                .retrieve()
                .body(CartTotal.class);
    }

    @Override
    public CartTotal addToCart(Integer userId, AddCartComboInput cartCombo) {
        cartClient.post()
                .uri("/{userId}", userId)
                .body(cartCombo)
                .retrieve()
                .toBodilessEntity();

        return getCart(userId);
    }

    @Override
    public CartTotal removeFromCart(Integer userId, UUID productId) {
        cartClient.delete()
                .uri("/{userId}/{productId}", userId, productId)
                .retrieve()
                .toBodilessEntity();

        return getCart(userId);
    }

    @Override
    public CartTotal clearCart(Integer userId) {
        cartClient.delete()
                .uri("/{userId}", userId)
                .retrieve()
                .toBodilessEntity();

        return getCart(userId);
    }
}
