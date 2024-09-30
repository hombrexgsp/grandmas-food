package com.globant.resolvers;

import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductResolver {

    // Queries
    Flux<Combo> getAll();
    Mono<Combo> searchByUuid(UUID uuid);
    Flux<Combo> searchByName(String name);

    // Mutations
    Mono<Combo> addCombo(CreateCombo createCombo);
    Mono<Void> updateCombo(UUID uuid, UpdateCombo updateCombo);
    Mono<Void> deleteCombo(UUID uuid);
}
