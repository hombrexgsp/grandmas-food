package com.globant.http;

import domain.combo.error.ComboDuplicatedName;
import domain.combo.error.ComboNotFound;
import domain.combo.error.NoComboChanges;
import domain.http.error.ErrorCode;
import domain.http.error.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ComboDuplicatedName.class)
    public ResponseEntity<ErrorResponse> handleComboDuplicatedName(ComboDuplicatedName e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(ErrorCode.DUPLICATE_PRODUCT_NAME, e.getMessage(), e)
        );
    }

    @ExceptionHandler(ComboNotFound.class)
    public ResponseEntity<ErrorResponse> handleComboNotFound(ComboNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(ErrorCode.PRODUCT_NOT_FOUND, e.getMessage(), e)
        );
    }

    @ExceptionHandler(NoComboChanges.class)
    public ResponseEntity<ErrorResponse> handleNoComboChanges(NoComboChanges e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(ErrorCode.NO_PRODUCT_CHANGES, e.getMessage(), e)
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ErrorCode.VALIDATION_ERROR, e.getMessage(), e)
        );
    }

    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(500).body(
                new ErrorResponse(ErrorCode.GENERAL_ERROR, e.getMessage(), e)
        );
    }
    
}
