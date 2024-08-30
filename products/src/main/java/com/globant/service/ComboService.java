package com.globant.service;

import com.globant.instances.ComboMappable;
import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import domain.combo.error.ComboNotFound;
import org.springframework.stereotype.Service;
import com.globant.repository.ComboRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ComboService {

    private final ComboRepository repository;
    private final ComboMappable mapper;

    public ComboService(ComboRepository repository, ComboMappable mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Combo> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::to)
                .toList();
    }

    @Transactional(readOnly = true)
    public Combo searchByUuid(UUID uuid) {
        return repository
                .findByUuid(uuid)
                .map(mapper::to)
                .orElseThrow(
                        () -> new ComboNotFound(STR."Combo with uuid \{uuid} not found")
                );
    }

    @Transactional(readOnly = true)
    public List<Combo> searchByName(String name) {
        return repository
                .findByFantasyName(name)
                .stream()
                .map(mapper::to)
                .toList();
    }

    @Transactional
    public Combo addCombo(CreateCombo createCombo) {
          return mapper.to(repository.save(mapper.from(createCombo.toCombo(UUID.randomUUID()))));
    }

    @Transactional
    public void updateCombo(UUID uuid, UpdateCombo updateCombo) {
        repository.findByUuid(uuid).ifPresentOrElse(
                _ -> {
                    repository.updateByUuid(uuid, mapper.from(updateCombo.toCombo(uuid)));
                },
                () -> {
                    throw new ComboNotFound(STR."Combo with uuid \{uuid} not found");
                }
        );
    }

    @Transactional
    public void deleteCombo(UUID uuid) {
        repository.findByUuid(uuid).ifPresentOrElse(
                repository::delete,
                () -> {
                    throw new ComboNotFound(STR."Combo with uuid \{uuid} not found");
                }
        );
    }


}


