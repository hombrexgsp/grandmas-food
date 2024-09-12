package com.globant.resolvers;

import com.globant.domain.order.CheckoutOrder;
import domain.payment.CreateOrder;
import domain.payment.CreatedOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PaymentResolver {
    List<CreatedOrder> getAllOrders();
    CreatedOrder checkout(CheckoutOrder checkoutOrder);
    CreatedOrder deliverOrder(UUID orderId, LocalDateTime deliveryTime);
}
