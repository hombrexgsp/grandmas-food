package com.globant.http;


import com.globant.components.GenerateMenuPdf;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/v1/products/menu")
public class PdfController {

    private final GenerateMenuPdf generateMenuPdf;

    public PdfController(GenerateMenuPdf generateMenuPdf) {
        this.generateMenuPdf = generateMenuPdf;
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> generateMenuPdf() {
        ByteArrayInputStream pdfStream = generateMenuPdf.execute();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=menu.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }

}
