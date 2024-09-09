package com.globant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class Payment {

    public static void main(String[] args) {
        SpringApplication.run(Payment.class, args);
    }

}