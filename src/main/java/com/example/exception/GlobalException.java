package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

    @ControllerAdvice
    public class GlobalException{

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
            return new ResponseEntity<>("User tapilmadi : " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InsufficientFundsException.class)
        public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException ex, WebRequest request) {
            return new ResponseEntity<>("Hesabinizda  yetərli məbləğ yoxdur.: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
            return new ResponseEntity<>("Xəta: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

