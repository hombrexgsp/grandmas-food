package com.globant.resolvers;

import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public interface ProductResolver {

    // Queries
    List<Combo> getAll();
    Combo searchByUuid(UUID uuid);
    List<Combo> searchByName(String name);

    // Mutations
    Combo addCombo(CreateCombo createCombo);
    Void updateCombo(UUID uuid, UpdateCombo updateCombo);
    Void deleteCombo(UUID uuid);
}
