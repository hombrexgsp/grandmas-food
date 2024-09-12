package domain.payment.delivery;

public record Pending() implements Delivery {
    @Override
    public String toString() {
        return "Pending";
    }
}
