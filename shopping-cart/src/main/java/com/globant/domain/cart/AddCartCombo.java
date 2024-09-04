package com.globant.domain.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AddCartCombo(
        @NotNull
        @org.hibernate.validator.constraints.UUID
        UUID productId,

        @NotNull @Positive
        Integer quantity
) {}
