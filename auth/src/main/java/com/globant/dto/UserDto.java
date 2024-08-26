package com.globant.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.globant.model.identity.DocumentIdentity;

public class UserDto {


    @NotNull
    private final DocumentIdentity documentIdentity;

    @NotNull
    @Size(max=255)
    private final String firstName;

    @NotNull
    @Size(max=255)
    private final String lastName;

    @Email
    @NotNull
    private final String email;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private final Long phoneNumber;

    @NotNull
    @Size(max=500)
    private final String address;


    public UserDto(DocumentIdentity documentIdentity, String firstName, String lastName, String email, Long phoneNumber, String address) {
        this.documentIdentity = documentIdentity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public @NotNull @Size(max = 500) String getAddress() {
        return address;
    }

    public @NotNull DocumentIdentity getDocumentIdentity() {
        return documentIdentity;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public @NotNull @Size(max = 255) String getFirstName() {
        return firstName;
    }

    public @NotNull @Size(max = 255) String getLastName() {
        return lastName;
    }

    public @NotNull @Digits(integer = 10, fraction = 0) Long getPhoneNumber() {
        return phoneNumber;
    }
}
