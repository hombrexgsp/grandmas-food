package domain.payment;
import domain.cart.CartTotal;
import jakarta.validation.constraints.NotNull;

public record CreateOrder(
        @NotNull
        Long documentNumber,

        @NotNull
        CartTotal cart,

        @NotNull
        String extraInformation
) {}
