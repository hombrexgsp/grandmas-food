package domain.combo.error.UserException;


import domain.http.error.ErrorCode;
import domain.user.DocumentIdentity;

public final class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(DocumentIdentity document){
        super(STR."User with document: \{document.getDocumentNumber()} already exists");
    }

    public ErrorCode getErrorCode(){
        return ErrorCode.DUPLICATE_USER;
    }
}
