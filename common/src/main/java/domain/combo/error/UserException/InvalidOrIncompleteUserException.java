package domain.combo.error.UserException;

import domain.http.error.ErrorCode;

public final class InvalidOrIncompleteUserException extends RuntimeException {
    public InvalidOrIncompleteUserException() {
        super("Values of the customer are invalid or incomplete");
    }

    public ErrorCode getErrorCode() {
      return ErrorCode.VALIDATION_ERROR;
    }
}
