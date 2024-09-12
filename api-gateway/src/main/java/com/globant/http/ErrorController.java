package com.globant.http;

import com.globant.domain.error.ErrorBody;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @GraphQlExceptionHandler
    public GraphQLError handleResolver(HttpClientErrorException e) {
        final var errorBody = e.getResponseBodyAs(ErrorBody.class);
        return GraphQLError
                .newError()
                .errorType(ErrorClassification.errorClassification(errorBody.code()))
                .extensions(Map.of("timestamp", errorBody.timestamp(), "exception", errorBody.exception()))
                .message(errorBody.description())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleGeneralValidation (ConstraintViolationException e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleMethodArgumentValidation (MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleUuidValidation (HandlerMethodValidationException e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleBindValidation (BindException e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .extensions(Map.of("errors", e.getFieldErrors(), "cause", e.getCause()))
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle (Throwable e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .extensions(Map.of("exception", e.getClass().getSimpleName(), "cause", e.getCause()))
                .build();
    }
}
