package com.globant.http;

import com.globant.domain.cart.AddCartComboInput;
import com.globant.resolvers.CartResolver;
import domain.cart.CartTotal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartResolver cartResolver;

    @QueryMapping
    public CartTotal getCart(
            @Argument @Positive @Digits(integer = 20, fraction = 0) Integer documentNumber
    ) {
        return cartResolver.getCart(documentNumber);
    }

    @MutationMapping
    public CartTotal addToCart(
            @Argument @Positive @Digits(integer = 20, fraction = 0) Integer documentNumber,
            @Argument @Valid AddCartComboInput cartCombo) {
        return cartResolver.addToCart(documentNumber, cartCombo);
    }

    @MutationMapping
    public CartTotal removeFromCart(
            @Argument @Positive @Digits(integer = 20, fraction = 0) Integer documentNumber,
            @Argument @UUID String productId
    ) {
        return cartResolver.removeFromCart(documentNumber, java.util.UUID.fromString(productId));
    }

    @MutationMapping
    public CartTotal clearCart(@Argument @Positive @Digits(integer = 20, fraction = 0) Integer documentNumber) {
        return cartResolver.clearCart(documentNumber);
    }
}
