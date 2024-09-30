package com.globant.domain.order;

public record CheckoutOrder(
        Long documentNumber,
        String extraInformation
) {
}
