package com.globant.domain.delivery;

public record Pending() implements Delivery {
    @Override
    public String toString() {
        return "Pending";
    }
}
