package com.globant.service;

import com.globant.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);

    //Should I add the document type in parameters?
    Optional<UserDto> getUserByDocumentNumber(Long documentNumber);

    UserDto updateUser(Long documentNumber, UserDto userDto);

    void deleteUser(Long documentNumber);

//    List<UserDto> getAllUsers();

    List<UserDto> getAllUsersSorted();

}
