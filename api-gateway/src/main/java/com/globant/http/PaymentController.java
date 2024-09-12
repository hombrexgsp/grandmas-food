package com.globant.http;

import com.globant.domain.order.CheckoutOrder;
import com.globant.resolvers.PaymentResolver;
import domain.payment.CreateOrder;
import domain.payment.CreatedOrder;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PaymentController {

    private final PaymentResolver paymentResolver;

    @Autowired
    public PaymentController(PaymentResolver paymentResolver) {
        this.paymentResolver = paymentResolver;
    }

    @QueryMapping
    public List<CreatedOrder> allOrders() {
        return paymentResolver.getAllOrders();
    }

    @MutationMapping
    public CreatedOrder createOrder(@Argument(name = "input") CheckoutOrder checkoutOrder) {
        return paymentResolver.createOrder(checkoutOrder);
    }

    @MutationMapping
    public CreatedOrder deliverOrder(
            @Argument @UUID String orderId,
            @Argument @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String deliveryTime
    ) {
        return paymentResolver.deliverOrder(
                java.util.UUID.fromString(orderId),
                LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(deliveryTime))
        );
    }

}
