package com.globant.http;

import com.globant.resolvers.ProductResolver;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductResolver productResolver;

    public ProductController(ProductResolver productResolver) {
        this.productResolver = productResolver;
    }

    @QueryMapping
    public Flux<Combo> allCombos() {
        return productResolver.getAll();
    }

    @QueryMapping
    public Mono<Combo> comboByUuid(@Argument @NotEmpty @org.hibernate.validator.constraints.UUID String uuid) {
        return productResolver.searchByUuid(UUID.fromString(uuid));
    }

    @QueryMapping
    public Flux<Combo> combosByName(@Argument @NotEmpty String name) {
        return productResolver.searchByName(name);
    }

    @MutationMapping
    public Mono<Combo> createCombo(@Argument(name = "input") @Valid CreateCombo combo) {
        return productResolver.addCombo(combo);
    }

    @MutationMapping
    public Mono<Void> updateCombo(
            @Argument @NotEmpty
            @org.hibernate.validator.constraints.UUID String uuid,
            @Argument(name = "input") @Valid UpdateCombo combo
    ) {
        return productResolver.updateCombo(UUID.fromString(uuid), combo);
    }

    @MutationMapping
    public Mono<Void> deleteCombo(@Argument @org.hibernate.validator.constraints.UUID String uuid) {
        return productResolver.deleteCombo(UUID.fromString(uuid));
    }
}
