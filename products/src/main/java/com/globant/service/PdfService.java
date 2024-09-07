package com.globant.service;

import domain.combo.Combo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream generateMenuPdf(List<Combo> combos) {
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
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
