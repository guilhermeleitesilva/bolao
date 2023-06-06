package com.saturno.bolao.controller.exceptions;

import com.saturno.bolao.service.exceptions.BadRequestException;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        DefaultError error = new DefaultError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DefaultError> badRequest(BadRequestException e, HttpServletRequest request) {
        DefaultError error = new DefaultError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Bad Request");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
