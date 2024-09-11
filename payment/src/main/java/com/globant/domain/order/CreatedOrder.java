package com.globant.domain.order;

import com.globant.domain.cart.CartComboSimple;
import com.globant.domain.delivery.Delivery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreatedOrder(
        UUID uuid,
        LocalDateTime creationDateTime,
        Long userId,
        List<CartComboSimple> products,
        String extraInformation,
        Float subtotal,
        Float tax,
        Float total,
        Delivery delivery
) {
    public static CreatedOrder fromOrder(Order order, Delivery delivery) {
        return new CreatedOrder(
                order.orderId(),
                order.creationTime(),
                order.documentNumber(),
                order.combos(),
                order.extraInformation(),
                order.subtotal(),
                order.subtotal() * 0.19f,
                order.subtotal() * 1.19f,
                delivery
        );
    }
}
