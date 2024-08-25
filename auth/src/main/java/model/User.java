package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import model.identity.DocumentIdentity;


@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    private DocumentIdentity document;

    @NotNull
    @Column(nullable = false)
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    @Size(max = 255)
    private String lastName;

    @Email
    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 0)
    private Long phoneNumber;

    @NotNull
    @Column(nullable = false)
    @Size(max = 500)
    private String address;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    public User(String address, DocumentIdentity document, String email, String firstName, String lastName, String password, Long phoneNumber) {
        this.address = address;
        this.document = document;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public @NotNull @Size(max = 500) String getAddress() {
        return address;
    }

    public void setAddress(@NotNull @Size(max = 500) String address) {
        this.address = address;
    }

    public @NotNull DocumentIdentity getDocument() {
        return document;
    }

    public void setDocument(@NotNull DocumentIdentity document) {
        this.document = document;
    }

    public @Email @NotNull @Size(max = 255) String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull @Size(max = 255) String email) {
        this.email = email;
    }

    public @NotNull @Size(max = 255) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @Size(max = 255) String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public @NotNull @Size(max = 255) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull @Size(max = 255) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?.,-])[A-Za-z\\d#$@!%&*?.,-]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") String password) {
        this.password = password;
    }

    public @NotNull @Digits(integer = 10, fraction = 0) Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull @Digits(integer = 10, fraction = 0) Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
