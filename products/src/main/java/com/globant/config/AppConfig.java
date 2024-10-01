package com.globant.config;

import com.globant.components.GenerateMenu;
import com.globant.service.ComboService;
import com.globant.service.MenuService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GenerateMenu generateMenuPdf(ComboService comboService, MenuService pdfService){
        return new GenerateMenu(comboService, pdfService);
    }
}
