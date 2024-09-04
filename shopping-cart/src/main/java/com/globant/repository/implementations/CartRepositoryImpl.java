package com.globant.repository.implementations;

import com.globant.repository.CartRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private final RedisTemplate<String, String> redis;

    public CartRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redis = redisTemplate;
    }

    @Override
    public void addProduct(String userId, String productId, String quantity) {
        redis.opsForHash().putIfAbsent(userId, productId, quantity);
    }

    @Override
    public Stream<Tuple2<UUID, Integer>> getCart(String userId) {
        return redis.<String,String>opsForHash().entries(userId)
                .entrySet()
                .stream()
                .map( entry ->
                        Tuple.of(
                                UUID.fromString(entry.getKey()),
                                Integer.parseInt(entry.getValue())
                        )
                );
    }

    @Override
    public void deleteCart(String userId) {
        redis.delete(userId);
    }

    @Override
    public void removeProductFromCart(String userId, String productId) {
        redis.opsForHash().delete(userId, productId);
    }
}
