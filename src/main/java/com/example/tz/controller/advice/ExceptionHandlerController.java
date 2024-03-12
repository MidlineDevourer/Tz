package com.example.tz.controller.advice;

import com.example.tz.exception.ErrorMessage;
import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.exception.impl.IncorrectProfileDataException;
import com.example.tz.exception.impl.NegativeBalanceException;
import com.example.tz.exception.impl.NegativeDepositException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Tag(name = "Exception Handler")
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return new ResponseEntity<>(new ErrorMessage(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectProfileDataException.class)
    public ResponseEntity<ErrorMessage> incorrectPinException(IncorrectProfileDataException incorrectProfileDataException) {
        return new ResponseEntity<>(new ErrorMessage(incorrectProfileDataException.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeBalanceException.class)
    public ResponseEntity<ErrorMessage> negativeBalanceException(NegativeBalanceException negativeBalanceException) {
        return new ResponseEntity<>(new ErrorMessage(negativeBalanceException.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegativeDepositException.class)
    public ResponseEntity<ErrorMessage> negativeDepositException(NegativeDepositException negativeDepositException) {
        return new ResponseEntity<>(new ErrorMessage(negativeDepositException.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
