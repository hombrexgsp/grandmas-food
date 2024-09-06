package domain.combo.error.UserException;

import domain.http.error.ErrorCode;

public class InvalidOrIncompleteUserException extends RuntimeException {
    public InvalidOrIncompleteUserException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
      return ErrorCode.VALIDATION_ERROR;
    }
}
