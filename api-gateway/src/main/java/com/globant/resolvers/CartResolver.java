package com.globant.resolvers;

import com.globant.domain.cart.AddCartComboInput;
import domain.cart.CartTotal;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CartResolver {
    Mono<CartTotal> getCart(Integer userId);
    Mono<CartTotal> addToCart(Integer userId, AddCartComboInput cartCombo);
    Mono<CartTotal> removeFromCart(Integer userId, UUID productId);
    Mono<CartTotal> clearCart(Integer userId);
}
