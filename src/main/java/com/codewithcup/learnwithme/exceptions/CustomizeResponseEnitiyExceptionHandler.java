package com.codewithcup.learnwithme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeResponseEnitiyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(globalExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoriesNotFoundException.class)
    public final ResponseEntity<Object> handleCategoryException(CategoriesNotFoundException ex, WebRequest request) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(new Date(), "There are no categories available right now", request.getDescription(false));
        return new ResponseEntity(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleAllEamilException(ResourceNotFoundException ex, WebRequest request) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(new Date(), "Sorry Data not found ", request.getDescription(false));
        return new ResponseEntity(globalExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<Object> handleAllContentException(ContentNotFoundException ex, WebRequest request) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(new Date(), "This content not available right now", request.getDescription(false));
        return new ResponseEntity(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

}
