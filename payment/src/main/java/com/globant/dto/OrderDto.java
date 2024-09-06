package com.globant.dto;

import com.globant.dto.delivery.Delivery;
import domain.cart.CartTotal;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        UUID uuid,
        LocalDateTime creationDateTime,
        Long userId,
        CartTotal cart,
        String extraInformation,
        Float tax,
        Float total,
        Delivery delivery
) {
}
