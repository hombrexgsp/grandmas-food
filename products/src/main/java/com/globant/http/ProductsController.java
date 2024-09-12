package com.globant.http;

import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.globant.service.ComboService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ComboService comboService;

    @Autowired
    public ProductsController(ComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping
    public ResponseEntity<List<Combo>> getAllCombos() {
        return ResponseEntity.ok(comboService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Combo> getByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(comboService.searchByUuid(uuid));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Combo>> getByName(@RequestParam("q") String name) {
        return ResponseEntity.ok(comboService.searchByName(name));
    }

    @PostMapping
    public ResponseEntity<Combo> addCombo(@Valid @RequestBody CreateCombo createCombo) {
        return ResponseEntity.status(201).body(comboService.addCombo(createCombo));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateCombo(@PathVariable UUID uuid, @Valid @RequestBody UpdateCombo updateCombo) {
        comboService.updateCombo(uuid, updateCombo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCombo(@PathVariable UUID uuid) {
        comboService.deleteCombo(uuid);
        return ResponseEntity.noContent().build();
    }
}
