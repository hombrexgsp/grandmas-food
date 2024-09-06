package domain.user;

public record UpdateUserInput(
     String firstName,
     String lastName,
     String email,
     Long phoneNumber,
     String address
){}
