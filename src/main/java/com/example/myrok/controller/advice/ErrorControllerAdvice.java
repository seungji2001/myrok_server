package com.example.myrok.controller.advice;

import com.example.myrok.dto.error.ErrorResponse;
import com.example.myrok.type.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
