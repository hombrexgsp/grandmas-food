package service;

import model.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByDocumentNumber(Long documentNumber) {
        return userRepository.findUserByDocumentNumber(documentNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserByDocumentNumber(Long documentNumber) {
        Optional<User> userToDelete =userRepository.findUserByDocumentNumber(documentNumber);
        userToDelete.ifPresent(userRepository::delete);
    }
}
