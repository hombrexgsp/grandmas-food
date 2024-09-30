package com.globant.domain.error;

import java.util.UUID;

public final class OrderNotFound extends RuntimeException {
    public OrderNotFound(UUID orderId) {
        super(STR."Order with id \{orderId} not found");
    }
}
