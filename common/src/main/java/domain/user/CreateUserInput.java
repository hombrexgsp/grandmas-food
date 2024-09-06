package domain.user;

import lombok.Data;

@Data
public class CreateUserInput {

    private DocumentIdentity documentIdentity;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String address;


}
