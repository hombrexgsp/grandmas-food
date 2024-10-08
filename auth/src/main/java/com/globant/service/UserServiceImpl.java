package com.globant.service;

import com.globant.dto.UserDto;
import domain.combo.error.UserException.DuplicateUserException;
import domain.combo.error.UserException.InvalidOrIncompleteUserException;
import domain.combo.error.UserException.NotFieldsUpdatedException;
import domain.combo.error.UserException.UserNotFoundException;
import com.globant.mapper.UserMapper;
import com.globant.model.User;
import com.globant.model.identity.DocumentIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globant.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    private boolean isUserDtoInvalidOrIncomplete(UserDto userDto) {
        return userDto.getFirstName() == null || userDto.getFirstName().isEmpty()
                || userDto.getLastName() == null || userDto.getLastName().isEmpty()
                || userDto.getEmail() == null || userDto.getEmail().isEmpty()
                || userDto.getPhoneNumber() == null
                || userDto.getAddress() == null || userDto.getAddress().isEmpty()
                || userDto.getDocumentIdentity() == null
                || userDto.getDocumentIdentity().getDocumentType() == null
                || userDto.getDocumentIdentity().getDocumentNumber() == null;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if(isUserDtoInvalidOrIncomplete(userDto)){
            throw new InvalidOrIncompleteUserException("User data is invalid or incomplete");
        }

        DocumentIdentity documentIdentity = userDto.getDocumentIdentity();
        if (userRepository.existsByDocumentDocumentNumber(documentIdentity.getDocumentNumber())) {
            throw new DuplicateUserException("User with document: " + documentIdentity.getDocumentNumber() + " already exists");
        }
            User newUser = userMapper.toEntity(userDto);
            User savedUser = userRepository.save(newUser);

        return userMapper.toDto(savedUser);

    }


    @Override
    @Transactional
    public UserDto updateUser(Long documentNumber, UserDto userDto) {

        User existingUser = userRepository.findUserByDocumentNumber(documentNumber)
                .orElseThrow(() -> new UserNotFoundException("User with document number: " + documentNumber + " not found."));

        if(!documentNumber.equals(userDto.getDocumentIdentity().getDocumentNumber())){
            throw new InvalidOrIncompleteUserException("Document number cannot be changed");
        }

        if (isUserDtoInvalidOrIncomplete(userDto)) {
            throw new InvalidOrIncompleteUserException("Values of the customer are invalid or incomplete");
        }

        boolean isUpdated = false;

        if (!existingUser.getFirstName().equals(userDto.getFirstName())) {
            existingUser.setFirstName(userDto.getFirstName());
            isUpdated = true;
        }
        if (!existingUser.getLastName().equals(userDto.getLastName())) {
            existingUser.setLastName(userDto.getLastName());
            isUpdated = true;
        }
        if (!existingUser.getEmail().equals(userDto.getEmail())) {
            existingUser.setEmail(userDto.getEmail());
            isUpdated = true;
        }
        if (!existingUser.getPhoneNumber().equals(userDto.getPhoneNumber())) {
            existingUser.setPhoneNumber(userDto.getPhoneNumber());
            isUpdated = true;
        }
        if (!existingUser.getAddress().equals(userDto.getAddress())) {
            existingUser.setAddress(userDto.getAddress());
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new NotFieldsUpdatedException("Any field is different in this update");
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByDocumentNumber(Long documentNumber) {

        if(documentNumber == null || documentNumber <=0){
            throw new InvalidOrIncompleteUserException("Document number is invalid or incomplete");
        }

        return userRepository.findUserByDocumentNumber(documentNumber)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with document number: " + documentNumber + " not found."));

    }

    @Override
    @Transactional
    public void deleteUser(Long documentNumber) {
        if (!userRepository.existsByDocumentDocumentNumber(documentNumber)) {
            throw new UserNotFoundException("User with document number: " + documentNumber + " not found.");
        }

        userRepository.deleteByDocumentDocumentNumber(documentNumber);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<UserDto> getAllUsers() {
//        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
//    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersSorted() {
        return userRepository.getAllUsersSorted().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUserByNameContaining(String firstName) {

        List<UserDto> users = userRepository.findUserByNameContaining(firstName)
                .stream().map(userMapper::toDto).collect(Collectors.toList());

        if(users.isEmpty()){
            throw new UserNotFoundException("Users with first name '" + firstName + "' not found.");
        }

        return users;
    }
}
