package com.nikolamateski.pcbooklibrary.config;

import com.nikolamateski.pcbooklibrary.exception.ResourceNotFoundException;
import com.nikolamateski.pcbooklibrary.util.ErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(ResourceNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorDTO(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(ConstraintViolationException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorDTO(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handle(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorDTO(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(MethodArgumentNotValidException exception) {
        LOGGER.error(exception.getMessage(), exception);

        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        var message = fieldErrors.stream()
                .map(item -> item.getDefaultMessage())
                .reduce((current, next) ->
                        current + "\n" + next
                )
                .orElse("Invalid request");
        return new ErrorDTO(message);
    }
}
