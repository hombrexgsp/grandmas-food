package com.globant.http;

import com.globant.components.GenerateMenu;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/menu")
public class MenuController {

    private final GenerateMenu generateMenu;

    public MenuController(GenerateMenu generateMenu) {
        this.generateMenu = generateMenu;
    }

    @GetMapping("/plaintext")
    public ResponseEntity<List<String>> generateMenuPlaintext() {
        return ResponseEntity.ok(generateMenu.executePlaintext());
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateMenuPdf() {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=menu.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(generateMenu.execute().toByteArray());
    }

    @GetMapping("/qr")
    public ResponseEntity<byte[]> generateMenuQr() {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=menu_qr.png")
                .contentType(MediaType.IMAGE_PNG)
                .body(generateMenu.executeQr().toByteArray());
    }
}
