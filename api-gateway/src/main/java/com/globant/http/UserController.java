package com.globant.http;

import com.globant.domain.user.User;
import com.globant.resolvers.UserResolver;


import com.globant.domain.user.CreateUserInput;

import domain.user.DocumentIdentity;
import com.globant.domain.user.UpdateUserInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    private final UserResolver userResolver;

    public UserController(UserResolver userResolver) {
        this.userResolver = userResolver;
    }

    @QueryMapping
    public Flux<User> allUsers() {
        return userResolver.getAllUsers();
    }

    @QueryMapping
    public Mono<User> userByDocumentNumber(@Argument @NotEmpty String documentNumber) {
        return userResolver.getUserByDocumentNumber(documentNumber);
    }

    @MutationMapping
    public Mono<User> createUser(@Argument @Valid CreateUserInput input){
        User newUser = new User(
                new DocumentIdentity(input.getDocumentIdentity().getDocumentType(), input.getDocumentIdentity().getDocumentNumber()),
                input.getFirstName(),
                input.getLastName(),
                input.getEmail(),
                input.getPhoneNumber(),
                input.getAddress()
        );

        return userResolver.createUser(newUser);
    }

    @MutationMapping
    public Mono<User> updateUser(
            @Argument @NotEmpty String documentNumber,
            @Argument(name = "input") @Valid UpdateUserInput updateUserInput
    ) {

        return userResolver.updateUser(documentNumber, updateUserInput);
    }

    @MutationMapping
    public Mono<Boolean> deleteUser(@Argument @NotEmpty String documentNumber){
        return userResolver.deleteUser(documentNumber);
    }

    @QueryMapping
    public Flux<User> userByFirstName(@Argument("firstName") @NotEmpty String firstName){
        return userResolver.getUserByFirstName(firstName);
    }

}
