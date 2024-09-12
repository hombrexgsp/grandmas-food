package domain.payment.cart;

import java.util.UUID;

public record CartComboSimple(
        UUID comboId,
        Integer quantity
) {
}
