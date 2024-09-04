package com.globant.services.implementations;

import com.globant.instances.ComboMappable;
import com.globant.repository.ProductRepository;
import com.globant.services.ProductService;
import domain.combo.Combo;
import domain.combo.error.ComboNotFound;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ComboMappable mapper;

    public ProductServiceImpl(ProductRepository repository, ComboMappable mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Combo getCombo(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::to)
                .orElseThrow(() -> new ComboNotFound(STR."Combo with uuid \{uuid} not found"));
    }
}
