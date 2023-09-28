package com.wellsfargo.bankapp.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        //responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());


//        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());


        responseBody.put("errors",  ex.getMessage());

        return new ResponseEntity<>(responseBody, headers, status);
    }

    
    
    @ExceptionHandler(value=BalanceInsufficientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleBalanceInsufficientException(BalanceInsufficientException ex) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    
    @ExceptionHandler(value=SavingsAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleSavingsAccountNotFound(SavingsAccountNotFoundException ex) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleCommonException(Exception ex) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

}
