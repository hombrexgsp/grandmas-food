package com.globant.resolvers;

import com.globant.domain.user.UpdateUserInput;
import com.globant.domain.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserResolver {

    Mono<User> createUser(User newUser);
    Flux<User> getAllUsers();
    Mono<User> updateUser(String documentNumber, UpdateUserInput updateUserInput);
    Mono<Boolean> deleteUser(String documentNumber);
    Mono<User> getUserByDocumentNumber(String documentNumber);
    Flux<User> getUserByFirstName(String firstName);
}
