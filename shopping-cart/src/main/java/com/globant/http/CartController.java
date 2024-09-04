package com.globant.http;

import com.globant.domain.cart.AddCartCombo;
import domain.cart.CartTotal;
import com.globant.services.ShoppingCart;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final ShoppingCart shoppingCart;

    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @PostMapping("/{document}")
    public void addToCart (
            @PathVariable @Positive @Digits(integer = 20, fraction = 0) Long document,
            @Valid @RequestBody AddCartCombo combo
    ) {
        shoppingCart.add(document, combo.productId(), combo.quantity());
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
            @PathVariable @NotBlank UUID productId
    ) {
        shoppingCart.removeProduct(document, productId);
        return ResponseEntity.noContent().build();
    }
}
