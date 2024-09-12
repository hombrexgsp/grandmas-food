package com.globant.http;

import com.globant.domain.error.ErrorBody;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @GraphQlExceptionHandler
    public GraphQLError handleResolver(HttpClientErrorException e) {
        var errorBody = e.getResponseBodyAs(ErrorBody.class);
        return GraphQLError
                .newError()
                .errorType(ErrorClassification.errorClassification(errorBody.code()))
                .extensions(Map.of("timestamp", errorBody.timestamp(), "exception", errorBody.exception()))
                .message(errorBody.description())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle (Throwable e) {
        log.error(e.getMessage());
        return GraphQLError
                .newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .build();
    }
}
