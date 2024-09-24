package com.globant.model;

import domain.user.DocumentIdentity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DocumentIdentity document;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastName;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, precision = 10, scale = 0)
    private Long phoneNumber;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;


    public User(String address, DocumentIdentity document, String email, String firstName, String lastName, Long phoneNumber, String password) {
        this.address = address;
        this.document = document;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User() {
    }

    public @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") String password) {
        this.password = password;
    }
}
