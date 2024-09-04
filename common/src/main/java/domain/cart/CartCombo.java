package domain.cart;

import domain.combo.Combo;

public record CartCombo(
        Combo combo,
        Integer quantity
) {
    public Float subTotal() {
        return combo.price() * quantity;
    }
}
