package com.globant.resolvers.implementations;

import com.globant.resolvers.ProductResolver;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class ProductResolverImpl implements ProductResolver {

    private final RestClient restClient;

    public ProductResolverImpl(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8080")
                .build();
    }


    @Override
    public List<Combo> getAll() {
        return restClient.get()
                .uri("/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Combo>>() {});
    }

    @Override
    public Combo searchByUuid(UUID uuid) {
        return restClient.get()
                .uri("/products/{uuid}", uuid)
                .retrieve()
                .body(Combo.class);
    }

    @Override
    public List<Combo> searchByName(String name) {
        return restClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/products/search")
                        .queryParam("q", name)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<Combo>>() {});
    }

    @Override
    public Combo addCombo(CreateCombo createCombo) {
        return restClient.post()
                .uri("/products")
                .body(createCombo, new ParameterizedTypeReference<CreateCombo>() {})
                .retrieve()
                .body(Combo.class);
    }

    @Override
    public Void updateCombo(UUID uuid, UpdateCombo updateCombo) {
        return restClient.put()
                .uri("/products/{uuid}", uuid)
                .body(updateCombo, new ParameterizedTypeReference<UpdateCombo>() {})
                .retrieve()
                .body(Void.class);

    }

    @Override
    public Void deleteCombo(UUID uuid) {
        return restClient.delete()
                .uri("/products/{uuid}", uuid)
                .retrieve()
                .body(Void.class);
    }
}
