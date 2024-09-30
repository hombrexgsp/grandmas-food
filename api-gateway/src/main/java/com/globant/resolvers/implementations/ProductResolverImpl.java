package com.globant.resolvers.implementations;

import com.globant.resolvers.ProductResolver;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProductResolverImpl implements ProductResolver {

    private final WebClient webClient;

    public ProductResolverImpl(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }


    @Override
    public Flux<Combo> getAll() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Combo>() {});
    }

    @Override
    public Mono<Combo> searchByUuid(UUID uuid) {
        return webClient.get()
                .uri("/products/{uuid}", uuid)
                .retrieve()
                .bodyToMono(Combo.class);
    }

    @Override
    public Flux<Combo> searchByName(String name) {
        return webClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/products/search")
                        .queryParam("q", name)
                        .build())
                .retrieve()
                .bodyToFlux(Combo.class);
    }

    @Override
    public Mono<Combo> addCombo(CreateCombo createCombo) {
        return webClient.post()
                .uri("/products")
                .body(createCombo, new ParameterizedTypeReference<CreateCombo>() {})
                .retrieve()
                .bodyToMono(Combo.class);
    }

    @Override
    public Mono<Void> updateCombo(UUID uuid, UpdateCombo updateCombo) {
        return webClient.put()
                .uri("/products/{uuid}", uuid)
                .body(updateCombo, new ParameterizedTypeReference<UpdateCombo>() {})
                .retrieve()
                .bodyToMono(Void.class);

    }

    @Override
    public Mono<Void> deleteCombo(UUID uuid) {
        return webClient.delete()
                .uri("/products/{uuid}", uuid)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
