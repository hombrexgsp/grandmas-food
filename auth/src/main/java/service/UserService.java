package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    Optional<User> getUserByDocumentNumber(Long documentNumber);
    List<User> getAllUsers();
    void deleteUserByDocumentNumber(Long documentNumber);

}
