package com.globant.resolvers.implementations;

import com.globant.domain.user.UpdateUserInput;
import com.globant.domain.user.User;
import com.globant.resolvers.UserResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class UserResolverImpl implements UserResolver {

    private final WebClient userClient;

    public UserResolverImpl(WebClient.Builder restClientBuilder) {
        this.userClient = restClientBuilder
                .baseUrl("http://localhost:8081/users")
                .build();
    }

    public Mono<User> createUser(User newUser){
        return userClient.post()
                .body(Mono.just(newUser), User.class)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Flux<User> getAllUsers(){
        return userClient.get()
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<User> updateUser(String documentNumber, UpdateUserInput updateUserInput){
        final var updatedUser = getUserByDocumentNumber(documentNumber)
                .map( existingUser ->
                        new User(
                                existingUser.documentIdentity(),
                                Optional.ofNullable(updateUserInput.firstName()).orElse(existingUser.firstName()),
                                Optional.ofNullable(updateUserInput.lastName()).orElse(existingUser.lastName()),
                                Optional.ofNullable(updateUserInput.email()).orElse(existingUser.email()),
                                Optional.ofNullable(updateUserInput.phoneNumber()).orElse(existingUser.phoneNumber()),
                                Optional.ofNullable(updateUserInput.address()).orElse(existingUser.address())

                    )
                );

        return userClient.put()
                .uri("/{documentNumber}", documentNumber)
                .body(updatedUser, User.class)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<Boolean> deleteUser(String documentNumber) {
        return userClient.delete()
                .uri("/{documentNumber}", documentNumber)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new HttpClientErrorException(clientResponse.statusCode()))
                )
                .bodyToMono(Void.class)
                .then(Mono.just(true))
                .onErrorReturn(false);
    }

    public Mono<User> getUserByDocumentNumber(String documentNumber) {
        return userClient.get()
                .uri("/document/{documentNumber}", documentNumber)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Flux<User> getUserByFirstName(String firstName){
        return userClient.get()
                .uri("/name/{firstName}", firstName)
                .retrieve()
                .bodyToFlux(User.class);
    }

}
