package com.project.bootcampsantander.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*Essa class é responsavel interceptar
 quando algum problema acontecer e retornar o
 status code que for necessario para o front*/
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionsResponse> handlerSecurity(BusinessException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionsResponse(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionsResponse> handlerSecurity(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionsResponse(e.getMessage()));
    }


}
