package com.levi9.internship.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class InternshipExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), "Internship Not Found API Client Error");
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(ConflictException exception) {
        return buildErrorResponse(HttpStatus.CONFLICT, exception.getMessage(), "Internship Conflict API Client Error");
    }

    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus httpStatus, String message, String explanation) {
        return new ResponseEntity<>(ApiError.builder().message(message).explanation(explanation).build(), httpStatus);
    }

}
