package domain.payment.delivery;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Delivered(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        LocalDateTime deliveredTime
) implements Delivery {
    @Override
    public String toString() {
        return STR."Delivered in \{deliveredTime}";
    }
}

