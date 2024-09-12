package com.globant.resolvers;

import com.globant.domain.user.User;

import java.util.List;

public interface UserResolver {

    User createUser(User newUser);
    List<User> getAllUsers();
    User updateUser(String documentNumber, User updateUser);
    boolean deleteUser(String documentNumber);
    User getUserByDocumentNumber(String documentNumber);
    List<User> getUserByFirstName(String firstName);
}
