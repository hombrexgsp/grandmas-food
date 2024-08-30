package domain.http.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // User
    USER_NOT_FOUND("E1001"),
    DUPLICATE_USER("E1002"),

    // Product
    PRODUCT_NOT_FOUND("E2001"),
    DUPLICATE_PRODUCT_NAME("E2002"),
    NO_PRODUCT_CHANGES("E2003"),

    // Validation
    VALIDATION_ERROR("E9001"),

    // General
    GENERAL_ERROR("E9999");

    private final String code;

}
