package com.globant.services.implementations;

import domain.cart.CartCombo;
import domain.cart.CartTotal;
import com.globant.repository.CartRepository;
import com.globant.services.ProductService;
import com.globant.services.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingCartImpl implements ShoppingCart {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public ShoppingCartImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public void add(Long userId, UUID productId, Integer quantity) {
        cartRepository.addProduct(userId.toString(), productId.toString(), quantity.toString());
    }

    @Override
    public CartTotal get(Long userId) {
        final var cart = cartRepository.getCart(userId.toString())
                .map(tuple -> new CartCombo(productService.getCombo(tuple._1), tuple._2))
                .toList();

        return new CartTotal(cart, cart.stream().map(CartCombo::subTotal).reduce(0F, Float::sum));
    }

    @Override
    public void delete(Long userId) {
        cartRepository.deleteCart(userId.toString());
    }

    @Override
    public void removeProduct(Long userId, UUID productId) {
        cartRepository.removeProductFromCart(userId.toString(), productId.toString());
    }
}
