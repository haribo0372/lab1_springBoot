package com.osipov.infprot.lab1.exceptions;

import com.osipov.infprot.lab1.exceptions.model.ApiError;
import com.osipov.infprot.lab1.exceptions.model.NotFoundException;
import com.osipov.infprot.lab1.exceptions.model.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.List;

import static com.osipov.infprot.lab1.exceptions.util.ExceptionHandlerUtils.*;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            log.warn("Ошибка валидации поля \"{}\" : {}", fieldName, errorMessage);
            errors.add(String.format("%s : %s", fieldName, errorMessage));
        });

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Ошибка корректности полей", "");
        apiError.setErrors(errors);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), "Не переданы обязательные параметры");

        loggingInfo("ExceptionHandler(MissingServletRequestParameterException.class)", apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiError> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), "Неверно указаны поля/параметры");

        loggingInfo("ExceptionHandler(HandlerMethodValidationException.class)", apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        ApiError apiError = new ApiError(ex);
        loggingInfo("ExceptionHandler(NotFoundException.class)", apiError);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
        ApiError apiError = new ApiError(ex);
        loggingInfo("ExceptionHandler(NotFoundException.class)", apiError);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                "Конфликт данных"
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleThrowable(Throwable ex) {
        ApiError apiError =
                new ApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Произошла непредвиденная ошибка");
        loggingErr("ExceptionHandler(Throwable.class)", apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
