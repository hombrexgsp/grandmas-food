package com.globant.service;

import domain.combo.Combo;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class MenuService {

    public ByteArrayOutputStream generateMenuPdf(List<Combo> combos) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 35);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(200, 750);
                contentStream.showText("Menu - GrandmasFood");
                contentStream.endText();

                contentStream.moveTo(25,740);
                contentStream.lineTo(575,740);
                contentStream.stroke();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(100, 700);

                for (Combo combo : combos) {
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    contentStream.showText(STR."\{combo.fantasyName()} $ \{combo.price()} COP");
                    contentStream.newLine();

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.showText(combo.description());
                    contentStream.newLine();
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream;

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    public List<String> generateMenuPlaintext(List<Combo> combos) {
        return combos.stream()
                .map( combo ->
                        STR."""
                                \{combo.fantasyName()} $ \{combo.price()} COP
                                \{combo.description()}
                                """
                )
                .toList();
    }

    public ByteArrayOutputStream generateMenuQr(List<Combo> combos) {
        return QRCode.from(String.join("\n", generateMenuPlaintext(combos)))
                .withSize(500, 500)
                .withColor(0xFF000000, 0xFFFFFFFF)
                .to(ImageType.PNG)
                .stream();
    }
}
