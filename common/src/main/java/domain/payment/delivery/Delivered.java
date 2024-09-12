package domain.payment.delivery;

import java.time.LocalDateTime;

public record Delivered(LocalDateTime deliveredDate) implements Delivery {
    @Override
    public String toString() {
        return STR."Delivered in \{deliveredDate}";
    }
}

