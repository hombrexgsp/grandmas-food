package domain.cart;

import java.util.List;

public record CartTotal(
        List<CartCombo> products,
        Float total
) {}
