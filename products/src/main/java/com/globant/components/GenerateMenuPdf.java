package com.globant.components;

import com.globant.service.ComboService;
import com.globant.service.PdfService;
import domain.combo.Combo;

import java.io.ByteArrayInputStream;
import java.util.List;

public class GenerateMenuPdf {

    private final ComboService comboService;
    private final PdfService pdfService;

    public GenerateMenuPdf(ComboService comboService, PdfService pdfService) {
        this.comboService = comboService;
        this.pdfService = pdfService;
    }

    public ByteArrayInputStream execute(){
        List<Combo> combos = comboService.getAll();
        return pdfService.generateMenuPdf(combos);
    }
}
