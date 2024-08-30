package com.globant.service;

import com.globant.dto.UserDto;
import com.globant.exception.customException.DuplicateUserException;
import com.globant.exception.customException.UserNotFoundException;
import com.globant.mapper.UserMapper;
import com.globant.model.User;
import com.globant.model.identity.DocumentIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globant.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        DocumentIdentity documentIdentity = userDto.getDocumentIdentity();
        if(userRepository.existsByDocumentDocumentNumber(documentIdentity.getDocumentNumber())) {
            throw new DuplicateUserException("User with document: " + documentIdentity.getDocumentNumber() + " already exists");
        }

        User newUser = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(newUser);

        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserByDocumentNumber(Long documentNumber) {

        return userRepository.findUserByDocumentNumber(documentNumber)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long documentNumber, UserDto userDto) {

        User existingUser = userRepository.findUserByDocumentNumber(documentNumber)
                .orElseThrow(() -> new UserNotFoundException("User with document number: " + documentNumber + " not found."));
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setAddress(userDto.getAddress());

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long documentNumber) {
        if(!userRepository.existsByDocumentDocumentNumber(documentNumber)) {
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


}
