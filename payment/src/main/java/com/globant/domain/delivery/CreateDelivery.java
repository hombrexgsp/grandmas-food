package com.globant.domain.delivery;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateDelivery(
        UUID orderId,
        LocalDateTime deliveryTime
) {}
