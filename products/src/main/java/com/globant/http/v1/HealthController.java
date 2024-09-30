package com.globant.http.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class HealthController {

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return """
                {
                    "health": "Safe and sound!"
                }
                """;
    }
}
