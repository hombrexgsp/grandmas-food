package com.globant.http;

import com.globant.resolvers.ProductResolver;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductResolver productResolver;

    public ProductController(ProductResolver productResolver) {
        this.productResolver = productResolver;
    }

    @QueryMapping
    public List<Combo> allCombos() {
        return productResolver.getAll();
    }

    @QueryMapping
    public Combo comboByUuid(@Argument UUID uuid) {
        return productResolver.searchByUuid(uuid);
    }

    @QueryMapping
    public List<Combo> combosByName(@Argument String name) {
        return productResolver.searchByName(name);
    }

    @MutationMapping
    public Combo createCombo(@Argument(name = "input") CreateCombo combo) {
        return productResolver.addCombo(combo);
    }

    @MutationMapping
    public void updateCombo(@Argument UUID uuid, @Argument(name = "input") UpdateCombo combo) {
        productResolver.updateCombo(uuid, combo);
    }

    @MutationMapping
    public void deleteCombo(@Argument UUID uuid) {
        productResolver.deleteCombo(uuid);
    }

}
