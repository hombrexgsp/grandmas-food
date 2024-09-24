package domain.combo.error.UserException;


import domain.http.error.ErrorCode;

public final class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long documentNumber){
        super(STR."User with document number \{documentNumber} not found");
    }

    public UserNotFoundException(String name){
        super(STR."No single user with name \{name} found");
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.USER_NOT_FOUND;
    }
}
