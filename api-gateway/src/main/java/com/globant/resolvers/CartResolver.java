package com.globant.resolvers;

import com.globant.domain.cart.AddCartComboInput;
import domain.cart.CartTotal;

import java.util.UUID;

public interface CartResolver {
    CartTotal getCart(Integer userId);
    CartTotal addToCart(Integer userId, AddCartComboInput cartCombo);
    CartTotal removeFromCart(Integer userId, UUID productId);
    CartTotal clearCart(Integer userId);
}
