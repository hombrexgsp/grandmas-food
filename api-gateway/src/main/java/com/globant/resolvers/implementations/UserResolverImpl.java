package com.globant.resolvers.implementations;

import com.globant.domain.user.User;
import com.globant.resolvers.UserResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserResolverImpl implements UserResolver {

    private final RestClient userClient;

    public UserResolverImpl(RestClient.Builder restClientBuilder) {
        this.userClient = restClientBuilder.baseUrl("http://localhost:8081/v1/users").build();
    }

    public User createUser(User newUser){
        return userClient.post()
                .uri("")
                .body(newUser)
                .retrieve()
                .body(User.class);
    }

    public List<User> getAllUsers(){
        return userClient.get().retrieve().body(new ParameterizedTypeReference<List<User>>() {});
    }

    public User updateUser(String documentNumber, User updateUser){
        return userClient.put()
                .uri("/{documentNumber}", documentNumber)
                .body(updateUser, new ParameterizedTypeReference<User>() {})
                .retrieve()
                .body(User.class);
    }

    public boolean deleteUser(String documentNumber){
            userClient.delete()
                    .uri("/{documentNumber}", documentNumber)
                    .retrieve()
                    .toBodilessEntity();
                    return true;

    }

    public User getUserByDocumentNumber(String documentNumber) {
        return userClient.get()
                .uri("/document/{documentNumber}", documentNumber)
                .retrieve()
                .body(User.class);
    }

    public List<User> getUserByFirstName(String firstName){
        return userClient.get()
                .uri("/name/{firstName}", firstName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {});
    }
}
