package com.globant.mapper;

import com.globant.dto.UserDto;
import com.globant.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
                user.getDocument(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress()
        );
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.getAddress(),
                userDto.getDocumentIdentity(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhoneNumber(),
                null
        );
    }
}


