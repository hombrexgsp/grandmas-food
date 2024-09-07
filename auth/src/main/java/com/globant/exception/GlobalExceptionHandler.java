package com.globant.exception;


import domain.combo.error.UserException.*;
import domain.http.error.ErrorCode;
import domain.http.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(
                userNotFoundException.getErrorCode(),
                userNotFoundException.getMessage(),
                userNotFoundException
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserException duplicateUserException) {
     ErrorResponse errorResponse = new ErrorResponse(
       duplicateUserException.getErrorCode(),
       duplicateUserException.getMessage(),
       duplicateUserException
     );
     return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException validationException){
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                validationException.getMessage(),
                validationException
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.GENERAL_ERROR,
                exception.getMessage(),
                exception
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidOrIncompleteUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidOrIncompleteUserException(InvalidOrIncompleteUserException invalidOrIncompleteUserException){
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                invalidOrIncompleteUserException.getMessage(),
                invalidOrIncompleteUserException
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFieldsUpdatedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleNotFieldsUpdatedException(NotFieldsUpdatedException notFieldsUpdatedException){
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.NOT_FIELDS_UPDATED,
                notFieldsUpdatedException.getMessage(),
                notFieldsUpdatedException
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


}
