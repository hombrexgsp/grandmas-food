package com.globant.config;

import com.globant.application.GenerateMenuPdf;
import com.globant.service.ComboService;
import com.globant.service.PdfService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GenerateMenuPdf generateMenuPdf(ComboService comboService, PdfService pdfService){
        return new GenerateMenuPdf(comboService, pdfService);
    }
}
