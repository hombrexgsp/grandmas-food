package com.globant.infrastructure.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    @Autowired
    public UserClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Object> allUsers(){
        return restTemplate.getForObject("http://localhost:8081/users", List.class);
    }

    public Object userByDocumentNumber(String documentNumber){
        return restTemplate.getForObject("http://localhost:8081/users/document/{documentNumber}", Object.class, documentNumber);
    }

}
