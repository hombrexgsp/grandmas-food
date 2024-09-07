package com.globant.http;

import com.globant.domain.user.User;
import com.globant.resolvers.UserResolver;


import com.globant.domain.user.CreateUserInput;

import domain.user.DocumentIdentity;
import com.globant.domain.user.UpdateUserInput;
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

    private final UserResolverImp userResolverImp;

    public UserController(UserResolverImp userResolverImp) {
        this.userResolverImp = userResolverImp;
    }

    @QueryMapping
    public List<User> allUsers() {
        return userResolverImp.getAllUsers();
    }

    @QueryMapping
    public User userByDocumentNumber(@Argument String documentNumber) {
        return userResolverImp.getUserByDocumentNumber(documentNumber);
    }

    @MutationMapping
    public User createUser(@Argument CreateUserInput input){
        User newUser = new User(
                new DocumentIdentity(input.getDocumentIdentity().getDocumentType(), input.getDocumentIdentity().getDocumentNumber()),
                input.getFirstName(),
                input.getLastName(),
                input.getEmail(),
                input.getPhoneNumber(),
                input.getAddress()
        );

        return userResolverImp.createUser(newUser);
    }

    @MutationMapping
    public User updateUser(@Argument String documentNumber, @Argument(name = "input") UpdateUserInput updateUserInput){
        User existingUser = userResolverImp.getUserByDocumentNumber(documentNumber);

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

        return userResolverImp.updateUser(documentNumber, updatedUser);
    }

    @MutationMapping
    public boolean deleteUser(@Argument String documentNumber){
        return userResolverImp.deleteUser(documentNumber);
    }

    @QueryMapping
    public List<User> userByFirstName(@Argument("firstName") String firstName){
        return userResolverImp.getUserByFirstName(firstName);
    }

}
