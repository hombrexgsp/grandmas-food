package com.globant.services;

import domain.combo.Combo;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface ProductService {
    Combo getCombo(UUID uuid);
}
