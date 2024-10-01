package com.globant.components;

import com.globant.service.ComboService;
import com.globant.service.MenuService;
import domain.combo.Combo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GenerateMenu {

    private final ComboService comboService;
    private final MenuService menuService;

    public GenerateMenu(ComboService comboService, MenuService pdfService) {
        this.comboService = comboService;
        this.menuService = pdfService;
    }

    public ByteArrayOutputStream execute(){
        List<Combo> combos = comboService.getAll();
        return menuService.generateMenuPdf(combos);
    }

    public List<String> executePlaintext() {
        return menuService.generateMenuPlaintext(comboService.getAll());
    }

    public ByteArrayOutputStream executeQr() {
        return menuService.generateMenuQr(comboService.getAll());
    }
}
