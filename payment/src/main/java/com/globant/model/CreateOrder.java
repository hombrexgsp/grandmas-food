package com.globant.model;
import domain.cart.CartTotal;

public record CreateOrder(
        Long documentNumber,
        CartTotal cart,
        String extraInformation
) {
}
