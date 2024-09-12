package com.globant.domain.user;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateUserInput(
        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,

        @Email
        String email,

        @Digits(integer = 10, fraction = 0)
        Long phoneNumber,

        @NotEmpty
        String address
){}
