package domain.http.error;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String code;
    private final LocalDateTime timestamp;
    private final String description;
    private final String exception;

    public ErrorResponse(ErrorCode errorCode, String description, Throwable exception) {
        this.code = errorCode.getCode();
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.exception = exception.getClass().getName();
    }
}
