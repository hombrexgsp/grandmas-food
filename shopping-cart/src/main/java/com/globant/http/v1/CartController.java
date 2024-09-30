package com.globant.http.v1;

import com.globant.domain.cart.AddCartCombo;
import domain.cart.CartTotal;
import com.globant.services.ShoppingCart;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final ShoppingCart shoppingCart;

    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @PostMapping("/{document}")
    public ResponseEntity<Void> addToCart (
            @PathVariable @Positive @Digits(integer = 20, fraction = 0) Long document,
            @Valid @RequestBody AddCartCombo combo
    ) {
        shoppingCart.add(document, combo.productId(), combo.quantity());
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{document}")
    public ResponseEntity<CartTotal> getCartTotal(
            @PathVariable @Positive @Digits(integer = 20, fraction = 0) Long document
    ) {
        return ResponseEntity.status(200).body(shoppingCart.get(document));
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<Void> deleteCart(
            @PathVariable @Positive @Digits(integer = 20, fraction = 0) Long document
    ) {
        shoppingCart.delete(document);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{document}/{productId}")
    public ResponseEntity<Void> removeProduct(
            @PathVariable @Positive @Digits(integer = 20, fraction = 0) Long document,
            @PathVariable UUID productId
    ) {
        shoppingCart.removeProduct(document, productId);
        return ResponseEntity.noContent().build();
    }
}
