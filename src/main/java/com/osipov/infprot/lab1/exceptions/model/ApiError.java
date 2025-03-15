package com.osipov.infprot.lab1.exceptions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
    private final HttpStatus status;
    private final String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private List<String> errors;
    private final String reason;

    public ApiError(Throwable exception, HttpStatus status, String reason) {
        this.status = status;
        this.message = exception.getMessage();
        this.timestamp = LocalDateTime.now();
        this.reason = reason;
    }

    public ApiError(HttpStatus status, String message, String reason) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.reason = reason;
    }

    public ApiError(NotFoundException ex) {
        this.message = "Запрашиваемый ресурс не найден";
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.NOT_FOUND;
        this.reason = ex.getMessage();
    }

    public ApiError(ValidationException ex) {
        this.message = "Ошибка передаваемых данных";
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.BAD_REQUEST;
        this.reason = ex.getMessage();
    }
}
