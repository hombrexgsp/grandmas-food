package com.globant.repository;

import io.vavr.Tuple2;

import java.util.UUID;
import java.util.stream.Stream;

public interface CartRepository {
    void addProduct(String userId, String productId, String quantity);
    Stream<Tuple2<UUID, Integer>> getCart(String userId);
    void deleteCart(String userId);
    void removeProductFromCart(String userId, String productId);
}
