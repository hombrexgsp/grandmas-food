package com.globant.domain.cart;

import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AddCartComboInput(
        UUID productId,

        @Positive
        Integer quantity
) {
}
