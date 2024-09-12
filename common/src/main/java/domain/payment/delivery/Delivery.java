package domain.payment.delivery;

public sealed interface Delivery permits Delivered, Pending {}
