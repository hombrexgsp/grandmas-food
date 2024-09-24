package domain.combo.error.UserException;

import domain.http.error.ErrorCode;

public final class NotFieldsUpdatedException extends RuntimeException {
    public NotFieldsUpdatedException() {
        super("No single field is different in this update");
    }
    public ErrorCode getErrorCode() {
        return ErrorCode.VALIDATION_ERROR;
    }
}
