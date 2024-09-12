package com.globant.http;

import com.globant.domain.user.User;
import com.globant.resolvers.UserResolver;


import com.globant.domain.user.CreateUserInput;

import domain.user.DocumentIdentity;
import com.globant.domain.user.UpdateUserInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class UserController {

    private final UserResolver userResolver;

    public UserController(UserResolver userResolver) {
        this.userResolver = userResolver;
    }

    @QueryMapping
    public List<User> allUsers() {
        return userResolver.getAllUsers();
    }

    @QueryMapping
    public User userByDocumentNumber(@Argument @NotEmpty String documentNumber) {
        return userResolver.getUserByDocumentNumber(documentNumber);
    }

    @MutationMapping
    public User createUser(@Argument @Valid CreateUserInput input){
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
    public User updateUser(@Argument @NotEmpty String documentNumber,
                           @Argument(name = "input") @Valid UpdateUserInput updateUserInput){
        User existingUser = userResolver.getUserByDocumentNumber(documentNumber);

        User updatedUser = new User(
                existingUser.documentIdentity(),
                Optional.ofNullable(updateUserInput.firstName()).orElse(existingUser.firstName()),
                Optional.ofNullable(updateUserInput.lastName()).orElse(existingUser.lastName()),
                Optional.ofNullable(updateUserInput.email()).orElse(existingUser.email()),
                Optional.ofNullable(updateUserInput.phoneNumber()).orElse(existingUser.phoneNumber()),
                Optional.ofNullable(updateUserInput.address()).orElse(existingUser.address())
//                Optional.ofNullable(updateUserInput.firstName()).orElse(existingUser.firstName()),
//                updateUserInput.lastName() != null ? updateUserInput.lastName() : existingUser.lastName(),
//                updateUserInput.email() != null ? updateUserInput.email() : existingUser.email(),
//                updateUserInput.phoneNumber() != null ? updateUserInput.phoneNumber() : existingUser.phoneNumber(),
//                updateUserInput.address() != null ? updateUserInput.address() : existingUser.address()
        );

        log.info(updatedUser.toString());

        return userResolver.updateUser(documentNumber, updatedUser);
    }

    @MutationMapping
    public boolean deleteUser(@Argument @NotEmpty String documentNumber){
        return userResolver.deleteUser(documentNumber);
    }

    @QueryMapping
    public List<User> userByFirstName(@Argument("firstName") @NotEmpty String firstName){
        return userResolver.getUserByFirstName(firstName);
    }

}
