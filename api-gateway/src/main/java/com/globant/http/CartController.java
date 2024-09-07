package com.globant.http;

import com.globant.domain.cart.AddCartComboInput;
import com.globant.resolvers.CartResolver;
import domain.cart.CartTotal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartResolver cartResolver;

    @QueryMapping
    public CartTotal getCart(@Argument Integer documentNumber) {
        return cartResolver.getCart(documentNumber);
    }

    @MutationMapping
    public CartTotal addToCart(@Argument Integer documentNumber, @Argument @Valid AddCartComboInput cartCombo) {
        return cartResolver.addToCart(documentNumber, cartCombo);
    }

    @MutationMapping
    public CartTotal removeFromCart(@Argument Integer documentNumber, @Argument UUID productId) {
        return cartResolver.removeFromCart(documentNumber, productId);
    }

    @MutationMapping
    public CartTotal clearCart(@Argument Integer documentNumber) {
        return cartResolver.clearCart(documentNumber);
    }
}
