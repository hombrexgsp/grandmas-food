package com.globant.services;

import domain.cart.CartTotal;

import java.util.UUID;

public interface ShoppingCart {
    void add(Long userId, UUID productId, Integer quantity);
    CartTotal get(Long userId);
    void delete(Long userId);
    void removeProduct(Long userId, UUID productId);
}
