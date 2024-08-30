package com.globant.http;

import domain.combo.Combo;
import domain.combo.CreateCombo;
import domain.combo.UpdateCombo;
import io.vavr.control.Try;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.globant.service.ComboService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ComboService comboService;

    public ProductsController(ComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping
    public List<Combo> getAllCombos() {
        return comboService.getAll();
    }

    @GetMapping("/{uuid}")
    public Combo getByUuid(@PathVariable UUID uuid) {
        return comboService.searchByUuid(uuid);
    }

    @GetMapping("/search")
    public List<Combo> getByName(@RequestParam("q") String name) {
        return comboService.searchByName(name);
    }

    @PostMapping
    public ResponseEntity<Combo> addCombo(@Valid @RequestBody CreateCombo createCombo) {
        return ResponseEntity.status(201).body(comboService.addCombo(createCombo));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateCombo(@PathVariable UUID uuid, @Valid @RequestBody UpdateCombo updateCombo) {
        return Try.run(() -> comboService.updateCombo(uuid, updateCombo))
                .map(_ -> ResponseEntity.noContent().<Void>build())
                .get();

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCombo(@PathVariable UUID uuid) {
        return Try.run(() -> comboService.deleteCombo(uuid))
                .map(_ -> ResponseEntity.noContent().<Void>build())
                .get();
    }
}
