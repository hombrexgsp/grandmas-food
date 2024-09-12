package com.globant.domain.user;

import domain.user.DocumentIdentity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserInput {
    @NotNull
    private DocumentIdentity documentIdentity;

    @NotNull
    @Size(max=255)
    private String firstName;

    @NotNull
    @Size(max=255)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long phoneNumber;

    @NotNull
    @Size(max=500)
    private String address;
}
