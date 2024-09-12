package com.globant.domain.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AddCartCombo(
        UUID productId,

        @NotNull @Positive
        Integer quantity
) {}
