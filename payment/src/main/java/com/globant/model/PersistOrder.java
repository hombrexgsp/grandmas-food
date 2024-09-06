package com.globant.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record PersistOrder(
        UUID orderId,
        Long documentNumber,
        LocalDateTime creationTime,
        String extraInformation,
        Float subtotal


) {


}
