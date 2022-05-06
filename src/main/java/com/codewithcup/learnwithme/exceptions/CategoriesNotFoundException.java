package com.codewithcup.learnwithme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException(String s) {
    }
}
