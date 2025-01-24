package com.portfolio.showcase_spring.Global;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ConstraintViolationExceptionFilter {

    public static class ErrorMsg {
        public ErrorMsg() {
            errors = new HashMap<>();
        }

        public Map<String, String> errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMsg> handle(ConstraintViolationException ex) {
        var errorMsg = new ErrorMsg();

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errorMsg.errors.put(field, message);
        }

        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
