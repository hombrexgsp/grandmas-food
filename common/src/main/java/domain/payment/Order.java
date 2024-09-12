package domain.payment;


import domain.payment.cart.CartComboSimple;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(
        @NotEmpty
        UUID orderId,

        @NotNull
        @FutureOrPresent
        LocalDateTime creationTime,

        @NotNull
        Long documentNumber,

        @NotNull
        List<CartComboSimple> combos,

        @NotNull
        String extraInformation,

        @Positive
        Float subtotal
) {}
