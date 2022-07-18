package com.maveric.account.exception;


import com.maveric.account.model.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApplicationError> userNotFoundException(UserNotFoundException e){
        ApplicationError error =  new ApplicationError(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, error.getCode());
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<ApplicationError> accountNotFoundException(AccountNotFoundException e){
        ApplicationError error =  new ApplicationError(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, error.getCode());
    }




}
